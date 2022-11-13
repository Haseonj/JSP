package dao;

import java.util.ArrayList;
import java.util.List;

import db.DBHelper;
import vo.User5VO;
import vo.User6VO;

public class User6DAO extends DBHelper {
	
	private static User6DAO instance = new User6DAO();
	public static User6DAO getInstance() {
		return instance;
	}
	private User6DAO() {}
	
	public void insertUser6(User6VO vo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("insert into `user6` values(?,?,?,?,?,?)");
			psmt.setString(1, vo.getUid());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getBirth());
			psmt.setInt(4, vo.getAge());
			psmt.setString(5, vo.getAddress());
			psmt.setString(6, vo.getHp());
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User6VO selectUser6(String uid) {
		User6VO vo = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("select * from `user6` where `uid`=?");
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new User6VO();
				vo.setUid(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setBirth(rs.getString(3));
				vo.setAge(rs.getInt(4));
				vo.setAddress(rs.getString(5));
				vo.setHp(rs.getString(6));
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public List<User6VO> selectUser6s() {
		List<User6VO> users = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `user6`");
			
			while(rs.next()) {
				User6VO vo = new User6VO();
				vo.setUid(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setBirth(rs.getString(3));
				vo.setAge(rs.getInt(4));
				vo.setAddress(rs.getString(5));
				vo.setHp(rs.getString(6));
				
				users.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public void updateUser6(User6VO vo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("update `user6` set `name`=?, `birth`=?, `age`=?, `address`=?, `hp`=? where `uid`=?");
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getBirth());
			psmt.setInt(3, vo.getAge());
			psmt.setString(4, vo.getAddress());
			psmt.setString(5, vo.getHp());
			psmt.setString(6, vo.getUid());
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser6(String uid) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("delete from `user6` where `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
