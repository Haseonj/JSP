package kr.co.farmstory1.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory1.bean.ArticleBean;
import kr.co.farmstory1.db.DBHelper;
import kr.co.farmstory1.db.Sql;

public class ArticleDAO extends DBHelper {

	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getinstance() {
		return instance;
	}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ArticleDAO() {}
	
	//
	public int insertArticle(ArticleBean ab) {
		int parent = 0;
		
		try {
			logger.info("insertArticle...");
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			stmt = conn.createStatement();
			
			psmt.setString(1, ab.getCate());
			psmt.setString(2, ab.getTitle());
			psmt.setString(3, ab.getContent());
			psmt.setInt(4, ab.getFname() == null ? 0 : 1);
			psmt.setString(5, ab.getUid());
			psmt.setString(6, ab.getRegip());
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
	
	public ArticleBean selectArticle(String no) {
		ArticleBean ab = null;
		try {
			logger.info("selectArticle...");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, no);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				ab = new ArticleBean();
				ab.setNo(rs.getInt(1));
				ab.setParent(rs.getInt(2));
				ab.setComment(rs.getInt(3));
				ab.setCate(rs.getString(4));
				ab.setTitle(rs.getString(5));
				ab.setContent(rs.getString(6));
				ab.setFile(rs.getInt(7));
				ab.setHit(rs.getInt(8));
				ab.setUid(rs.getString(9));
				ab.setRegip(rs.getString(10));
				ab.setRdate(rs.getString(11));
				ab.setFno(rs.getInt(12));
				ab.setPno(rs.getInt(13));
				ab.setNewName(rs.getString(14));
				ab.setOriName(rs.getString(15));
				ab.setDownload(rs.getInt(16));
			}
			
			close();
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return ab;
	}
	
	
	public List<ArticleBean> selectArticles(String cate, int limitStart) {
		List<ArticleBean> articles = new ArrayList<>();
		
		try {
			logger.info("selectArticles...");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, limitStart);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleBean ab = new ArticleBean();
				ab.setNo(rs.getInt(1));
				ab.setParent(rs.getInt(2));
				ab.setComment(rs.getInt(3));
				ab.setCate(rs.getString(4));
				ab.setTitle(rs.getString(5));
				ab.setContent(rs.getString(6));
				ab.setFile(rs.getInt(7));
				ab.setHit(rs.getInt(8));
				ab.setUid(rs.getString(9));
				ab.setRegip(rs.getString(10));
				ab.setRdate(rs.getString(11));
				ab.setNick(rs.getString(12));
				
				articles.add(ab);
			}
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return articles;
	}
	
	public List<ArticleBean> selectLatests(String cate1, String cate2, String cate3) {
		List<ArticleBean> latests = new ArrayList<>();
		try {
			logger.info("selectLatest...");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_LATESTS);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			psmt.setString(3, cate3);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleBean ab = new ArticleBean();
				ab.setNo(rs.getInt(1));
				ab.setTitle(rs.getString(2));
				ab.setRdate(rs.getString(3).substring(2, 10));
				
				latests.add(ab);
			}
			
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return latests;
	}
	
	public synchronized List<ArticleBean> selectLatests(String cate) {
		List<ArticleBean> latests = new ArrayList<>();
		try {
			logger.info("selectLatest(String)...");
			
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_LATEST);
			psmt.setString(1, cate);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleBean ab = new ArticleBean();
				ab.setNo(rs.getInt(1));
				ab.setTitle(rs.getString(2));
				ab.setRdate(rs.getString(3).substring(2, 10));
				
				latests.add(ab);
			}
			
			close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return latests;
	}
	
	public int selectCountTotal(String cate) {
		int total = 0;
		
		try {
			logger.info("selectCountTotal...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_TOTAL);
			psmt.setString(1, cate);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return total;
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
	
	public void deleteArticle(String no) {
		try {
			logger.info("deleteArticle...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, null);
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
