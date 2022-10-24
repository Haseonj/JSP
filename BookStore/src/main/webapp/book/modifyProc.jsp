<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.jboard1.db.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");

	String bookName = request.getParameter("bookName");
	String publisher= request.getParameter("publisher");
	String price= request.getParameter("price");
	String bookId= request.getParameter("bookId");
	
	// 데이터베이스 입력
	try{
		Connection conn = DBCP.getConnection();
		String sql = "UPDATE `book` SET `bookName`=?, `publisher`=?, `price`=?";
			  sql += "WHERE `bookId`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, bookName);
		psmt.setString(2, publisher);
		psmt.setString(3, price);
		psmt.setString(4, bookId);
		
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// 리다이렉트
	response.sendRedirect("./list.jsp");
%>