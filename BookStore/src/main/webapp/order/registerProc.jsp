<%@page import="java.sql.PreparedStatement"%>
<%@page import="config.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	
	String orderId = request.getParameter("orderId");
	String custId = request.getParameter("custId");
	String bookId = request.getParameter("bookId");
	String salePrice = request.getParameter("salePrice");
	String orderDate = request.getParameter("orderDate");
	
	// 데이터베이스 작업
	try{
		Connection conn = DBCP.getConnection();
		String sql = "INSERT INTO `order` VALUES(?,?,?,?,?)";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, orderId);
		psmt.setString(2, custId);
		psmt.setString(3, bookId);
		psmt.setString(4, salePrice);
		psmt.setString(5, orderDate);
		psmt.executeUpdate();
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// 리다이렉트
	response.sendRedirect("./list.jsp");
%>