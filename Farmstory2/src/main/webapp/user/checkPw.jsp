<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/_header.jsp"/>
<script src="/Farmstory2/js/emailAuth.js"></script>
<script>
	let success = "${success}";
	
	if(success == "100"){
		alert('비밀번호가 일치하지 않습니다.');	
	}
</script>
<main id="user">
    <section class="find checkPw">
        <form action="/Farmstory2/user/checkPw.do" method="post">
            <table border="0">
                <caption>회원정보 수정</caption>
                <tr>
                    <td>아이디</td>
                    <td><input type="text" name="uid" value="${sessUser.uid}" readonly/></td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td>
                        <div>
                            <input type="password" name="pass" placeholder="비밀번호 입력"/>
                        </div>
                    </td>
                </tr>                       
            </table>
            <div align="right">
            	<a href="/Farmstory2/" class="btn btnCancel">취소</a>
            	<input type="submit" class="btn btnNext" value="다음">
        	</div>                                        
        </form>

        
    </section>
</main>
<jsp:include page="/_footer.jsp"/>