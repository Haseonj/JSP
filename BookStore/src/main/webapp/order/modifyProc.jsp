<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.jboard1.db.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	
	String custId = request.getParameter("custId");
	String bookId = request.getParameter("bookId");
	String salePrice = request.getParameter("salePrice");
	String orderDate = request.getParameter("orderDate");
	String orderId = request.getParameter("orderId");
	
	// 데이터베이스 작업
	
	try{
		Connection conn = DBCP.getConnection();
		String sql = "UPDATE `order` SET `custId`=?, `bookId`=?, `salePrice`=?, `orderDate`=?";
			  sql += "WHERE `orderId`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, custId);
		psmt.setString(2, bookId);
		psmt.setString(3, salePrice);
		psmt.setString(4, orderDate);
		psmt.setString(5, orderId);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// 리다이렉트
	response.sendRedirect("./list.jsp");
	
%>