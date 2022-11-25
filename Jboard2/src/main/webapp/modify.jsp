<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./_header.jsp"/>
        <main id="board">
            <section class="modify">

                <form action="/Jboard2/modify.do?no=${vo.no}&pg=${pg}" method="post">
                    <table border="0">
                        <caption>글수정</caption>
                        <tr>
                            <th>제목</th>
                            <td><input type="text" name="title" value="${vo.title}"/></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea name="content">${vo.content}</textarea>
                            </td>
                        </tr>
                        <c:if test="${vo.file > 0}">
                        <tr>
                            <th>파일</th>
                            <td>
                                <input type="file" name="file" value="${vo.fname}"/>
                            </td>
                        </tr>
                        </c:if>
                    </table>
                    
                    <div>
                        <a href="/Jboard2/view.do?no=${vo.no}&pg=${pg}" class="btn btnCancel">취소</a>
                        <input type="submit" value="작성완료" class="btn btnComplete"/>
                    </div>
                </form>

            </section>
        </main>
<jsp:include page="./_footer.jsp"/>