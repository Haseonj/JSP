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
						<div id="map" style="width:100%;height:400px;"></div>
						
						<script>
							var container = document.getElementById('map');
							var options = {
								center: new kakao.maps.LatLng(35.136078, 129.0965834),
								level: 3
							};
					
							var map = new kakao.maps.Map(container, options);
							
							// 마커가 표시될 위치입니다 
							var markerPosition  = new kakao.maps.LatLng(35.136078, 129.0965834); 

							// 마커를 생성합니다
							var marker = new kakao.maps.Marker({
							    position: markerPosition
							});

							// 마커가 지도 위에 표시되도록 설정합니다
							marker.setMap(map);

							// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
							// marker.setMap(null);    
						</script>                        
                        <!-- 1. 지도 노드 -->
                    </p>
                    <div></div>
                </article>
            </section>
        </div>
<%@ include file="/_footer.jsp" %>