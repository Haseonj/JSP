<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./_header.jsp"/>
        <main id="board">
            <section class="list">                
                <form action="#">
                    <input type="text" name="search" placeholder="제목 키워드, 글쓴이 검색">
                    <input type="submit" value="검색">
                </form>
                
                <table border="0">
                    <caption>글목록</caption>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>날짜</th>
                        <th>조회</th>
                    </tr>
                    <c:forEach var="vo" items="${articles}">                    
                    <tr>
                        <td>${vo.no}</td>
                        <td><a href="./view.html">${vo.title}[${vo.hit}]</a></td>
                        <td>${vo.name}</td>
                        <td>${vo.rdate}</td>
                        <td>12</td>
                    </tr>
                    </c:forEach>
                </table>

                <div class="page">
                	<c:choose>
                		<c:when test="${pageGroupStart gt 1}">
                    		<a href="/Jboard2/list.do?pg=${pageGroupStart-1}" class="prev">이전</a>
                    	</c:when>
                    </c:choose>
                    <c:forEach var="i" begin="${pageGroupStart}" end="${i le pageGroupEnd}">
                    	<a href="/Jboard2/list.do?pg=${i}" class="num ${currentPage == i ? current : off }">${i}</a>
                    </c:forEach>
                    <c:choose>
                    	<c:when test="${pageGroupEnd lt lastPageNum}">
                    		<a href="/Jboard2/list.do?pg=${pageGroupEnd + 1}" class="next">다음</a>
                    	</c:when>
                    </c:choose>
                </div>

                <a href="./write.html" class="btn btnWrite">글쓰기</a>
                
            </section>
        </main>
<jsp:include page="./_footer.jsp"/>