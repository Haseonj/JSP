package kr.co.farmstory2.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.Sql;
import kr.co.farmstory2.vo.ArticleVO;

public class ArticleDAO extends DBHelper {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(ArticleVO vo) {
		int parent = 0;
		try {
			logger.info("insertArticle...");
			conn = getConnection();
			conn.setAutoCommit(false);
			
			
			psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			stmt = conn.createStatement();
			
			psmt.setString(1, vo.getCate());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getContent());
			psmt.setInt(4, vo.getFname() == null ? 0 : 1);
			psmt.setString(5, vo.getUid());
			psmt.setString(6, vo.getRegip());
			
			psmt.executeUpdate();
			rs = stmt.executeQuery(Sql.SELECT_MAX_NO);
			conn.commit();
			
			if(rs.next()) {
				parent = rs.getInt(1);
			}
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return parent;
	}
	
	public void insertFile(int parent, String newName, String fname) {
		try {
			logger.info("insertFile...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, parent);
			psmt.setString(2, newName);
			psmt.setString(3, fname);
			
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public ArticleVO insertComment(ArticleVO vo) {
		ArticleVO article = null;
		int result = 0;
		try {
			logger.info("insertComment...");
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt1 = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt2 = conn.prepareStatement(Sql.UPDATE_ARTICLE_COMMENT_PLUS);
			stmt = conn.createStatement();
			
			psmt1.setInt(1, vo.getParent());
			psmt1.setString(2, vo.getContent());
			psmt1.setString(3, vo.getUid());
			psmt1.setString(4, vo.getRegip());
			psmt2.setInt(1, vo.getParent());
			
			psmt1.executeUpdate();
			psmt2.executeUpdate();
			rs = stmt.executeQuery(Sql.SELECT_COMMENT_LATEST);
			
			conn.commit();
			
			
			if(rs.next()) {
				article = new ArticleVO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setContent(rs.getString(6));
				article.setRdate(rs.getString(11).substring(2, 10));
				article.setNick(rs.getString(12));
			}
			
			close();
			psmt1.close();
			psmt2.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return article;
	}
	
	public int selectCountTotal(String search, String cate) {
		int total = 0;
		
		try {
			logger.info("selectCountTotal...");
			conn = getConnection();
			
			if(search == null) {
				psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL);
				psmt.setString(1, cate);
				rs = psmt.executeQuery();
			}else {
				psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
				psmt.setString(2, "%"+search+"%");
				psmt.setString(3, cate);
				rs = psmt.executeQuery();
			}
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return total;
	}
	
	public List<ArticleVO> selectArticles(String cate, int start) {
		List<ArticleVO> articles = new ArrayList<>();
		try {
			logger.info("selectArticles...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO vo = new ArticleVO();
				vo.setNo(rs.getInt(1));
				vo.setParent(rs.getInt(2));
				vo.setComment(rs.getInt(3));
				vo.setCate(rs.getString(4));
				vo.setTitle(rs.getString(5));
				vo.setContent(rs.getString(6));
				vo.setFile(rs.getInt(7));
				vo.setHit(rs.getInt(8));
				vo.setUid(rs.getString(9));
				vo.setRegip(rs.getString(10));
				vo.setRdate(rs.getString(11));
				vo.setNick(rs.getString(12));
				
				articles.add(vo);
			}
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}
	
	public List<ArticleVO> selectArticlesByKeyword(String keyword, String cate, int start) {
		List<ArticleVO> articles = new ArrayList<>();
		try {
			logger.info("selectArticlesByKeyword...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES_BY_KEYWORD);
			psmt.setString(1, "%"+keyword+"%");
			psmt.setString(2, "%"+keyword+"%");
			psmt.setString(3, cate);
			psmt.setInt(4, start);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO vo = new ArticleVO();
				vo.setNo(rs.getInt(1));
				vo.setParent(rs.getInt(2));
				vo.setComment(rs.getInt(3));
				vo.setCate(rs.getString(4));
				vo.setTitle(rs.getString(5));
				vo.setContent(rs.getString(6));
				vo.setFile(rs.getInt(7));
				vo.setHit(rs.getInt(8));
				vo.setUid(rs.getString(9));
				vo.setRegip(rs.getString(10));
				vo.setRdate(rs.getString(11));
				vo.setNick(rs.getString(15));
				
				articles.add(vo);
			}
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}
	
	public ArticleVO selectArticle(String no) {
		ArticleVO vo = null;
		try {
			logger.info("selectArticle...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new ArticleVO();
				vo.setNo(rs.getInt(1));
				vo.setParent(rs.getInt(2));
				vo.setComment(rs.getInt(3));
				vo.setCate(rs.getString(4));
				vo.setTitle(rs.getString(5));
				vo.setContent(rs.getString(6));
				vo.setFile(rs.getInt(7));
				vo.setHit(rs.getInt(8));
				vo.setUid(rs.getString(9));
				vo.setRegip(rs.getString(10));
				vo.setRdate(rs.getString(11));
				vo.setFno(rs.getInt(12));
				vo.setPno(rs.getInt(13));
				vo.setNewName(rs.getString(14));
				vo.setOriName(rs.getString(15));
				vo.setDownload(rs.getInt(16));
			}
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public List<ArticleVO> selectComments(String parent) {
		List<ArticleVO> comments = new ArrayList<>();
		
		try {
			logger.info("selectComments...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
			psmt.setString(1, parent);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO comment = new ArticleVO();
				comment.setNo(rs.getInt(1));
				comment.setParent(rs.getInt(2));
				comment.setComment(rs.getInt(3));
				comment.setCate(rs.getString(4));
				comment.setTitle(rs.getString(5));
				comment.setContent(rs.getString(6));
				comment.setFile(rs.getInt(7));
				comment.setHit(rs.getInt(8));
				comment.setUid(rs.getString(9));
				comment.setRegip(rs.getString(10));
				comment.setRdate(rs.getString(11).substring(2, 10));
				comment.setNick(rs.getString(12));
				
				comments.add(comment);
			}
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return comments;
	}
	
	public List<ArticleVO> selectLatests(String cate1, String cate2, String cate3) {
		List<ArticleVO> latests = new ArrayList<>();
		try {
			logger.info("selectLatests...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_LATESTS);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			psmt.setString(3, cate3);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO vo = new ArticleVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setRdate(rs.getString(3).substring(2, 10));
				
				latests.add(vo);
			}
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return latests;
	}
	
	public synchronized List<ArticleVO> selectLatest(String tabsCate) {
		List<ArticleVO> latests = new ArrayList<>();
		try {
			logger.info("selectLatests(String)...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_LATEST);
			psmt.setString(1, tabsCate);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleVO vo = new ArticleVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setRdate(rs.getString(3).substring(2, 10));
				
				latests.add(vo);
			}
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return latests;
	}
	
	public void updateArticle(String title, String content, String no) {
		try {
			logger.info("updateArticle...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, no);
			psmt.executeUpdate();

			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void updateArticleHit(String no) {
		try {
			logger.info("updateArticleHit...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			psmt.setString(1, no);
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public int updateComment(String content, String no) {
		int result = 0;
		try {
			logger.info("updateComment...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_COMMENT);
			psmt.setString(1, content);
			psmt.setString(2, no);
			result = psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public void deleteArticle(String no) {
		try {
			logger.info("deleteArticle...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void deleteFile(String no) {
		try {
			logger.info("deleteFile...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.DELETE_FILE);
			psmt.setString(1, no);
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public int deleteComment(String no, String parent) {
		int result = 0;
		try {
			logger.info("deleteComment...");
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt1 = conn.prepareStatement(Sql.DELETE_COMMENT);
			psmt2 = conn.prepareStatement(Sql.UPDATE_ARTICLE_COMMENT_MINUS);
			psmt1.setString(1, no);
			psmt2.setString(1, parent);
			result = psmt1.executeUpdate();
			psmt2.executeUpdate();
			
			conn.commit();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
