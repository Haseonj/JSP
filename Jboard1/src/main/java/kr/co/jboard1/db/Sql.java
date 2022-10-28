package kr.co.jboard1.db;

public class Sql {

	// user
	public static final String INSERT_USER = "INSERT INTO `board_user` SET "
											+ "`uid`=?,"
											+ "`pass`=SHA2(?, 256),"
											+ "`name`=?,"
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=?,"
											+ "`regip`=?,"
											+ "`rdate`=NOW()";
	
	public static final String SELECT_TERMS = "SELECT * FROM `board_terms`";
	public static final String SELECT_USER = "SELECT * FROM `board_user` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	public static final String SELECT_COUNT_UID = "SELECT COUNT(`uid`) FROM `board_user` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK = "SELECT COUNT(`nick`) FROM `board_user` WHERE `nick`=?";
	
	// board
	public static final String INSERT_ARTICLE = "INSERT INTO `board_article` SET "
												+ "`title`=?,"
												+ "`content`=?,"
												+ "`file`=?,"
												+ "`uid`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	public static final String INSERT_FILE = "INSERT INTO `board_file` SET "
											+ "`parent`=?,"
											+ "`newName`=?,"
											+ "`oriName`=?";
	
	public static final String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `board_article`";
	
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(`no`) FROM `board_article`";
	
	public static final String SELECT_ARTICLES = "SELECT a.*, b.nick FROM `board_article` AS a "
												+ "JOIN `board_user` AS b "
												+ "ON a.uid = b.uid "
												+ "ORDER BY `no` DESC "
												+ "LIMIT ?, 10";
	
	// 이번 쿼리의 경우는 파일을 첨부 하지 않았을 경우에도 출력이 되어야 하는데 inner join은 a 와 b의 교집합만 사용하는 쿼리
	// 이기 때문에 파일 미첨부 데이터는 출력하지 않는다.
	// 하지만, left join의 경우는 a의 모든 정보를 교집합과 같이 출력하기 때문에 파일 미포함 게시글도 출력이 가능해진다.
	public static final String SELECT_ARTICLE = "SELECT a.*, b.fno, b.parent AS pno, b.newName, b.oriName, b.download "
												+ "FROM `board_article` AS a "
												+ "left JOIN `board_file` AS b "
												+ "ON a.`no` = b.`parent` "
												+ "WHERE `no`=?";
	public static final String SELECT_FILE = "select * from `board_file` where `parent`=?";
	
	public static final String UPDATE_ARTICLE_HIT = "UPDATE `board_article` SET `hit` = `hit` + 1 WHERE `no`=?";
	
	public static final String UPDATE_FILE_DOWNLOAD = "UPDATE `board_file` set `download` = `download` + 1 WHERE `fno`=?";
}
