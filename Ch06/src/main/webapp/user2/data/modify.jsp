<%@page import="config.DBCP"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String age = request.getParameter("age");
	String uid = request.getParameter("uid");
	
	// 데이터베이스 작업
	int result = 0;
	try{
		Connection conn = DBCP.getConnection();
		PreparedStatement psmt = conn.prepareStatement("UPDATE `user2` SET `name`=?, `hp`=?, `age`=? WHERE `uid`=?");
		psmt.setString(1, name);
		psmt.setString(2, hp);
		psmt.setString(3, age);
		psmt.setString(4, uid);
		result = psmt.executeUpdate();
		
		
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// JSON 변환
		   
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	String jsonData = json.toString();
	
	out.print(jsonData);
	
%>