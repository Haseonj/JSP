package kr.co.farmstory2.db;

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
	
	// db에 아이디, 비밀번호가 일치하는지 확인하는 쿼리문
	public static final String SELECT_USER_BY_INFO = "SELECT count('uid') FROM `board_user` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	
	public static final String SELECT_COUNT_UID = "SELECT COUNT(`uid`) FROM `board_user` WHERE `uid`=?";
	
	public static final String SELECT_COUNT_NICK = "SELECT COUNT(`nick`) FROM `board_user` WHERE `nick`=?";
	
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(`email`) FROM `board_user` WHERE `email`=?";
	
	public static final String SELECT_UESR_FOR_FIND_ID = "select * from `board_user` where `name`=? and `email`=?";
	
	public static final String SELECT_UESR_FOR_FIND_PW = "select * from `board_user` where `uid`=? and `email`=?";
	
	public static final String SELECT_USER_BY_SESSID = "SELECT * FROM `board_user` WHERE `sessId`=? AND `sessLimitDate` > NOW()";
	
	public static final String UPDATE_USER = "update `board_user` set "
											+ "`name`=?, "
											+ "`nick`=?, "
											+ "`email`=?, "
											+ "`hp`=?, "
											+ "`zip`=?, "
											+ "`addr1`=?, "
											+ "`addr2`=? "
											+ "where `uid`=?";
	
	public static final String UPDATE_USER_PASSWORD = "update `board_user` set `pass`=SHA2(?, 256) where `uid`=?";
	
	public static final String UPDATE_USER_FOR_SESSION = "UPDATE `board_user` SET `sessId`=?, `sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) WHERE `uid`=?";
	
	public static final String UPDATE_USER_FOR_SESS_LIMIT_DATE = "UPDATE `board_user` SET `sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) WHERE `sessId`=?";
	
	public static final String UPDATE_USER_FOR_SESSION_OUT = "UPDATE `board_user` SET `sessId`=NULL, `sessLimitDate`=NULL WHERE `uid`=?";
	
	public static final String DELETE_ARTICLE_FOR_USER = "DELETE a, b FROM `board_article` AS a "
														+ "LEFT JOIN `board_file` AS b ON a.no = b.parent "
														+ "WHERE `uid`= ?";
	
	public static final String DELETE_USER = "UPDATE `board_user` SET "
											+ "`grade`= 0, "
											+ "`wdate`= NOW() "
											+ "WHERE `uid`= ?";
	
	// board
	public static final String INSERT_ARTICLE = "INSERT INTO `board_article` SET "
												+ "`cate`=?, "
												+ "`title`=?,"
												+ "`content`=?, "
												+ "`file`=?, "
												+ "`uid`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW()";
	
	public static final String INSERT_FILE = "INSERT INTO `board_file` SET "
											+ "`parent`=?, "
											+ "`newName`=?, "
											+ "`oriName`=?";
	
	public static final String INSERT_COMMENT = "INSERT INTO `board_article` SET "
												+ "`parent`=?,"
												+ "`content`=?,"
												+ "`uid`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";

	
	public static final String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `board_article`";
	
	// parent = 0 은 댓글을 카운트 하지 않고 목록에 출력하기 위해서
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(`no`) FROM `board_article` WHERE `parent`=0 AND `cate`=?";
	
	public static final String SELECT_COUNT_TOTAL_FOR_SEARCH = "SELECT COUNT(`no`) FROM `board_article` AS a "
																+ "JOIN `board_user` AS b ON a.uid = b.uid "
																+ "WHERE `parent`=0 AND (`title` LIKE ? OR `nick` LIKE ?) AND `cate`=?";
	
	// 목록에 댓글과 같이 출력되는것을 방지
	public static final String SELECT_ARTICLES = "SELECT a.*, b.nick FROM `board_article` AS a "
												+ "JOIN `board_user` AS b "
												+ "ON a.uid = b.uid "
												+ "WHERE `parent`=0 AND `cate`=?"
												+ "ORDER BY `no` DESC "
												+ "LIMIT ?, 10";
	
	public static final String SELECT_ARTICLES_BY_KEYWORD = "SELECT * FROM `board_article` AS a "
															+ "JOIN `board_user` AS b ON a.uid = b.uid "
															+ "WHERE "
															+ "`parent`=0 AND (`title` LIKE ? OR `nick` LIKE ?) "
															+ "AND `cate`=? "
															+ "ORDER BY "
															+ "`no` DESC "
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
	
	public static final String SELECT_COMMENTS = "SELECT a.*, b.`nick` FROM `board_article` AS a "
												+ "JOIN `board_user` AS b "
												+ "ON a.uid = b.uid "
												+ " WHERE `parent`=? ORDER BY `no` ASC";
	
	public static final String SELECT_COMMENT_LATEST = "SELECT a.*, b.nick FROM `board_article` AS a "
													+ "JOIN `board_user` AS b USING (`uid`) "
													+ "WHERE `parent`!=0 ORDER BY `no` DESC LIMIT 1";
	
	public static final String SELECT_LATESTS = "(SELECT `no`, `title`, `rdate` FROM `board_article` WHERE `cate`=? ORDER BY `no` DESC LIMIT 5) "
												+ "UNION "
												+ "(SELECT `no`, `title`, `rdate` FROM `board_article` WHERE `cate`=? ORDER BY `no` DESC LIMIT 5) "
												+ "UNION "
												+ "(SELECT `no`, `title`, `rdate` FROM `board_article` WHERE `cate`=? ORDER BY `no` DESC LIMIT 5)";
	
	public static final String SELECT_LATEST = "SELECT `no`, `title`, `rdate` FROM `board_article` WHERE `cate`=? ORDER BY `no` DESC LIMIT 3";
	
	public static final String UPDATE_ARTICLE = "UPDATE `board_article` SET `title`=?, `content`=?, `rdate`=NOW() WHERE `no`=?";
	
	public static final String UPDATE_ARTICLE_HIT = "UPDATE `board_article` SET `hit` = `hit` + 1 WHERE `no`=?";
	
	public static final String UPDATE_ARTICLE_COMMENT_PLUS = "UPDATE `board_article` SET `comment` = `comment` + 1 WHERE `no`=?";
	
	public static final String UPDATE_ARTICLE_COMMENT_MINUS = "UPDATE `board_article` SET `comment` = `comment` - 1 WHERE `no`=?";
	
	public static final String UPDATE_FILE_DOWNLOAD = "UPDATE `board_file` set `download` = `download` + 1 WHERE `fno`=?";
	
	public static final String UPDATE_COMMENT = "UPDATE `board_article` SET "
												+ "`content`=?,"
												+ "`rdate`=NOW() "
												+ "WHERE `no`=?";
	
	public static final String DELETE_ARTICLE = "DELETE FROM `board_article` WHERE `no`=? OR `parent`=?";
	
	public static final String DELETE_COMMENT = "DELETE FROM `board_article` WHERE `no`=?";
	
	public static final String DELETE_FILE = "DELETE FROM `board_file` WHERE `parent`=?";
}
