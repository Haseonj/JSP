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
let isUidOk 	= true;
let isPassOk 	= true;
let isNameOk 	= true;
let isNickOk 	= true;
let isEmailOk	= true;
let isEmailAuthOk = true;
let isEmailAuthCodeOk = true;
let receivedCode = true;
let isHpOk 		= true;


$(function(){
	
	//  비밀번호 검사하기
	$('input[name=pass2]').focusout(function(){
		
		let pass1 = $('input[name=pass1]').val();		
		let pass2 = $('input[name=pass2]').val();
		
		if(pass2.match(rePass)){
			
			if(pass1 == pass2){
				isPassOk = true;
				$('.passResult').css('color','green').text('사용하실 수 있는 비밀번호 입니다.');
			}else{
				isPassOk = false;
				$('.passResult').css('color','red').text('비밀번호가 일치하지 않습니다.');
			}
			
		}else{
			isPassOk = false;
			$('.passResult').css('color','red').text('비밀번호는 숫자, 영문, 특수문자 포함 5자리 이상 이어야 합니다.');
		}
		
	});	
	
	//  이름 검사하기
	$('input[name=name]').focusout(function(){
		
		let name = $(this).val();
		
		if(name.match(reName)){
			isNameOk = true;
			$('.nameResult').text('');
		}else{
			isNameOk = false;
			$('.nameResult').css('color','red').text('유효한 이름이 아닙니다.');
		}
		
	});
	
	//  별명 검사하기
	$('input[name=nick]').keydown(function(){
		isNickOk = false;
	});
	
	$('#btnNickCheck').click(function(){
		
		let nick = $('input[name=nick]').val();
		
		if(isNickOk){
			return;
		}
		
		if(!nick.match(reNick)){
			$('.nickResult').css('color','red').text('유효한 별명이 아닙니다.');
			isNickOk = false;
			return;	
		}
		
		
		let jsonData = {
				"nick": nick
		};
		
		$('.nickResult').css('color','black').text('...');
		
		setTimeout(function(){
			
			$.ajax({
				url: '/Farmstory2/user/checkNick.do',
				method: 'get',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					
					if(data.result == 0){
						$('.nickResult').css('color','green').text('사용 가능한 별명 입니다.');	
						isNickOk = true;
					}else{
						$('.nickResult').css('color','red').text('이미 사용중인 별명 입니다.');
						isNickOk = false;
					}
					
				}
			});
			
		}, 500);
		
	});
	
	//  이메일 유효성 검사
	$('input[name=email]').focusout(function(){
		
		let email = $(this).val();
		
		if(!email.match(reEmail)){
			isEmailOk = false;
			$('.resultEmail').css('color','red').text('유효하지 않은 이메일입니다.');
		}else{
			isEmailOk = true;
			$('.resultEmail').text('');
		}
		
	});
	
	// 이메일 중복 검사
	$('#btnCheckEmail').click(function(){
		
		let email = $('input[name=email]').val();
		
		$.ajax({
			url: '/Farmstory2/user/checkEmail.do',
			method: 'get',
			data: {"email": email},
			dataType: 'json',
			success: function(data){
				if(data.result == 0){
					$('#btnCheckEmail').hide();
					$('#btnEmail').show();
					isEmailOk = true;
					isEmailAuthOk = false;
					isEmailAuthCodeOk = false;
				}else{
					alert('이미 사용중인 이메일 입니다.');
					isEmailOk = false;
				}
			}
		});
	});
	
	// 이메일 인증코드 발송 클릭
	$('#btnEmail').click(function(){
		
		$(this).hide();
		let email = $('input[name=email]').val();
		
		if(email == ''){
			alert('이메일을 입력 하세요.');
			return;
		}
		
		if(isEmailAuthOk){
			console.log('here2');
			return;	
		}
		
		isEmailAuthOk = true;
		
		$('.resultEmail').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
		console.log('here3');
		
		setTimeout(function(){
			console.log('here4');
			$.ajax({
				url: '/Farmstory2/user/emailAuth.do',
				method: 'get',
				data: {"email":email},
				dataType: 'json',
				success: function(data){
					if(data.status > 0){
						// 메일 발송 성공
						console.log('here5');
						isEmailAuthOk = true;
						$('.resultEmail').text('인증코드를 전송 했습니다. 이메일을 확인 하세요.');
						$('.auth').show();
						receivedCode = data.code;
						
					}else{
						// 메일 발송 실패
						console.log('here5');
						isEmailAuthOk = false; 
						alert('메일전송이 실패 했습니다.\n다시 시도 하시기 바랍니다.');
					}
				}
			});
		}, 1000);		
	});
	
	// 이메일 인증코드 확인 버튼
	$('#btnEmailConfirm').click(function(){
		let code = $('input[name=auth]').val();
		
		if(code == receivedCode){
			isEmailAuthCodeOk = true;
			$('input[name=email]').attr('readonly', true);
			$('.resultEmail').text('이메일이 인증 되었습니다.');
			$('.auth').hide();
		}else{
			isEmailAuthCodeOk = false;
			alert('인증코드가 틀립니다.\n다시 확인 하십시오.');
		}
	});
	
	//  휴대폰 검사하기
	$('input[name=hp]').focusout(function(){
		
		let hp = $(this).val();
		
		if(hp.match(reHp)){
			isHpOk = true;
			$('.hpResult').text('');
		}else{
			isHpOk = false;
			$('.hpResult').css('color','red').text('유효하지 않은 휴대폰입니다.');
		}
		
	});
	
	
	// 회원탈퇴
	$('#btnDelete').click(function(){
		
		let isDeleteOk = confirm('정말 탈퇴 하시겠습니까?');
		
		if(isDeleteOk){
			let uid = $('input[name=uid]').val();
			
			$.ajax({
				url: '/Farmstory2/user/delete.do',
				method: 'get',
				data: {"uid": uid},
				dataType: 'json',
				success: function(data){
					if(data.result == 1){
						$(location).attr('href', '/Farmstory2/user/login.do?success=300');
					}
				}
			});	
		}
		
		
		
	});
	
	// 최종 폼 전송할 때
	$('.register > form').submit(function(){
		
		// 아이디 검증
		if(!isUidOk){
			alert('아이디를 확인 하십시오.');
			return false;	
		}
		
		// 비밀번호 검증
		if(!isPassOk){
			alert('비밀번호가 유효하지 않습니다.');
			return false;	
		}
		
		// 이름 검증
		if(!isNameOk){
			alert('이름이 유효하지 않습니다.');
			return false;	
		}
		
		// 별명 검증
		if(!isNickOk){
			alert('별명을 확인 하십시오.');
			return false;	
		}
		
		// 이메일 검증
		if(!isEmailOk){
			alert('이메일이 유효하지 않습니다.');
			return false;	
		}
		
		// 이메일 인증코드 검증
		if(!isEmailAuthCodeOk){
			alert('이메일을 인증을 수행 하십시오.');
			return false;	
		}
		
		// 휴대폰 검증
		if(!isHpOk){
			alert('휴대폰이 유효하지 않습니다.');
			return false;	
		}
		
		return true;
	});
});