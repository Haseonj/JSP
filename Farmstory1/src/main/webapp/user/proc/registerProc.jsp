<%@page import="kr.co.farmstory1.bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.farmstory1.dao.UserDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String uid 	 = request.getParameter("uid");
	String pass  = request.getParameter("pass");
	String name  = request.getParameter("name");
	String nick  = request.getParameter("nick");
	String email = request.getParameter("email");
	String hp 	 = request.getParameter("hp");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String regip = request.getRemoteAddr();
	
	UserBean user = new UserBean();
	user.setUid(uid);
	user.setPass(pass);
	user.setName(name);
	user.setNick(nick);
	user.setEmail(email);
	user.setHp(hp);
	user.setAddr1(addr1);
	user.setAddr2(addr2);
	user.setRegip(regip);
	
	UserDAO.getInstance().insertUser(user);
	
	response.sendRedirect("/Farmstory1/user/login.jsp");
%>