<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/_header.jsp"/>
<jsp:include page="./_${group}.jsp"/>
	        <main id="board">
	            <section class="modify">
	                <form action="/Farmstory2/board/modify.do" method="post">
	                	<input type="hidden" name="no" value="${vo.no}">
	                	<input type="hidden" name="pg" value="${pg}">
	                	<input type="hidden" name="group" value="${group}">
	                	<input type="hidden" name="cate" value="${cate}">
	                    <table border="0">
	                        <caption>글수정</caption>
	                        <tr>
	                            <th>제목</th>
	                            <td>
	                                <input type="text" name="title" value="${vo.title}">
	                            </td>
	                        </tr> 
	                        <tr>   
	                            <th>내용</th>
	                            <td>
	                                <textarea name="content">${vo.content}</textarea>
	                            </td>
	                        </tr>
	                    </table>
	
	                    <div>
	                        <a href="/Farmstory2/board/view.do?group=${group}&cate=${cate}&no=${vo.no}&pg=${pg}" class="btn btnCancel">취소</a>
	                        <input type="submit" value="작성완료" class="btn btnComplete">
	                    </div>
	                </form>
	            </section>
	        </main>
		</article>
    </section>
</div>
<jsp:include page="/_footer.jsp"/>