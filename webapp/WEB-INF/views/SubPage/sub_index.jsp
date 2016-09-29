<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/sub.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
	<div class="container">
		<div id="contents">
			<div class="img">
				<img src="/gs25/assets/images/subindex/gs25Main.png" width="600px">
			</div>
			<div class="login">
				<div id="login1">
				<p id="title"><em>GS편의점</em> <em style="color: blue">로그인</em></p>
				<p id="intro">기존 GS편의점 사이트에 가입하신<br> 아이디와 비밀번호로 로그인 하실 수 있습니다.</p>
				</div>
				<div id="login2">
					<form class="login-form" name="loginform" method="post" action="/convenience/user/login">
						<ul>
							<li><input id="email" name="email" class="form-control" type="text" value="" placeholder="이메일"></li>
							<li><input name="password" type="password" class="form-control" value="" placeholder="비밀번호"></li>
						</ul>
							<input id="login-btn" type="submit" value="로그인">
					</form>
				</div>
				<div id="login3">
					<a href='/gs25/user/joinform' id="aleft">회원가입</a>
					<a href="/gs25/user/findInfo" id="aright">아이디/비밀번호찾기</a>
				</div>
			</div>
		</div>
	</div>
		
	<div class="prod_wrap">
		<div class="container">
			<div class="prod_tab">
				<div class="afterClass" id="tab1">유통기한임박 상품<div>할인율 최고적용 상품</div></div>
				<div class="beforeClass" id="tab2">인기상품<div>누적판매순 인기상품</div></div>
				<div class="beforeClass" id="tab3">신상품<div>방금 들어온 따끈따끈한 신상품</div></div>
				<div class="beforeClass" id="tab4">추천상품<div>선택장애 극복을 위한 추천상품</div></div>
			</div>

			<div class="prod_list" id="list1">
				<ul>
					<c:set var='countList' value='${fn:length(list)}'/>
               		<c:forEach var='vo' items='${vo }' varStatus='status'>
						<li>
							<div class='sbbox pro'>
								<span class='tip typ1'><span>${countList }</span></span>
								<a href='#'><img src='${vo.producturl }'></a>
								<span class='title'>
								<em class='mt'>${vo.maker})${vo.name }</em><em>${vo.price }원</em>
								</span>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="prod_list" id="list2">
				<ul>
					<c:forEach begin="1" end="4" varStatus='status'>
								<li>
								<div class='sbbox pro'>
								<span class='tip typ1'><span>"+i+"</span></span>
								<a href='/gscvs/ko/products/event-goods'><img src='http://gs25appimg.gsretail.com/imgsvr/item/GD_8801056076719_002.jpg' alt='Y)오렌지스파클 상품'></a>
								<span class='title'>
								<em class='mt'>유어스)롯데오렌지스파클</em><em>1,700원</em>
								</span>
								</div>
								</li>
					</c:forEach>

				</ul>
			</div>
			<div class="prod_list" id="list3">
				<ul>
					<c:set var='countList' value='${fn:length(list)}'/>
               		<c:forEach var='vo3' items='${vo3 }' varStatus='status'>
						<li>
							<div class='sbbox pro'>
								<span class='tip typ1'><span>${countList }</span></span>
								<a href='#'><img src='${vo3.producturl }'></a>
								<span class='title'>
								<em class='mt'>${vo3.maker})${vo3.name }</em><em>${vo3.price }원</em>
								</span>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="prod_list" id="list4">
				<ul>
					<c:set var='countList' value='${fn:length(list)}'/>
               		<c:forEach var='vo' items='${vo4 }' varStatus='status'>
						<li>
							<div class='sbbox pro'>
								<span class='tip typ1'><span>${countList }</span></span>
								<a href='#'><img src='${vo.producturl }'></a>
								<span class='title'>
								<em class='mt'>${vo.maker})${vo.name }</em><em>${vo.price }원</em>
								</span>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
		
		
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
<script>
jQuery('.prod_tab').click(function (e) {
	console.log('${list }');
	var getId = e.target.getAttribute('id');
	var id = getId.substring( getId.length-1, getId.length );
	var index = 0; 
	// alert(getId);
	for( index = 1; index<=5; index++){
		$('#tab'+index).attr('class', 'afterClass');
		if(index != id){
			$('#tab'+index).attr('class', 'beforeClass');
			$('#list'+id).show();
		}
		$('#list'+index).hide();
	}
});
</script>
</html>