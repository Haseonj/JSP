<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.shop.db.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String uid = request.getParameter("uid");
	String prodNo = request.getParameter("prodNo");
	String orderCount = request.getParameter("orderCount");
	
	
	int result = 0;
	
	try{
		Connection conn = DBCP.getConnection();
		String sql = "INSERT INTO `order` SET `orderId`=?, `orderProduct`=?, `orderCount`=?, `orderDate`=NOW()";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, uid);
		psmt.setString(2, prodNo);
		psmt.setString(3, orderCount);
		result = psmt.executeUpdate();
		
		psmt.close();
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	String jsonData = json.toString();
	
	out.print(jsonData);
%>