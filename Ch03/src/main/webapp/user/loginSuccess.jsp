<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
 	String id = (String) session.getAttribute("sessid");
	
	if(id == null){
		response.sendRedirect("../6_session.jsp");
		
		//처리종료
		return; 
		
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login Success</title>
	</head>
	<body>
		<h3>로그인 성공</h3>
		<p>
			<%= id %>님 반갑습니다.<br/>
			<a href="./logout.jsp">로그아웃</a>
		</p>
	</body>
</html>