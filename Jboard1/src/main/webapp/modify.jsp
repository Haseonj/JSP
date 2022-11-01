<%@page import="kr.co.jboard1.db.Sql"%>
<%@page import="kr.co.jboard1.bean.ArticleBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.co.jboard1.db.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String no = request.getParameter("no");

	ArticleBean article = null;
	
	try{
		Connection conn = DBCP.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(Sql.SELECT_MODIFY);
		
		if(rs.next()){
			article = new ArticleBean();
			article.setNo(rs.getInt(1));
			article.setParent(rs.getInt(2));
			article.setComment(rs.getInt(3));
			article.setCate(rs.getString(4));
			article.setTitle(rs.getString(5));
			article.setContent(rs.getString(6));
			article.setFile(rs.getInt(7));
			article.setHit(rs.getInt(8));
			article.setUid(rs.getString(9));
			article.setRegip(rs.getString(10));
			article.setRdate(rs.getString(11));
			article.setFname(rs.getString(12));
			article.setNick(rs.getString(13));
			article.setFno(rs.getInt(14));
			article.setPno(rs.getInt(15));
			article.setNewName(rs.getString(16));
			article.setOriName(rs.getString(17));
			article.setDownload(rs.getInt(18));
			
		}
		
		stmt.close();
		rs.close();
		conn.close();
				
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<%@ include file="./_header.jsp" %>
<main id="board">
    <section class="modify">
        <form action="#">
            <table border="0">
                <caption>글수정</caption>
                <tr>
                    <th>제목</th>
                    <td>
                        <input type="text" name="title" value="<%= article.getTitle() %>">
                    </td>
                </tr> 
                <tr>   
                    <th>내용</th>
                    <td>
                        <textarea name="content" value="<%= article.getContent() %>"></textarea>
                    </td>
                </tr>
                <tr>
                    <th>파일</th>    
                    <td>
                        <input type="file" name="file">
                    </td>
                </tr>
            </table>

            <div>
                <a href="/Jboard1/view.jsp" class="btn btnCancel">취소</a>
                <input type="submit" value="작성완료" class="btn btnComplete">
            </div>
        </form>
    </section>
</main>
<%@ include file="./_footer.jsp"%>