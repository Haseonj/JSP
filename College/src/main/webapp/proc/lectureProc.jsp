<%@page import="com.google.gson.JsonObject"%>
<%@page import="kr.co.college.bean.CollegeBean"%>
<%@page import="kr.co.college.dao.CollegeDAO"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
	String lecNo = request.getParameter("lecNo");
	String lecName = request.getParameter("lecName");
	String lecCredit = request.getParameter("lecCredit");
	String lecTime = request.getParameter("lecTime");
	String lecClass = request.getParameter("lecClass");
	
	CollegeBean lecture = new CollegeBean();
	lecture.setLecNo(lecNo);
	lecture.setLecName(lecName);
	lecture.setLecCredit(lecCredit);
	lecture.setLecTime(lecTime);
	lecture.setLecClass(lecClass);
	
	int result = CollegeDAO.getInstance().insertLecture(lecture);
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	String jsonData = json.toString();
	
	out.print(jsonData);
%>