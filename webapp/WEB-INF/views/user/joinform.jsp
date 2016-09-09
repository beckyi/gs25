<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="/gs25/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(function(){
	 $('#password').keyup(function(){
		   $('font[name=passCheck]').text('');
		  }); //#password.keyup

		  $('#repassword').keyup(function(){
		   if($('#password').val()!=$('#repassword').val()){
		    $('font[name=passCheck]').text('');
		    $('font[name=passCheck]').html("암호틀림");
		   }else{
		    $('font[name=passCheck]').text('');
		    $('font[name=passCheck]').html("암호맞음");
		   }
		  }); 
});
</script>
</head>
<body>
	<div id="container">
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post" action="/gs25/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value=""><br>

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value=""><br>
					<!-- <img id="image-checked"  style="width:16px; display: none" src="/gs25/img/check.png"/>
					<input type="button" id="btn-checkEmail" value="id 중복체크"> -->
					
					<label class="block-label">비밀번호</label>
					<input id="password" name="password" type="password" value=""><br>
					
					<label class="block-label">비밀번호 재확인</label>
					<input id="repassword" name="repassword" type="password" value=""><br>
					<font name="passCheck" size="2" color="red"></font>
					
					<label class="block-label" for="name">생년월일</label>
					<input id="birth" name="birth" type="text" value=""><label>예) 1990.01.27 : 19900127</label><br>
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="FEMALE" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="MALE">
					</fieldset><br>
					
					<label class="block-label" for="name">주소</label>
					<input id="address" name="address" type="text" value=""><br>
					
					<label class="block-label" for="name">전화번호</label>
					<input id="phone" name="phone" type="text" value=""><br>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset><br>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>