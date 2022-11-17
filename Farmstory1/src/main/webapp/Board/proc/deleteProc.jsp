<%@page import="kr.co.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String no 	 = request.getParameter("no");
	String pg 	 = request.getParameter("pg");
	String group = request.getParameter("group");
	String cate  = request.getParameter("cate");
	
	ArticleDAO.getinstance().deleteArticle(no);
	
	response.sendRedirect("/Farmstory1/Board/list.jsp?group="+group+"&cate="+cate+"&pg="+pg);
%>