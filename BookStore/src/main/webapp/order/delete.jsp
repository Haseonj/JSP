<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 저장
	request.setCharacterEncoding("utf-8");
	
	String orderId = request.getParameter("orderId");
	
	// 데이터베이스 작업
	
	try{
		Connection conn = DBCP.getConnection();
		String sql = "DELETE FROM `order` WHERE `orderId`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, orderId);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// 리다이렉트
	response.sendRedirect("./list.jsp");
	
%>