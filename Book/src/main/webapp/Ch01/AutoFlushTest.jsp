<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" buffer="1kb" autoFlush="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>page 지시어 - buffer, autoFlush 속성</title>
	</head>
	<body>
		<%
			for(int i = 1; i <= 100; i++) {
				out.println("abcde12345");
			} // 출력 내용이 속성으로 설정한 1kb를 초과하여 발생한 에러
		%>
	</body>
</html>