<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="content">
			<form id="id-form" name="idForm" method="post" action="/gs25/user/idFind">
			<a></a>
			<fieldset>
			<legend>아이디 찾기</legend>
				<label class="block-label" for="name">이름</label>
				<input id="name" name="name" type="text" value=""><br>
				<label class="block-label" for="name">생년월일</label>
				<input id="birth" name="birth" type="text" value=""><br>
				<label class="block-label" for="name">전화번호</label>
				<input id="name" name="name" type="text" value=""><br>
				<input type="submit" value="아이디찾기">
			</fieldset>
			</form>
			<form id="password-form" name="passForm" method="post" action="/gs25/user/passFind">
			<fieldset>
			<legend>비밀번호 찾기</legend>
				<label class="block-label" for="email">아이디</label>
				<input id="email" name="email" type="text" value=""><br>
				<label class="block-label" for="name">이름</label>
				<input id="name" name="name" type="text" value=""><br>
				<label class="block-label" for="birth">생년월일</label>
				<input id="birth" name="birth" type="text" value=""><br>
				<label class="block-label" for="phone">전화번호</label>
				<input id="phone" name="phone" type="text" value=""><br>
				<input type="submit" value="비밀번호찾기">
			</fieldset>
			</form>
		</div>
	</div>
</body>
</html>