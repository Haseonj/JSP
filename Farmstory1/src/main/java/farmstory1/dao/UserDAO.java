package farmstory1.dao;

import farmstory1.bean.TermsBean;
import farmstory1.db.DBHelper;

public class UserDAO extends DBHelper {
	
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	private UserDAO() {}
	
	public void insertUser() {}
	
	public void selectUser() {}
	
	public TermsBean selectTerms() {
		TermsBean terms = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `board_terms`");
			
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
			psmt = conn.prepareStatement("select count('uid') from `board_user` where `uid`=?");
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
}
