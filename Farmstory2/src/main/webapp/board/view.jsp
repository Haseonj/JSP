<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/_header.jsp"/>
<jsp:include page="./_${group}.jsp"/>
	        <main id="board">
	            <section class="view">
	                
	                    <table border="0">
	                        <caption>글보기</caption>
	                        <tr>
	                            <th>제목</th>
	                            <td><input type="text" name="title" readonly value="${vo.title}"></td>
	                        </tr>
	                        <c:if test="${vo.file > 0}">
	                        <tr>
	                            <th>파일</th>    
	                            <td>
	                                <a href="/Farmstory1/Board/download.do?parent=${no}">${vo.oriName}</a>&nbsp;<span>${vo.download}</span>회 다운로드
	                            </td>
	                        </tr>
	                        </c:if>
	                        <tr>   
	                            <th>내용</th>
	                            <td>
	                                <textarea name="content" readonly>${vo.content}</textarea>
	                            </td>
	                        </tr>
	                    </table>
	
	                    <div>
	                        <a href="/Farmstory2/board/delete.do?no=${no}&pg=${pg}&group=${group}&cate=${cate}" class="btn btnRemove">삭제</a>
	                        <a href="/Farmstory2/board/modify.do?group=${group}&cate=${cate}&pg=${pg}&no=${no}" class="btn btnModify">수정</a>
	                        <a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}" class="btn btnList">목록</a>
	
	                    </div>
	                    <!-- 댓글 목록 -->
	                    <section class="commentList">
	                        <h3>댓글목록</h3>
	                        <article>
	                            <span class="nick">길동이</span>
	                            <span class="date">20-05-20</span>
	                            <p class="content">댓글 샘플 입니다.</p>
	                            <div>
	                                <a href="#" class="remove">삭제</a>
	                                <a href="#" class="modify">수정</a>
	                            </div>
	                        </article>
	
	                        <p class="empty">등록된 댓글이 없습니다.</p>
	
	
	                    </section>
	                    <!-- 댓글 쓰기 -->
	                    <section class="commentForm">
	                        <h3>댓글쓰기</h3>
	
	                        <form action="#" method="post">
	                        	<input type="hidden" name="uid" value="${cate}">
	                        	<input type="hidden" name="no" value="${no}">
	                            <textarea name="content" placeholder="댓글내용 입력"></textarea>
	                            <div>
	                                <a href="#" class="btn btnCancel">취소</a>
	                                <input type="submit" value="작성완료" class="btn btnComplete">
	                            </div>
	                        </form>
	
	                    </section>
	                
	            </section>
	        </main>
        </article>
    </section>
</div>
<jsp:include page="/_footer.jsp"/>