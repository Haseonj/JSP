<%@page import="com.google.gson.JsonObject"%>
<%@page import="farmstory1.dao.UserDAO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	
	int result = UserDAO.getInstance().selectCountUid(uid);
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	String jsonData = json.toString();
	
	out.print(jsonData);
%>