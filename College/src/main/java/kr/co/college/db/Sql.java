package kr.co.college.db;

public class Sql {
	
	public static final String SELECT_LECTURE = "SELECT * FROM `lecture`";
	
	public static final String SELECT_REGISTER = "SELECT `regStdNo`, b.stdName, c.lecName, `regLecNo`, `regMidScore`, `regFinalScore`, `regTotalScore`, `regGrade` "
												+ "FROM `register` AS a "
												+ "JOIN `student` AS b "
												+ "ON a.regStdNo = b.stdNo "
												+ "JOIN `lecture` AS c "
												+ "ON a.regLecNo = c.lecNo";
	
	public static final String SELECT_STUDENT = "SELECT * FROM `student`";
	
	public static final String INSERT_LECTURE = "INSERT INTO `lecture` values (?,?,?,?,?)";
}
