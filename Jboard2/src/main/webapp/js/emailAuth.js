/**
 * 
 */
 
 let preventDoubleClick = false;
 let isEmailAuthOk = false;
 
 $(function(){
	
	// 이메일 인증 검사
	let emailCode = 0;
		
	$('#btnEmailAuth').click(function(){
		let email = $('input[name=email]').val();
		
		$.ajax({
			url: '/Jboard2/user/emailAuth.do',
			method: 'get',
			data: {"email":email},
			dataType: 'json',
			success: function(data){
				if(data.status == 1){
					// 메일 발송 성공
					emailCode = data.code;
					
					$('.emailResult').text('인증코드를 전송 했습니다. 이메일을 확인 하세요.');
					$('.auth').show();
				}else{
					// 메일 발송 성공
					$('.emailResult').text('이메일을 실패했습니다. 이메일을 확인 후 다시 하시기 바랍니다.');
				}
			}
		});
	});
	
	// 이메일 인증코드 확인
	$('#btnEmailConfirm').click(function(){
		let code = $('input[name=auth]').val();
		
		if(code == emailCode){
			isEmailAuthOk = true;
			$('.emailResult').text('이메일이 인증 되었습니다.');
		}
	});
})