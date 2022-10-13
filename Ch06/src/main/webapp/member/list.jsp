<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="bean.MemberBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터베이스 작업
	String host = "jdbc:mysql://127.0.0.1:3306/java2db";
	String user = "root";
	String pass = "1234";
	
	List<MemberBean> members = null;
	
	try{
		// 1단계
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2단계
		Connection conn = DriverManager.getConnection(host, user, pass);
		// 3단계
		Statement stmt = conn.createStatement();
		// 4단계
		ResultSet rs = stmt.executeQuery("SELECT * FROM `member`");
		// 5단계
		members = new ArrayList<>(); 
		
		while(rs.next()){
			
			MemberBean mb = new MemberBean();
			mb.setUid(rs.getString(1));
			mb.setName(rs.getString(2));
			mb.setHp(rs.getString(3));
			mb.setPos(rs.getString(4));
			mb.setDep(rs.getString(5));
			mb.setDate(rs.getString(6));
			
			members.add(mb);
		}
		// 6단계
		rs.close();
		stmt.close();
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>member::list</title>
	</head>
	<body>
		<h3>member 목록</h3>
		<a href="../1_JDBCTest.jsp">처음으로</a>
		<a href="./register.jsp">member 등록</a>
		
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>직급</th>
				<th>부서</th>
				<th>입사일</th>
				<th>관리</th>
			</tr>
			<% for(MemberBean mb : members){ %>
			<tr>
				<th><%= mb.getUid() %></th>
				<th><%= mb.getName() %></th>
				<th><%= mb.getHp() %></th>
				<th><%= mb.getPos() %></th>
				<th><%= mb.getDep() %></th>
				<th><%= mb.getDate() %></th>
				<th>
					<a href="./modify.jsp?uid=<%= mb.getUid() %>">수정</a>
					<a href="#">삭제</a>
				</th>
			</tr>
			<% } %>
		</table>
	</body>
</html>