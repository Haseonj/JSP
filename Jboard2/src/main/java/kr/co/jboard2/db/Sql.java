package kr.co.jboard2.db;

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

	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(`email`) FROM `board_user` WHERE `email`=?";
	
	public static final String SELECT_UESR_FOR_FIND_ID = "select * from `board_user` where `name`=? and `email`=?";
	
	public static final String SELECT_UESR_FOR_FIND_PW = "select * from `board_user` where `uid`=? and `email`=?";
	
	public static final String SELECT_USER_BY_SESSID = "SELECT * FROM `board_user` WHERE `sessId`=? AND `sessLimitDate` > NOW()";
	
	public static final String UPDATE_USER_PASSWORD = "update `board_user` set `pass`=SHA2(?, 256) where `uid`=?";
	
	public static final String UPDATE_USER_FOR_SESSION = "UPDATE `board_user` SET `sessId`=?, `sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) WHERE `uid`=?";
	
	public static final String UPDATE_USER_FOR_SESS_LIMIT_DATE = "UPDATE `board_user` SET `sessLimitDate` = DATE_ADD(NOW(), INTERVAL 3 DAY) WHERE `sessId`=?";
	
	public static final String UPDATE_USER_FOR_SESSION_OUT = "UPDATE `board_user` SET `sessId`=NULL, `sessLimitDate`=NULL WHERE `uid`=?";
	
	public static final String SELECT_USER_BY_INFO = "SELECT count('uid') FROM `board_user` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	
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
	
	public static final String INSERT_COMMENT = "INSERT INTO `board_article` SET "
												+ "`parent`=?,"
												+ "`content`=?,"
												+ "`uid`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";

	
	public static final String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `board_article`";
	
	// parent = 0 ??? ????????? ????????? ?????? ?????? ????????? ???????????? ?????????
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(`no`) FROM `board_article` WHERE `parent`=0";
	
	public static final String SELECT_COUNT_TOTAL_FOR_SEARCH = "SELECT COUNT(`no`) FROM `board_article` AS a "
																+ "JOIN `board_user` AS b ON a.uid = b.uid "
																+ "WHERE `parent`=0 AND (`title` LIKE ? OR `nick` LIKE ?)";
	
	// ????????? ????????? ?????? ?????????????????? ??????
	public static final String SELECT_ARTICLES = "SELECT a.*, b.nick FROM `board_article` AS a "
												+ "JOIN `board_user` AS b "
												+ "ON a.uid = b.uid "
												+ "WHERE `parent`=0 "
												+ "ORDER BY `no` DESC "
												+ "LIMIT ?, 10";
	
	public static final String SELECT_ARTICLES_BY_KEYWORD = "SELECT * FROM `board_article` AS a "
															+ "JOIN `board_user` AS b ON a.uid = b.uid "
															+ "WHERE "
															+ "`parent`=0 AND (`title` LIKE ? OR `nick` LIKE ?) "
															+ "ORDER BY "
															+ "`no` DESC "
															+ "LIMIT ?, 10";
															
	
	// ?????? ????????? ????????? ????????? ?????? ?????? ????????? ???????????? ????????? ????????? ????????? inner join??? a ??? b??? ???????????? ???????????? ??????
	// ?????? ????????? ?????? ????????? ???????????? ???????????? ?????????.
	// ?????????, left join??? ????????? a??? ?????? ????????? ???????????? ?????? ???????????? ????????? ?????? ????????? ???????????? ????????? ???????????????.
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
	
	public static final String UPDATE_USER = "update `board_user` set "
											+ "`name`=?, "
											+ "`nick`=?, "
											+ "`email`=?, "
											+ "`hp`=?, "
											+ "`zip`=?, "
											+ "`addr1`=?, "
											+ "`addr2`=? "
											+ "where `uid`=?";
	
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
	
	public static final String DELETE_USER = "UPDATE `board_user` SET "
											+ "`grade`= 0, "
											+ "`wdate`= NOW() "
											+ "WHERE `uid`= ?";
}
