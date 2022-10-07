<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>8_scopeTest</title>
		<!-- 
			날짜 : 2022/10/07
			이름 : 하선주
			내용 : JSP 내장객체 영역 실습하기
			
			내장객체영역
			 - JSP 내장객체가 저장되는 메모리의 유효기간
			 - 웹은 페이지가 모여 하나의 요청을	처리, 요청이 모여 하나의 세션을 다시 세션이 모여 웹애플리케이션을 구성
		 -->
	</head>
	<body>
		<h3>내장객체 영역</h3>
		
		<%
			// 내장객체영역 값 설정
			pageContext.setAttribute("name", "김유신"); // 해당 페이지내에서만 김유신 참조 가능
			request.setAttribute("name", "김춘추"); // 해당 페이지를 요청하는 페이지에 참조 가능 (포워딩 하는 페이지 까지 가능)
			session.setAttribute("name", "장보고"); // 해당 페이지를 요청하는 클라이언트에 저장
			application.setAttribute("name", "이순신"); // 해당 프로젝트를 실행하는 서버(톰캣)에 저장
		%>
		
		<h4>내장객체 값 확인</h4>
		<p>
			pageContext 확인 : <%= pageContext.getAttribute("name") %><br/>
			request 확인 : <%= request.getAttribute("name") %><br/>
			session 확인 : <%= session.getAttribute("name") %><br/>
			application 확인 : <%= application.getAttribute("name") %><br/>
		</p>
		
		<%
			pageContext.forward("./proc/scopeResult.jsp");
		%>
		
	</body>
</html>