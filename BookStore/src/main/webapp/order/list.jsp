<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="bean.OrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.jboard1.db.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%	
	List<OrderBean> orders = new ArrayList<>();
	
	try{
		Connection conn = DBCP.getConnection();
		String sql = "SELECT * FROM `order`";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			OrderBean ob = new OrderBean();
			ob.setOrderId(rs.getInt(1));
			ob.setCustId(rs.getInt(2));
			ob.setBookId(rs.getInt(3));
			ob.setSalePrice(rs.getInt(4));
			ob.setOrderDate(rs.getString(5));
			
			orders.add(ob);
		}
		
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
		<title>order::list</title>
	</head>
	<body>
		<h3>주문목록</h3>
		<a href="../index.jsp">처음으로</a>
		<a href="./register.jsp">주문등록</a>
		<table border="1">
			<tr>
				<th>주문번호</th>
				<th>고객번호</th>
				<th>책번호</th>
				<th>할인가격</th>
				<th>주문날짜</th>
				<th>관리</th>
			</tr>
			<% for(OrderBean ob : orders){ %>
			<tr>
				<td><%= ob.getOrderId() %></td>
				<td><%= ob.getCustId() %></td>
				<td><%= ob.getBookId() %></td>
				<td><%= ob.getSalePrice() %></td>
				<td><%= ob.getOrderDate() %></td>
				<td>
					<a href="./modify.jsp?orderId=<%= ob.getOrderId() %>">수정</a>
					<a href="./delete.jsp?orderId=<%= ob.getOrderId() %>">삭제</a>
				</td>
			</tr>
			<% } %>
		</table>
	</body>
</html>