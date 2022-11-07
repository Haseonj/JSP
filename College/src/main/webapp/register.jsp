<%@page import="kr.co.college.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.college.dao.CollegeDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	CollegeDAO dao = CollegeDAO.getInstance();

	List<CollegeBean> colleges = dao.selectRegister();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>수강관리</title>
	</head>
	<body>
		<h3>수강관리</h3>
		<a href="#">강좌관리</a>
		<a href="#">수강관리</a>
		<a href="#">학생관리</a>
		<table border="1">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>강좌명</th>
				<th>강좌코드</th>
				<th>중간시험</th>
				<th>기말시험</th>
				<th>총점</th>
				<th>등급</th>
			</tr>
			<% for(CollegeBean college : colleges){ %>
			<tr>
				<td><%= college.getRegStdNo() %></td>
				<td><%= college.getRegLecNo() %></td>
				<td><%= college.getRegMidScore() %></td>
				<td><%= college.getRegFinalScore() %></td>
				<td><%= college.getRegTotalScore() %></td>
				<td><%= college.getRegGrade() %></td>
			</tr>
			<% } %>
		</table>
	</body>
</html>