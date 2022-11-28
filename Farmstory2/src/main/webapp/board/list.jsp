<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/_header.jsp"/>
<jsp:include page="./_${group}.jsp"/>
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
							<tr>
			    				<td>${pageStartNum}</td>
								<td><a href="/Farmstory2/board/view.do?group=${group}&cate=${cate}&pg=${currentPage}&no=${ab.getNo()}">${ab.getTitle()}[${ab.getComment()}]</a></td>
								<td>${ab.getNick()}</td>
								<td>${ab.getRdate().substring(2, 10)}</td>
								<td>${ab.getHit()}</td>
							</tr>
						</tbody>
					</table>
					<!-- 페이지 네비게이션 -->
					<div class="page">
							<a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}&pg=${pageGroupStart - 1}" class="prev">이전</a>
							<a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}&pg=${i}" class="num ${currentPage == i ? 'current' : 'off'}">${i}</a>
							<a href="/Farmstory2/board/list.do?pg=${pageGroupEnd +1}" class="next">다음</a>
					</div>
					<!-- 글쓰기 버튼 -->
					<a href="/Farmstory2/board/write.do?group=${group}&cate=${cate}" class="btn btnWrite">글쓰기</a>
				</section>
			</main>
		</article>
	</section>
</div>
<jsp:include page="/_footer.jsp"/>