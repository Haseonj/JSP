<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/_header.jsp" %>
        <div id="sub">
            <div><img src="/Farmstory1/img/sub_top_tit1.png" alt="INTRODUCTION"></div>
            <section class="cate1">
                <aside>
                    <img src="/Farmstory1/img/sub_aside_cate1_tit.png" alt="팜스토리 소개">
                    <ul class="lnb">
                        <li><a href="/Farmstory1/introduction/hello.jsp">인사말</a></li>
                        <li class="on"><a href="/Farmstory1/introduction/direction.jsp">찾아오시는 길</a></li>
                    </ul>
                </aside>
                <article>
                    <nav>
                        <img src="/Farmstory1/img/sub_nav_tit_cate1_tit2.png" alt="찾아오시는길">
                        <p>
                            HOME > 팜스토리소개 >
                            <em>찾아오시는길</em>
                        </p>
                    </nav>
                    <p>
                        <strong>팜스토리</strong>
                        <br>
                        주소 : 경기도 이천시 잘한다구 신난다동 123
                        <br>
                        전화 : 01-1234-5678
                        <br>
                    </p>
                    <p>
                        <strong>찾아오시는길</strong>
                        <br>
                        <!-- * 카카오맵 - 지도퍼가기 -->
                        <div id="map" style="height:400px;"></div>
                        
                        <!-- 1. 지도 노드 -->
                    </p>
                    <div></div>
                </article>
            </section>
        </div>
<%@ include file="/_footer.jsp" %>