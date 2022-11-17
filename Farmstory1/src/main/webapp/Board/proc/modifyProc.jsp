<%@page import="kr.co.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String pg = request.getParameter("pg");
	
	
	
	ArticleDAO.getinstance().updateArticle(no, title, content);
	
	response.sendRedirect("/Farmstory1/Board/view.jsp?no="+no+"&pg="+pg+"&group="+group+"&cate="+cate);
	
%>