package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.Sql;
import kr.co.jboard2.vo.ArticleVO;

public class ArticleDAO extends DBHelper {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int selectCountTotal() {
		int total = 0;
		
		try {
			logger.info("selectCountTotal...");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_COUNT_TOTAL);
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return total;
	}
	
	public List<ArticleVO> selectArticles(int limitStart) {
		List<ArticleVO> articles = new ArrayList<>();
		try {
			logger.info("selectArticle...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setInt(1, limitStart);
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
	
}
