<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user2::manager</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script src="./js/list.js"></script>
		<script src="./js/register.js"></script>
		<script src="./js/modify.js"></script>
		<script>
			$(document).ready(function(){
				
				
				
				// user2 목록 불러오기
				list();
				
				// user2 목록보기 (동적 연결 이벤트)
				$(document).on('click', '#userList', function(e){
					e.preventDefault();
					list();
				});
				
				// user2 등록화면
				$(document).on('click', '#userAdd', function(e){
					e.preventDefault();
					register();
				});
				
				// user2 등록하기
				$(document).on('click', '#btnRegister', function(){
					
					// 데이터 가져오기
					let uid  = $('input[name=uid]').val();
					let name = $('input[name=name]').val();
					let hp 	 = $('input[name=hp]').val();
					let age  = $('input[name=age]').val();
					
					// JSON 생성
					let jsonData = {
							"uid":uid,
							"name":name,
							"hp":hp,
							"age":age
							};
					
					console.log(jsonData);
					
					// 전송하기
					$.ajax({
						url: './data/register.jsp',
						method: 'post',
						data: jsonData,
						dataType: 'json',
						success: function(data){
							
							if(data.result == 1){
								alert('입력성공!');
								list();
							}else{
								alert('입력실패! 아이디가 중복 되었습니다.');
							}
						}
					});
				});
					
				// user2 수정화면
				$(document).on('click', '#userModify', function(e){
					e.preventDefault();
					
					// 수정 하기 전의 데이터 값을 입력 해놓기 위한 용도로
					// 수정 버튼을 누른 행의 uid, name, hp, age값이 들어있는 td태그를 저장한다.
					let user = $(this).parent().parent().children(); // a -> td(관리 컬럼) -> tr -> td(아이디, 이름, 휴대폰, 나이, 관리)
					
					modify(user); // 파라미터로 user변수를 보낸다.
				});
				
				// user2 수정하기
				$(document).on('click', '#btnModify', function(){
					
					// 데이터 가져오기
					let uid = $('input[name=uid]').val();
					let name = $('input[name=name]').val();
					let hp = $('input[name=hp]').val();
					let age = $('input[name=age]').val();
					
					// json데이터로 변환
					let jsonData = {
							"uid":uid,
							"name":name,
							"hp":hp,
							"age":age
					};
					
					// 데이터 전송
					$.ajax({
						url: './data/modify.jsp',
						method: 'post',
						data: jsonData,
						dataType: 'json',
						success: function(data){
							if(data.result == 1){
								alert('수정되었습니다.');
								list();
							}else{
								alert('수정되지 않았습니다.');
							}
						}
					});
				});
				
				// user2 삭제하기
				$(document).on('click', '#userDelete', function(){
					
					let doDelete = confirm('정말 삭제 하시겠습니까?');
					
					if(doDelete){
						
						// 정보 가져오기
						let uid = $(this).parent().parent().children('td:eq(0)').text();
						
						// json 변환하기
						let jsonData = {
							"uid":uid
						}
						
						// 변환된 데이터 전송
						$.ajax({
							url: './data/delete.jsp',
							method: 'post',
							data: jsonData,
							dataType: 'json',
							success: function(data){
									alert('삭제되었습니다.');
									list();
							}
						});
					}else{
						return false;
					}
				});
			});
		</script>
	</head>
	<body>
		<h3>user2 관리자</h3>
		
		<nav></nav>
		<section></section>
		
	</body>
</html>