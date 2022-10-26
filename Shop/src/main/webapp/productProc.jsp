<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.shop.db.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String uid = request.getParameter("uid");
	String prodNo = request.getParameter("prodNo");
	String orderCount = request.getParameter("orderCount");
	
	
	int result = 0;
	Connection conn = null;
	
	try{
		conn = DBCP.getConnection();
		
		// 자동 커밋 해제 후 트랜잭션 시작
		conn.setAutoCommit(false);		
		
		// 주문 정보를 받아 주문목록으로 올려주는 쿼리문
		String sql = "INSERT INTO `order` SET `orderId`=?, `orderProduct`=?, `orderCount`=?, `orderDate`=NOW()";
		
		// 주문한 갯수 만큼 재고량을 빼주는 쿼리문
		String update_sql = "UPDATE `product` SET `stock`=`stock`-(SELECT `orderCount` FROM `order` ORDER BY orderDate DESC LIMIT 1) WHERE `prodNo`=?";						 
		
		PreparedStatement psmt1 = conn.prepareStatement(sql);
		psmt1.setString(1, uid);
		psmt1.setString(2, prodNo);
		psmt1.setString(3, orderCount);
		
		PreparedStatement psmt2 = conn.prepareStatement(update_sql);
		psmt2.setString(1, prodNo);
		
		
		result = psmt1.executeUpdate();
		psmt2.executeUpdate();
		
		
		if(result != 0){
			conn.commit();	
		}
		
		
		psmt1.close();
		conn.close();
	}catch(Exception e){
		conn.rollback();
		e.printStackTrace();
	}
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	String jsonData = json.toString();
	
	out.print(jsonData);
%>