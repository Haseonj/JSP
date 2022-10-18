<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<main id="user">
    <section class="register">
        <form action="#">
            <table border="1">
                <caption>사이트 이용정보 입력</caption>
                <tr>
                    <td>아이디</td>
                    <td>
                        <input type="text" name="uid" placeholder="아이디 입력">
                        <button type="button"><img src="/Jboard1/img/chk_id.gif" alt="중복확인"></button>
                        <span class="uidResult"></span>
                    </td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input type="password" name="pass" placeholder="비밀번호 입력"></td>
                </tr>
                <tr>
                    <td>비밀번호 확인</td>
                    <td><input type="password" name="pass2" placeholder="비밀번호 입력 확인"></td>
                </tr>
            </table>

            <table border="1">
                <caption>이용정보 입력</caption>
                <tr>
                    <td>이름</td>
                    <td>
                        <input type="text" name="uid" placeholder="이름 입력">
                    </td>
                </tr>
                <tr>
                    <td>별명</td>
                    <td>
                        <p class="nickInfo">공백없는 한글, 영문, 숫자 입력</p>
                        <input type="text" name="nick" placeholder="별명 입력">
                        <button type="button"><img src="/Jboard1/img/chk_id.gif" alt="중복확인"></button>
                        <span class="nickResult"></span>
                    </td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input type="email" name="email" placeholder="이메일 입력"></td>
                </tr>
                <tr>
                    <td>휴대폰</td>
                    <td><input type="text" name="hp" placeholder="휴대폰 입력"></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td>
                        <input type="text" name="zip" placeholder="우편번호">
                        <button type="button"><img src="/Jboard1/img/chk_post.gif" alt=""></button>
                        <input type="text" name="addr1" placeholder="주소 검색">
                        <input type="text" name="addr2" placeholder="상세주소 입력">
                    </td>
                </tr>
            </table>

            <div>
                <a href="/Jboard1/user/login.jsp" class="btn btnCancel">취소</a>
                <input type="submit" value="회원가입" class="btn btnRegister"/>
            </div>
        </form>
    </section>
</main>
<%@ include file="./_footer.jsp" %>