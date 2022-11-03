<%@page import="kr.co.shop.bean.ShopBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.co.shop.db.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
List<ShopBean> shops = new ArrayList<>();
	try{
		Connection conn = DBCP.getConnection();
		String sql = "SELECT ";
			  sql += "`orderNo`, `name`, `prodName`, `orderCount`, `orderDate` ";
			  sql += "FROM `order` AS a ";
			  sql += "JOIN `customer` AS b ON a.orderId = b.uid ";
			  sql += "JOIN `product` AS c ON a.orderProduct = c.prodNo";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			ShopBean sb = new ShopBean();
			sb.setOrderNo(rs.getInt(1));
			sb.setName(rs.getString(2));
			sb.setProdName(rs.getString(3));
			sb.setOrderCount(rs.getInt(4));
			sb.setOrderDate(rs.getString(5));
			
			shops.add(sb);
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
		<title>order</title>
	</head>
	<body>
		<h3>주문목록</h3>
		<a href="./customer.jsp">고객목록</a>
		<a href="./product.jsp">상품목록</a>
		<table border="1">
			<tr>
				<th>주문번호</th>
				<th>주문자</th>
				<th>주문상품</th>
				<th>주문수량</th>
				<th>주문일</th>
			</tr>
			<%
			for(ShopBean sb : shops){
			%>
			<tr>
				<td><%= sb.getOrderNo() %></td>
				<td><%= sb.getName() %></td>
				<td><%= sb.getProdName() %></td>
				<td><%= sb.getOrderCount() %></td>
				<td><%= sb.getOrderDate() %></td>
			</tr>
			<% } %>
		</table>
	</body>
</html>