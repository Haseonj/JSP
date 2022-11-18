package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBHelper;
import db.Sql;
import vo.BookVO;

public class BookDAO extends DBHelper {

	private static BookDAO instance = new BookDAO();
	public static BookDAO getInstance() {
		return instance;
	}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private BookDAO() {}
	
	public void insertBook(BookVO vo) {
		try {
			logger.info("insertBook...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_BOOK);
			psmt.setInt(1, vo.getBookId());
			psmt.setString(2, vo.getBookName());
			psmt.setString(3, vo.getPublisher());
			psmt.setInt(4, vo.getPrice());
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public BookVO selectBook(String bookId) {
		BookVO vo = null;
		try {
			logger.info("selecBook...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_BOOK);
			psmt.setString(1, bookId);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new BookVO();
				vo.setBookId(rs.getString(1));
				vo.setBookName(rs.getString(2));
				vo.setPublisher(rs.getString(3));
				vo.setPrice(rs.getString(4));
			}
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public List<BookVO> selectBooks() {
		List<BookVO> books = new ArrayList<>();
		try {
			logger.info("selectBooks...");
			
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_BOOKS);
			
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setBookId(rs.getInt(1));
				vo.setBookName(rs.getString(2));
				vo.setPublisher(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				
				books.add(vo);
			}
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return books;
	}
	
	public void updateBook(BookVO vo) {
		try {
			logger.info("updateBook...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_BOOK);
			psmt.setString(1, vo.getBookName());
			psmt.setString(2, vo.getPublisher());
			psmt.setInt(3, vo.getPrice());
			psmt.setInt(4, vo.getBookId());
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void deleteBook(String bookId) {
		try {
			logger.info("deleteBook...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.DELETE_BOOK);
			psmt.setString(1, bookId);
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}
