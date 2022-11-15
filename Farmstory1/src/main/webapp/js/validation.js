/**
 * 
 */
 
// 타자 검증에 사용하는 정규표현식
let reUid 	= /^[a-z]+[a-z0-9]{5,19}$/g;
let rePass 	= /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
let reName 	= /^[ㄱ-힣]+$/;
let reNick 	= /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
let reHp 	= /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

// 폼 데이터 검증 결과 상태변수
let isUidOk 	= false;
let isPassOk 	= false;
let isNameOk 	= false;
let isNickOk 	= false;
let isEmailOk	= false;
let isHpOk 		= false;

 
 $(function(){
	// 아이디 검사하기
	$('input[name=uid]').keydown(function(){
		isUidOk = false;
	});
	
	$('#btnIdCheck').click(function(){
		
		let uid = $('input[name=uid]').val();
		
		if(isUidOk){
			return;
		}
		
		if(!uid.match(reUid)){
			$('.uidResult').css('color', 'red').text('유효한 아이디가 아닙니다.');
			isUidOk = false;
			return;
		}
		
		let jsonData = {"uid":uid};
		
		$('.uidResult').css('color', 'black').text('...');
		
		setTimeout(function(){
			$.ajax({
				url:'/Farmstory1/user/proc/checkUid.jsp',
				method:'post',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					if(data.result == 0){
						$('.uidResult').css('color', 'green').text('사용 가능한 아이디 입니다.');
						isUidOk = true;
					}else{
						$('.uidResult').css('color', 'red').text('이미 사용중인 아이디 입니다.');
					}
				}
			});
		}, 500);
	});
});