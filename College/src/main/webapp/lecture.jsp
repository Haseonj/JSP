<%@page import="kr.co.college.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.college.dao.CollegeDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
	
	CollegeDAO dao = CollegeDAO.getInstance();
	List<CollegeBean> lectures = dao.selectLecuter();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>college::수강관리</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(function(){
				// 강좌등록 창 열기
				$('.btnInsert').click(function(){
					$('section').show();
				});
				
				// 강좌등록 창 닫기
				$('.btnClose').click(function(){
					$('section').hide();
				});
				
				// 강좌등록
				$('input[type=submit]').click(function(){
					
					let lecNo = $('input[name=lecNo]').val();
					let lecName = $('input[name=lecName]').val();
					let lecCredit = $('input[name=lecCredit]').val();
					let lecTime = $('input[name=lecTime]').val();
					let lecClass = $('input[name=lecClass]').val();
					
					let jsonData = {
						"lecNo": lecNo,
						"lecName": lecName,
						"lecCredit": lecCredit,
						"lecTime": lecTime,
						"lecClass": lecClass
						
					};
					
					$.post('./proc/lectureProc.jsp', jsonData, function(data){
						if(data.result == 1){
							alert('강좌가 추가되었습니다.');
						}else{
							alert('다시 한 번 확인 해 주십시오.');
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<h3>수강관리</h3>
		<a href="/College/lecture.jsp">강좌관리</a>
		<a href="/College/register.jsp">수강관리</a>
		<a href="/College/student.jsp">학생관리</a>
		<h4>강좌현황</h4>
		<table border="1">
			<tr>
				<th>번호</th>
				<th>강좌명</th>
				<th>학점</th>
				<th>시간</th>
				<th>강의장</th>
			</tr>
			<%
			for(CollegeBean lecture : lectures){
			%>
			<tr>
				<td><%= lecture.getLecNo() %></td>
				<td><%= lecture.getLecName() %></td>
				<td><%= lecture.getLecCredit() %></td>
				<td><%= lecture.getLecTime() %></td>
				<td><%= lecture.getLecClass() %></td>
			</tr>
			<% } %>
			<button class="btnInsert">등록</button>
		</table>
		<section style="display:none;">
		<h4>강좌등록</h4>
		<table border="1">
			<tr>
				<td>번호</td>
				<td><input type="text" name="lecNo"></td>
			</tr>
			<tr>
				<td>강좌명</td>
				<td><input type="text" name="lecName"></td>
			</tr>
			<tr>
				<td>학점</td>
				<td><input type="text" name="lecCredit"></td>
			</tr>
			<tr>
				<td>시간</td>
				<td><input type="text" name="lecTime"></td>
			</tr>
			<tr>
				<td>강의장</td>
				<td><input type="text" name="lecClass"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="추가"></td>
			</tr>
			<button class="btnClose">닫기</button>
		</table>
		</section>
	</body>
</html>