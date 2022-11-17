<%@page import="kr.co.farmstory1.bean.ArticleBean"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
<%
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String pg = request.getParameter("pg");
	
	int limitStart = 0;
	int currentPage = 1;
	int total = 0;
	int lastPageNum = 0;
	int pageGroupCurrent = 1;
	int pageGroupStart = 1;
	int pageGroupEnd = 0;
	int pageStartNum = 0;
	
	
	ArticleDAO dao = ArticleDAO.getinstance();
	
	total = dao.selectCountTotal(cate);
	
	if(total % 10 == 0){
		lastPageNum = (total / 10);
	}else{
		lastPageNum = (total / 10) + 1;
	}
	
	if(pg != null){
		currentPage = Integer.parseInt(pg);
	}
	
	limitStart = (currentPage - 1) * 10;
	
	pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
	pageGroupEnd = pageGroupCurrent * 10;
	
	if(pageGroupEnd > lastPageNum){
		pageGroupEnd = lastPageNum;
	}
	
	pageStartNum = total - limitStart;
	
	List<ArticleBean> articles = dao.selectArticles(cate, limitStart);
	
	pageContext.include("/Board/_"+group+".jsp");
%>
        <main id="board">
            <section class="list">
                
                <table border="0">
                    <caption>글목록</caption>
                    <tbody>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>글쓴이</th>
                            <th>날짜</th>
                            <th>조회</th>
                        </tr>
                        <% for(ArticleBean ab : articles){ %>
                        <tr>
                            <td><%= pageStartNum-- %></td>
                            <td><a href="./view.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= currentPage %>&no=<%= ab.getNo() %>"><%= ab.getTitle() %>[<%= ab.getComment() %>]</a></td>
                            <td><%= ab.getNick() %></td>
                            <td><%= ab.getRdate().substring(2, 10) %></td>
                            <td><%= ab.getHit() %></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <!-- 페이지 네비게이션 -->
                <div class="page">
                	<% if(pageGroupStart > 1){ %>
                    <a href="/Farmstory1/Board/list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= pageGroupStart - 1 %>" class="prev">이전</a>
                    <% } %>
                    <% for(int i = pageGroupStart; i <= pageGroupEnd; i++){ %>
                    <a href="/Farmstory1/Board/list.jsp?group=<%= group %>&cate=<%= cate %>&pg=<%= i %>" class="num <%= (currentPage == i) ? "current" : "off" %>"><%= i %></a>
                    <% } %>
                    <% if(pageGroupEnd < lastPageNum){ %>
                    <a href="/Farmstory1/Board/list.jsp?pg=<%= pageGroupEnd +1 %>" class="next">다음</a>
                    <% } %>
                </div>
                <!-- 글쓰기 버튼 -->
                <a href="./write.jsp?group=<%= group %>&cate=<%= cate %>" class="btn btnWrite">글쓰기</a>
            </section>
        </main>
		</article>
    </section>
</div>
<%@ include file="/_footer.jsp" %>