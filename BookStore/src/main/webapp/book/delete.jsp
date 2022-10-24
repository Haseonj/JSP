<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.jboard1.db.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	
	String bookId = request.getParameter("bookId");
	// 데이터베이스 작업
	try{
		Connection conn = DBCP.getConnection();
		String sql = "DELETE FROM `book` WHERE `bookId`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, bookId);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	// 리다이렉트
	response.sendRedirect("./list.jsp");
%>