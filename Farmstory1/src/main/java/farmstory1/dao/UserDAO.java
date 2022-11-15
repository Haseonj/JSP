package farmstory1.dao;

import farmstory1.bean.TermsBean;
import farmstory1.bean.UserBean;
import farmstory1.db.DBHelper;
import farmstory1.db.Sql;

public class UserDAO extends DBHelper {
	
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	private UserDAO() {}
	
	public void insertUser(UserBean user) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_USER);
			psmt.setString(1, user.getUid());
			psmt.setString(2, user.getPass());
			psmt.setString(3, user.getName());
			psmt.setString(4, user.getNick());
			psmt.setString(5, user.getEmail());
			psmt.setString(6, user.getHp());
			psmt.setString(7, user.getZip());
			psmt.setString(8, user.getAddr1());
			psmt.setString(9, user.getAddr2());
			psmt.setString(10, user.getRegip());
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectUser() {}
	
	public TermsBean selectTerms() {
		TermsBean terms = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_TERMS);
			
			if(rs.next()) {
				terms = new TermsBean();
				terms.setTerms(rs.getString(1));
				terms.setPrivacy(rs.getString(2));;
			}
			
			close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return terms;
	}
	
	public int selectCountUid(String uid) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int selectCountNick(String nick) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
