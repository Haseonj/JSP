package kr.co.college.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.college.bean.CollegeBean;
import kr.co.college.db.DBHelper;
import kr.co.college.db.Sql;


// DAO(Data Access Object) : 데이터베이스 처리 클래스
public class CollegeDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static CollegeDAO instance = new CollegeDAO();
	public static CollegeDAO getInstance() {
		return instance;
	}
	private CollegeDAO() {}
	
	public List<CollegeBean> selectLecuter() {
		List<CollegeBean> lectures = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_LECTURE);
			
			while(rs.next()) {
				CollegeBean lecture = new CollegeBean();
				lecture.setLecNo(rs.getInt(1));
				lecture.setLecName(rs.getString(2));
				lecture.setLecCredit(rs.getInt(3));
				lecture.setLecTime(rs.getInt(4));
				lecture.setLecClass(rs.getString(5));
				
				lectures.add(lecture);
			}
			
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lectures;
	}
	
	public List<CollegeBean> selectRegister() {
		List<CollegeBean> registers = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_REGISTER);
			
			while(rs.next()) {
				CollegeBean register = new CollegeBean();
				register.setRegStdNo(rs.getInt(1));
				register.setStdName(rs.getString(2));
				register.setLecName(rs.getString(3));
				register.setRegLecNo(rs.getInt(4));
				register.setRegMidScore(rs.getInt(5));
				register.setRegFinalScore(rs.getInt(6));
				register.setRegTotalScore(rs.getInt(7));
				register.setRegGrade(rs.getString(8));
				
				registers.add(register);
			}
			
			close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return registers;
		
	}
	
	public List<CollegeBean> selectStudent() {
		
		List<CollegeBean> students = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_STUDENT);
			
			while(rs.next()) {
				CollegeBean student = new CollegeBean();
				student.setStdNo(rs.getInt(1));
				student.setStdName(rs.getString(2));
				student.setStdHp(rs.getString(3));
				student.setStdYear(rs.getInt(4));
				student.setStdAddress(rs.getString(5));
				
				students.add(student);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return students;
	}
	
	public int insertLecture(CollegeBean lecture) {
		int result = 0;
		
		try {
			logger.info("insertLecture");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_LECTURE);
			psmt.setInt(1, lecture.getLecNo());
			psmt.setString(2, lecture.getLecName());
			psmt.setInt(3, lecture.getLecCredit());
			psmt.setInt(4, lecture.getLecTime());
			psmt.setString(5, lecture.getLecClass());
			
			result = psmt.executeUpdate();
			
			close();
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
	
	
}