<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/_header.jsp"/>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/Farmstory2/js/userModify.js"></script>
<script src="/Farmstory2/js/zipcode.js"></script>
<main id="user">
    <section class="modify">
        <form action="/Farmstory2/user/modify.do" method="post">
            <table border="1">
                <caption>사이트 이용정보 수정</caption>
                <tr>
                    <td>아이디</td>
                    <td>
                        <input type="text" name="uid" value="${sessUser.uid}" readonly/>
                    </td>
                </tr>
                <tr>
                	<td>비밀번호 변경</td>
                	<td class="passChange">
                		<button type="button" class="btnPwChange">변경하기</button>
                		<button type="button" class="btnChangeCancel">접기</button>
                	</td>
                </tr>
                <tr class="pass1">
                    <td>비밀번호</td>
                    <td><input type="password" name="pass1" placeholder="비밀번호 입력"/></td>
                </tr>
                <tr class="pass2">
                    <td>비밀번호 확인</td>
                    <td><input type="password" name="pass2" placeholder="비밀번호 입력 확인"/>
                    	<span class="passResult"></span>
                    </td>
                </tr>
                <tr>
                	<td>가입날짜</td>
                	<td><input type="text" name="rdate" value="${sessUser.rdate}" readonly></td>
                </tr>
            </table>
            <table border="1">
                <caption>개인정보 수정</caption>
                <tr>
                    <td>이름</td>
                    <td>
                        <input type="text" name="name" value="${sessUser.name}"/>
                        <span class="nameResult"></span>                        
                    </td>
                </tr>
                <tr>
                    <td>별명</td>
                    <td>
                        <p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
                        <input type="text" name="nick" value="${sessUser.nick}"/>
                        <button type="button" id="btnNickCheck"><img src="/Farmstory2/img/chk_id.gif" alt="중복확인"/></button>
                        <span class="nickResult"></span>
                    </td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td>
                        
                        <input type="email" name="email" value="${sessUser.email}"/>
                        <button type="button" id="btnCheckEmail"><img src="/Farmstory2/img/chk_id.gif" alt="중복확인"></button>
                        <button type="button" id="btnEmail"><img src="/Farmstory2/img/chk_auth.gif" alt="인증번호 받기"/></button>
                        <span class="resultEmail"></span>
                        <div class="auth">
                            <input type="text" name="auth" placeholder="인증번호 입력"/>
                            <button type="button" id="btnEmailConfirm"><img src="/Farmstory2/img/chk_confirm.gif" alt="확인"/></button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>휴대폰</td>
                    <td><input type="text" name="hp" value="${sessUser.hp}"/>
                    	<span class="hpResult"></span>
                    </td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td>
                        <input type="text" name="zip" id="zip" value="${sessUser.zip}" readonly/>
                        <button type="button" onclick="zipcode()"><img src="/Farmstory2/img/chk_post.gif" alt="우편번호찾기"/></button>
                        <input type="text" name="addr1" id="addr1" value="${sessUser.addr1}"/>
                        <input type="text" name="addr2" id="addr2" value="${sessUser.addr2}"/>
                    </td>
                </tr>
                <tr>
                	<td>회원탈퇴</td>
                	<td><button type="button" class="btnDelete">회원탈퇴</button></td>
                </tr>
            </table>
            <div>
                <a href="/Farmstory2/user/login.do" class="btn btnCancel">취소</a>
                <input type="submit" value="수정하기" class="btn btnModify"/>
            </div>
        </form>
    </section>
</main>
<jsp:include page="/_footer.jsp"/>