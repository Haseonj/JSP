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
		<script src="./js/product.js"></script>
		<script>
		$(document).ready(function(){
			
			// 주문하기 화면 출력
			$(document).on('click', '#btnProduct', function(e){
				e.preventDefault();
				let prod = $(this).parent().parent().children();
				
				product(prod);
			});
			
			// 주문하기 정보 전송
			$(document).on('click', '#btnOrder', function(){
				
				// 입력한 정보 변수에 담기
				let prodNo = $('input[name=prodNo]').val();
				let orderCount = $('input[name=orderCount]').val();
				let uid = $('input[name=uid]').val();
				
				// Json변환
				let jsonData = {
						"prodNo":prodNo,
						"orderCount":orderCount,
						"uid":uid
				};
				
				// 변환된 정보 proc파일 전송
				$.ajax({
					url: './productProc.jsp',
					method: 'post',
					data: jsonData,
					dataType: 'json',
					success: function(data){
						if(data.result == 1){
							alert('주문완료!');
						}else{
							alert('주문실패!');
						}
					}
				});
				
			});
			
		});
		</script>
	</head>
	<body>
		<h3>상품목록</h3>
		<a href="./order.jsp">주문목록</a>
		<a href="./product.jsp">상품목록</a>
		<form action="#" method="post">
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
						<input type="submit" id="btnProduct" value="주문">
					</td>
				</tr>
				<% } %>
			</table>
			<h4></h4>
			<section></section>
		</form>
	</body>
</html>