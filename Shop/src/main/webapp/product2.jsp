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
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `product`");
		
		while(rs.next()){
			ShopBean sb = new ShopBean();
			sb.setProdNo(rs.getInt(1));
			sb.setProdName(rs.getString(2));
			sb.setStock(rs.getInt(3));
			sb.setPrice(rs.getInt(4));
			sb.setCompany(rs.getString(5));
			
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
		<title>product</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>		
			$(function(){
				$('.btnOrder').click(function(){
					let prodNo = $(this).val();
					$('section').show().find('input[name=prodNo]').val(prodNo);
				});
				
				$('.btnClose').click(function(){
					$('section').hide();
				});
				
				$('input[type=submit]').click(function(){
					
					let prodNo 		= $('input[name=prodNo]').val;
					let prodCount 	= $('input[name=prodCount]').val;
					let prodOrderer = $('input[name=prodOrderer]').val;
					
					let jsonData = {
							"prodNo": prodNo,
							"prodCount": prodCount,
							"prodOrderer": prodOrderer
					};
					
					$.post('./registerProc.jsp', 'jsonData', function(data){
						
						if(data.result > 0){
							alert('주문완료!');
						}else{
							alert('주문실패!');
						}
						
					});
					
					
				});;
			});
		</script>
	</head>
	<body>
		<h3>상품목록</h3>
		<a href="./order.jsp">주문목록</a>
		<a href="./product.jsp">상품목록</a>
			<table border="1">
				<tr>
					<th>상품번호</th>
					<th>상품명</th>
					<th>재고량</th>
					<th>가격</th>
					<th>제조사</th>
					<th>주문</th>
				</tr>
				<% for(ShopBean sb : shops){ %>
				<tr>
					<td><%= sb.getProdNo() %></td>
					<td><%= sb.getProdName() %></td>
					<td><%= sb.getStock() %></td>
					<td><%= sb.getPrice() %></td>
					<td><%= sb.getCompany() %></td>
					<td>
						<button class="btnOrder" value="<%= sb.getProdNo() %>">주문</button>
					</td>
				</tr>
				<% } %>
			</table>
		
			<section style="display:none;">
				<h4>주문하기</h4>
				<table border="1">
					<tr>
						<td>상품번호</td>
						<td><input type="text" name="prodNo" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>수량</td>
						<td><input type="text" name="prodCount"/></td>
					</tr>
					<tr>
						<td>주문자</td>
						<td><input type="text" name="prodOrderer"/></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input type="submit" value="주문하기"/></td>
					</tr>
				</table>
				<button class="btnClose">닫기</button>
			</section>
	</body>
</html>