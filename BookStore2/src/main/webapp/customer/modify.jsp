<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Customer::Modify</title>
	</head>
	<body>
		<h3>도서수정</h3>
		<a href="/BookStore2/index.jsp">처음으로</a>
		<a href="/BookStore2/customer/list.do">고개목록</a>
		
		<form action="/BookStore2/customer/modify.do" method="post">
			<table border="1">
				<tr>
					<td>고객번호</td>
					<td><input type="text" name="custId" readonly value="${vo.custId}"></td>
				</tr>
				<tr>
					<td>고객명</td>
					<td><input type="text" name="name" value="${vo.name}"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address" value="${vo.address}"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="phone" value="${vo.phone}"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
	</body>
</html>