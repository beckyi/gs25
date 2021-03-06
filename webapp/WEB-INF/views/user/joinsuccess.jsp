<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/gs25/assets/css/gs25site.css" rel="stylesheet" type="text/css">
<link href="/gs25/assets/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<div class="container">
		<div id="title1">
			<h3>GS25 편의점 회원가입 완료</h3>
		</div>
		<div id ="title1_p">
			<p><em id="emphasis">회원가입을 축하합니다.</em>&nbsp;고객과 함께 내일을 꿈꾸며,
			<br>새로운 삶의 가치를 창조하는 GS25 편의점 회원이 되신걸 환영합니다.</p>
		</div>
		<div id="contents">
			<div id="join-succ">
				<div id="goMain">
					<ul>
						<li id="line"><h3 class="lineM"><a href="/gs25/main">메인페이지 이동</a></h3></li>
						<li id="line"><h3 class="lineL"><a href="/gs25/user/loginform">로그인하기</a></h3></li>
					</ul>
				</div>	
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>