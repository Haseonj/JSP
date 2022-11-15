<%@page import="kr.co.college.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.college.dao.CollegeDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 학생관리 
	// 학생정보만
	// 수강관리 검색 창 구현 학번 검색 시 해당 학번의 학생정보만 출력하도록 구현
	// 수강신청 구현 (학번 이름 신청강좌 => input 말고 다른걸로 구현  신청 닫기 버튼 추가)
	CollegeDAO dao = CollegeDAO.getInstance();
	List<CollegeBean> students = dao.selectStudent();
	

%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>학생관리</title>
	</head>
	<body>
		<h3>학생관리</h3>
		<a href="/College/lecture.jsp">강좌관리</a>
		<a href="/College/register.jsp">수강관리</a>
		<a href="/College/student.jsp">학생관리</a>
		<table border="1">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>학년</th>
				<th>주소</th>
			</tr>
			<% for(CollegeBean student : students){ %>
			<tr>
				<td><%= student.getStdNo() %></td>
				<td><%= student.getStdName() %></td>
				<td><%= student.getStdHp() %></td>
				<td><%= student.getStdYear() %></td>
				<td><%= student.getStdAddress() %></td>
			</tr>
			<% } %>
		</table>
	</body>
</html>
