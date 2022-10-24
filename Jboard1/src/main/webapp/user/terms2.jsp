<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="kr.co.jboard1.db.DBCP"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
	$(function(){
		// 다음버튼을 클릭 했을 때 나타나는 이벤트에 대한 로직
		$('.btnNext').click(function(){
			
			// 체크박스의 상태 중 체크여부를 확인하는 객체 생성
			let isCheck1 = $('input[class=terms]').is(':checked');
			let isCheck2 = $('input[class=privacy]').is(':checked');
			
			// 체크박스에 모두 체크 했을 때와 그렇지 않을 때의 로직
			if(isCheck1 && isCheck2){
				
				// 모두 체크 하였다면 트루를 반환하여 다음으로 넘어감
				return true;
			}else{
				
				// 하지 않았다면, 폴스를 반환하여 다음으로 넘어가지 않으며 아래와 같은 문구가 화면상단에 나옴
				alert('동의 체클르 하셔야 합니다.');
				return false;
			}
		});
	})
</script>
<%
	// DB내용을 담을 변수 생성
	String terms = null;
	String privacy = null;

	try{
		
		// 생성해둔 커넥션 풀 이용하여 DB연결
		Connection conn = DBCP.getConnection();
		// DB의 정보를 전부 가져오기만 하므로, statement 사용
		Statement stmt = conn.createStatement();
		// DB정보를 resultset 타입으로 변환 후 저장
		ResultSet rs = stmt.executeQuery("SELECT * FROM `board_terms`");
		
		if(rs.next()){
			// resultset타입 DB정보를 변수에 저장
			terms = rs.getString(1);
			privacy = rs.getString(2);
		}
		// 커넥션 풀 반납
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<main id="user">
    <section class="terms">
        <table border="1">
            <caption>사이트 이용약관</caption>
            <tr>
                <td>
                    <textarea name="terms"><%= terms %></textarea>
                    <label><input type="checkbox" class="terms">&nbsp;동의합니다</label>
                </td>
            </tr>
        </table>
        <table border="1">
            <caption>개인정보 취급방침</caption>
            <tr>
                <td>
                    <textarea name="privacy"><%= privacy %></textarea>
                    <label><input type="checkbox" class="privacy">&nbsp;동의합니다</label>
                </td>
            </tr>
        </table>
        <p>
            <a href="/Jboard1/user/login.jsp" class="btn btnCancel">취소</a>
            <a href="/Jboard1/user/register.jsp" class="btn btnNext">다음</a>
        </p>
    </section>
</main>
<%@ include file="./_footer.jsp" %>