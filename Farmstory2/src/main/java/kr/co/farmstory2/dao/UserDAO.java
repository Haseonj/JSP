package kr.co.farmstory2.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBCP;
import kr.co.farmstory2.db.Sql;
import kr.co.farmstory2.vo.TermsVO;
import kr.co.farmstory2.vo.UserVO;

public class UserDAO {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	Connection conn = null;
	PreparedStatement psmt = null;
	PreparedStatement psmt1 = null;
	PreparedStatement psmt2 = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	
	public int insertUser(UserVO vo) {
		int result = 0;
		try{
			logger.info("insertUser...");
			conn = DBCP.getConnection();
			
			psmt = conn.prepareStatement(Sql.INSERT_USER);
			psmt.setString(1, vo.getUid());
			psmt.setString(2, vo.getPass());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getNick());
			psmt.setString(5, vo.getEmail());
			psmt.setString(6, vo.getHp());
			psmt.setString(7, vo.getZip());
			psmt.setString(8, vo.getAddr1());
			psmt.setString(9, vo.getAddr2());
			psmt.setString(10, vo.getRegip());
			
			result = psmt.executeUpdate();
			
			conn.close();
			psmt.close();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public UserVO selectUser(String uid, String pass) {
		UserVO vo = null;
		try {
			logger.info("selectUser...");
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO();
				vo.setUid(rs.getString(1));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setNick(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setHp(rs.getString(6));
				vo.setGrade(rs.getInt(7));
				vo.setZip(rs.getString(8));
				vo.setAddr1(rs.getString(9));
				vo.setAddr2(rs.getString(10));
				vo.setRegip(rs.getString(11));
				vo.setRdate(rs.getString(12));
			}
			
			conn.close();
			psmt.close();
			rs.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public int selectUserByInfo(String uid, String pass) {
		int result = 0;
		try {
			logger.info("selectUserByInfo...");
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER_BY_INFO);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			// 카운트 커리문을 이용하여 1이 나오면 일치, 0이 나오면 비일치
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			conn.close();
			psmt.close();
			rs.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	
	public UserVO selectUserForFindId (String name, String email) {
		UserVO vo = null;
		try {
			logger.info("selectUserForFindId...");
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_UESR_FOR_FIND_ID);
			psmt.setString(1, name);
			psmt.setString(2, email);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO();
				vo.setUid(rs.getString(1));
				vo.setName(rs.getString(3));
				vo.setEmail(rs.getString(5));
				vo.setRdate(rs.getString(12));
			}
			
			conn.close();
			psmt.close();
			rs.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public UserVO selectUserForFindPw (String uid, String email) {
		UserVO vo = null;
		try {
			logger.info("selectUserForFindPw...");
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_UESR_FOR_FIND_PW);
			psmt.setString(1, uid);
			psmt.setString(2, email);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO();
				vo.setUid(rs.getString(1));
			}
			
			conn.close();
			psmt.close();
			rs.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public UserVO selectUserBySessId(String sessId) {
		UserVO vo = null;
		try {
			logger.info("selecUserBySessId...");
			
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER_BY_SESSID);
			psmt.setString(1, sessId);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO();
				vo.setUid(rs.getString(1));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setNick(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setHp(rs.getString(6));
				vo.setGrade(rs.getInt(7));
				vo.setZip(rs.getString(8));
				vo.setAddr1(rs.getString(9));
				vo.setAddr2(rs.getString(10));
				vo.setRegip(rs.getString(11));
				vo.setRdate(rs.getString(12));
			}
			conn.close();
			psmt.close();
			rs.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public int selectCountUid(String uid) {
		int result = 0;
		
		try{
			logger.info("selectCountUid...");
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			
			rs = psmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			conn.close();
			psmt.close();
			rs.close();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCountNick(String nick) {
		int result = 0;
		
		try{
			logger.info("selectCountNick...");
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			
			rs = psmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			conn.close();
			psmt.close();
			rs.close();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public int selectCountEmail(String email) {
		int result = 0;
		
		try{
			logger.info("selectCountEmail...");
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_EMAIL);
			psmt.setString(1, email);
			
			rs = psmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			conn.close();
			psmt.close();
			rs.close();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public TermsVO selectTerms() {
		TermsVO vo = null;
		try {
			logger.info("selectTerms...");
			conn = DBCP.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_TERMS);
			if(rs.next()) {
				vo = new TermsVO();
				vo.setTerms(rs.getString(1));
				vo.setPrivacy(rs.getString(2));
			}
			
			conn.close();
			stmt.close();
			rs.close();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public void updateUser(UserVO vo) {
		try {
			logger.info("updateUser...");
			conn = DBCP.getConnection();
			conn.setAutoCommit(false);
			
			if(vo.getPass() == null) {
				psmt = conn.prepareStatement(Sql.UPDATE_USER);
				psmt.setString(1, vo.getName());
				psmt.setString(2, vo.getNick());
				psmt.setString(3, vo.getEmail());
				psmt.setString(4, vo.getHp());
				psmt.setString(5, vo.getZip());
				psmt.setString(6, vo.getAddr1());
				psmt.setString(7, vo.getAddr2());
				psmt.setString(8, vo.getUid());
				psmt.executeUpdate();
			}else {
				psmt1 = conn.prepareStatement(Sql.UPDATE_USER);
				psmt2 = conn.prepareStatement(Sql.UPDATE_USER_PASSWORD);
				psmt1.setString(1, vo.getName());
				psmt1.setString(2, vo.getNick());
				psmt1.setString(3, vo.getEmail());
				psmt1.setString(4, vo.getHp());
				psmt1.setString(5, vo.getZip());
				psmt1.setString(6, vo.getAddr1());
				psmt1.setString(7, vo.getAddr2());
				psmt1.setString(8, vo.getUid());
				psmt2.setString(1, vo.getPass());
				psmt2.setString(2, vo.getUid());
				psmt1.executeUpdate();
				psmt2.executeUpdate();
			}
			conn.commit();
			
			conn.close();
			psmt.close();
			psmt1.close();
			psmt2.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public int updateUserPassword(String pass, String uid) {
		int result = 0;
		
		try {
			logger.info("updateUserPassword...");
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_PASSWORD);
			psmt.setString(1, pass);
			psmt.setString(2, uid);
			result = psmt.executeUpdate();
			
			conn.close();
			psmt.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	public void updateUserForSession(String sessId, String uid) {
		try {
			logger.info("updateUserForSession...");
			
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_FOR_SESSION);
			psmt.setString(1, sessId);
			psmt.setString(2, uid);
			psmt.executeUpdate();
			
			conn.close();
			psmt.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void updateUserForSessLimitDate(String sessId) {
		try {
			logger.info("updateUserForSessLimitDate...");
			
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_FOR_SESS_LIMIT_DATE);
			psmt.setString(1, sessId);
			psmt.executeUpdate();
			
			conn.close();
			psmt.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void updateUserForSessionOut(String uid) {
		try {
			logger.info("updateUserForSessionOut...");
			
			conn = DBCP.getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_FOR_SESSION_OUT);
			psmt.setString(1, uid);
			psmt.executeUpdate();
			
			conn.close();
			psmt.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void deleteUser(String uid) {
		try {
			logger.info("deleteUser...");
			conn = DBCP.getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(Sql.DELETE_USER);
			psmt.setString(1, uid);
			psmt.executeUpdate();
			
			conn.commit();
			
			conn.close();
			psmt.close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	
}

