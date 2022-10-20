<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>order::register</title>
	</head>
	<body>
		<h3>주문등록</h3>
		<a href="../index.jsp">처음으로</a>
		<a href="./list.jsp">주문목록</a>
		<form action="./registerProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>주문번호</td>
					<td><input type="text" name="orderId" placeholder="주문번호입력"></td>
				</tr>
				<tr>
					<td>고객번호</td>
					<td><input type="text" name="custId" placeholder="고객번호입력"></td>
				</tr>
				<tr>
					<td>책번호</td>
					<td><input type="text" name="bookId" placeholder="책번호입력"></td>
				</tr>
				<tr>
					<td>할인가격</td>
					<td><input type="text" name="salePrice" placeholder="할인가격입력"></td>
				</tr>
				<tr>
					<td>주문날짜</td>
					<td><input type="date" name="orderDate"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="등록하기"></td>
				</tr>
			</table>
		</form>
	</body>
</html>