<%@page import="kr.co.farmstory1.bean.ArticleBean"%>
<%@page import="kr.co.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String no = request.getParameter("no");
	String pg = request.getParameter("pg");
	
	pageContext.include("/Board/_"+group+".jsp");
	
	ArticleBean ab = ArticleDAO.getinstance().selectArticle(no);
%>
	        <main id="board">
	            <section class="modify">
	                <form action="/Farmstory1/Board/proc/modifyProc.jsp?no=<%= ab.getNo() %>" method="GET">
	                	<input type="hidden" name="no" value="<%= no %>">
	                	<input type="hidden" name="pg" value="<%= pg %>">
	                	<input type="hidden" name="group" value="<%= group %>">
	                	<input type="hidden" name="cate" value="<%= cate %>">
	                    <table border="0">
	                        <caption>글수정</caption>
	                        <tr>
	                            <th>제목</th>
	                            <td>
	                                <input type="text" name="title" value="<%= ab.getTitle() %>">
	                            </td>
	                        </tr> 
	                        <tr>   
	                            <th>내용</th>
	                            <td>
	                                <textarea name="content"><%= ab.getContent() %></textarea>
	                            </td>
	                        </tr>
	                    </table>
	
	                    <div>
	                        <a href="./view.jsp?group=<%= group %>&cate=<%= cate %>&no=<%= ab.getNo() %>&pg=<%= pg %>" class="btn btnCancel">취소</a>
	                        <input type="submit" value="작성완료" class="btn btnComplete">
	                    </div>
	                </form>
	            </section>
	        </main>
		</article>
    </section>
</div>
<%@ include file="/_footer.jsp" %>