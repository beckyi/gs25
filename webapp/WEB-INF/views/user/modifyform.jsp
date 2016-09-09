<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="content">
			<div id="user">
				<form id="join-form" name="modifyForm" method="post" action="/gs25/user/modify">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name }">

					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<label class="block-label" for="name">생일</label>
					<input id="birth" name="birth" type="text" value="${userVo.birth }">
					
					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test='${"FEMALE" == userVo.gender }'>
								<label>여</label> <input type="radio" name="gender" value="FEMALE" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="MALE">
							</c:when>
							<c:otherwise>
								<label>여</label> <input type="radio" name="gender" value="FEMALE">
								<label>남</label> <input type="radio" name="gender" value="MALE"  checked="checked">
							</c:otherwise>
						</c:choose>
					</fieldset>
					
					<label class="block-label" for="name">주소</label>
					<input id="address" name="address" type="text" value="${userVo.address }">
					
					<label class="block-label" for="name">전화번호</label>
					<input id="phone" name="phone" type="text" value="${userVo.phone }">
					
					<input type="submit" value="수정하기">
				</form>
			</div>
		</div>
	</div>
</body>
</html>