package farmstory1.db;

public class Sql {
	
	public static final String INSERT_USER = "insert into `board_user` set "
											+ "`uid`=?,"
											+ "`pass`=sha2(?, 256),"
											+ "`name`=?,"
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=?,"
											+ "`regip`=?,"
											+ "`rdate`=now()";
	
	public static final String SELECT_USER = "";
	
	public static final String SELECT_TERMS = "select * from `board_terms`";
	
	public static final String SELECT_COUNT_UID = "select count('uid') from `board_user` where `uid`=?";
	
	public static final String SELECT_COUNT_NICK = "select count('nick') from `board_user` where `nick`=?";
}
