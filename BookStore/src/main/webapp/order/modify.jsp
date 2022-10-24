<%@page import="bean.OrderBean"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="kr.co.jboard1.db.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	
	String orderId = request.getParameter("orderId");
	// 데이터베이스 작업
	OrderBean ob = null;
	
	try{
		Connection conn = DBCP.getConnection();
		String sql = "SELECT * FROM `order` WHERE `orderId`=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, orderId);
		ResultSet rs = psmt.executeQuery();
		
		if(rs.next()){
			ob = new OrderBean();
			ob.setOrderId(rs.getInt(1));
			ob.setCustId(rs.getInt(2));
			ob.setBookId(rs.getInt(3));
			ob.setSalePrice(rs.getInt(4));
			ob.setOrderDate(rs.getString(5));
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>order::modify</title>
	</head>
	<body>
		<h3>주문수정</h3>
		<a href="../index.jsp">처음으로</a>
		<a href="./list.jsp">주문목록</a>
		<form action="./modifyProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>주문번호</td>
					<td><input type="text" name="orderId" value="<%= ob.getOrderId() %>"></td>
				</tr>
				<tr>
					<td>고객번호</td>
					<td><input type="text" name="custId" value="<%= ob.getCustId() %>"></td>
				</tr>
				<tr>
					<td>책번호</td>
					<td><input type="text" name="bookId" value="<%= ob.getBookId() %>"></td>
				</tr>
				<tr>
					<td>할인가격</td>
					<td><input type="text" name="salePrice" value="<%= ob.getSalePrice() %>"></td>
				</tr>
				<tr>
					<td>주문날짜</td>
					<td><input type="date" name="orderDate" value=<%= ob.getOrderDate() %>></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="등록하기"></td>
				</tr>
			</table>
		</form>
	</body>
</html>