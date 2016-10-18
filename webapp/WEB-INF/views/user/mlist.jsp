<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/gs25/assets/css/index.css" rel="stylesheet" type="text/css">
<link href="/gs25/assets/css/manage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>

<script type="text/javascript">
var stmnLEFT = 230; // 오른쪽 여백 
var stmnGAP1 = 20; // 위쪽 여백 
var stmnGAP2 = 150; // 스크롤시 브라우저 위쪽과 떨어지는 거리 
var stmnBASE = 150; // 스크롤 시작위치 
var stmnActivateSpeed = 35; //스크롤을 인식하는 딜레이 (숫자가 클수록 느리게 인식)
var stmnScrollSpeed = 23; //스크롤 속도 (클수록 느림)
var stmnTimer; 

function RefreshStaticMenu() { 
	 var stmnStartPoint, stmnEndPoint; 
	 stmnStartPoint = parseInt(document.getElementById('STATICMENU').style.top, 10); 
	 stmnEndPoint = Math.max(document.documentElement.scrollTop, document.body.scrollTop) + stmnGAP2; 
	 if (stmnEndPoint < stmnGAP1) stmnEndPoint = stmnGAP1; 
	 if (stmnStartPoint != stmnEndPoint) { 
	  stmnScrollAmount = Math.ceil( Math.abs( stmnEndPoint - stmnStartPoint ) / 15 ); 
	  document.getElementById('STATICMENU').style.top = parseInt(document.getElementById('STATICMENU').style.top, 10) + ( ( stmnEndPoint<stmnStartPoint ) ? -stmnScrollAmount : stmnScrollAmount ) + 'px'; 
	  stmnRefreshTimer = stmnScrollSpeed; 
	  }
	 stmnTimer = setTimeout("RefreshStaticMenu();", stmnActivateSpeed); 
 } 
 
function InitializeStaticMenu() {
	 document.getElementById('STATICMENU').style.right = stmnLEFT + 'px';  //처음에 오른쪽에 위치. left로 바꿔도.
	 document.getElementById('STATICMENU').style.top = document.body.scrollTop + stmnBASE + 'px'; 
	 RefreshStaticMenu();
 }
 </script>
</head>
<body>
<div id="container">
<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<c:if test="${authUser.position == 'HEADQUARTERS'}">
	<div id="intro_main">
		<div id="intro_sub">
			<!-- 회원관리 삭제버튼이 죽어요.. -->
			<!-- <div id="STATICMENU" style="right: 130px;"> -->
				<div class="myarea_wrap" id="userLive">
					<div class="mymenu">
						<div class="couwrap_off">
							<p>
								안녕하세요<br> 본사관리자님<br>
							</p>
							<p class="menu_tit">관리자 메뉴</p>
						</div>
						<ul class="my_lst">
							<li><a href="/gs25/map/mlist" class="my_m0">매장관리</a></li>
							<li><a href="/gs25/user/mlist" class="my_m1">회원관리</a></li>
							<li><a href="/gs25/product/list" class="my_m2">상품관리</a></li>
							<li><a href="/gs25/custom/customboardManager" class="my_m3">고객센터</a></li>
						</ul>
						<div class="menuwrap">
							<p class="menu_tit"></p>
						</div>
					</div>
					<a href="#" class="top" style="width: 85px;">TOP</a>
				</div>
			<!-- </div> -->
		</div>
	</div>
	</c:if>
	
	<div id="content" style="padding: 80px 0;">
	<div id="manage_wrap">
	
		<div id="title1">
			<h1>회원관리</h1>
			<h4>
				전체 회원수 :  <span>${total }</span>
			</h4>
		</div>
		
		<div id="tap_content">
		    <ul class="tabs">
		        <li class="active" rel="tab1">CUSTOMER</li>
		        <li rel="tab2">BRANCH</li>
		    </ul>
		    <div class="tab_container">
		        <div id="tab1" class="tab_content">
		        	<h4>
						CUSTOMER 회원수 :  <span>${map.totalCount }</span>
					</h4>
					
					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>아이디</th>
							<th>비밀번호</th>
							<th>생년월일</th>
							<th>성별</th>
							<th>주소</th>
							<th>연락처</th>
							<th>포인트</th>
							<th>직책</th>

							<th>삭제</th>
						</tr>
						<c:set var="firstIndex"	value="${map.totalCount - (map.currentPage - 1)*map.sizeList }" />
						<c:forEach var='vo' items='${map.list}' varStatus='status'>
							<tr>
							<td>${countList - s.index }</td>
							<td>${vo.name }</td>
							<td>${vo.email }</td>
							<td>${vo.password }</td>
							<td>${vo.birth }</td>
							<td>${vo.gender }</td>
							<td>${vo.address }</td>
							<td>${vo.phone }</td>
							<td>${vo.point }</td>
							<td>${vo.position }</td>
							<td>
								<c:if test='${vo.position != "HEADQUARTERS" }'>
									<a href="/gs25/user/userdelete?no=${vo.no}" class="del">삭제</a>
								</c:if>
							</td>
							</tr>
						</c:forEach>
					</table>
					
					<div class="pager">
						<ul>
							<c:if test="${map.prevtoPage >= 0 }">
								<li><a href="/gs25/user/mlist?p=${map.prevtoPage }">◀◀</a></li>
							</c:if>

							<c:if test="${map.prevPage >= 0 }">
								<li><a href="/gs25/user/mlist?p=${map.prevPage }">◀</a></li>
							</c:if>

							<c:forEach begin='${map.firstPage }' end='${map.lastPage }'
								step='1' var='i'>
								<c:choose>
									<c:when test='${map.currentPage == i }'>
										<li class="selected">${i }</li>
									</c:when>
									<c:when test='${i > map.pageCount }'>
										<li>${i }</li>
									</c:when>
									<c:otherwise>
										<li><a href="/gs25/user/mlist?p=${i }">${i }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:if test='${map.nextPage > 0 }'>
								<li><a href="/gs25/user/mlist?p=${map.nextPage }">▶</a></li>
							</c:if>
							<c:if test='${map.nexttoPage > 0 }'>
								<li><a href="/gs25/user/mlist?p=${map.nexttoPage }">▶▶</a></li>
							</c:if>

						</ul>
					</div>
		        </div>
		        <div id="tab2" class="tab_content">
		        	<h4>
						BRANCH 회원수 :  <span>${map2.totalCount }</span>
					</h4>
					
					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>아이디</th>
							<th>비밀번호</th>
							<th>생년월일</th>
							<th>성별</th>
							<th>주소</th>
							<th>연락처</th>
							<th>포인트</th>
							<th>직책</th>
							<th>매장번호</th>
							<th>삭제</th>
						</tr>
						<c:set var="firstIndex"	value="${map2.totalCount - (map2.currentPage - 1)*map2.sizeList }" />
						<c:forEach var='vo' items='${map2.list}' varStatus='status'>
							<tr>
							<td>${countList - s.index }</td>
							<td>${vo.name }</td>
							<td>${vo.email }</td>
							<td>${vo.password }</td>
							<td>${vo.birth }</td>
							<td>${vo.gender }</td>
							<td>${vo.address }</td>
							<td>${vo.phone }</td>
							<td>${vo.point }</td>
							<td>${vo.position }</td>
							<c:choose>
								<c:when test='${vo.store_no != 0 }'>
									<td>${vo.store_no }</td>
								</c:when>
								<c:otherwise>
									<td>X</td>
								</c:otherwise>
							</c:choose>
							<td>
								<a href="/gs25/user/userdelete?no=${vo.no}" class="del">삭제</a>
							</td>
							</tr>
						</c:forEach>
					</table>
					
					<div class="pager">
						<ul>
							<c:if test="${map.prevtoPage >= 0 }">
								<li><a href="/gs25/user/mlist?p=${map.prevtoPage }">◀◀</a></li>
							</c:if>

							<c:if test="${map.prevPage >= 0 }">
								<li><a href="/gs25/user/mlist?p=${map.prevPage }">◀</a></li>
							</c:if>

							<c:forEach begin='${map.firstPage }' end='${map.lastPage }'
								step='1' var='i'>
								<c:choose>
									<c:when test='${map.currentPage == i }'>
										<li class="selected">${i }</li>
									</c:when>
									<c:when test='${i > map.pageCount }'>
										<li>${i }</li>
									</c:when>
									<c:otherwise>
										<li><a href="/gs25/user/mlist?p=${i }">${i }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:if test='${map.nextPage > 0 }'>
								<li><a href="/gs25/user/mlist?p=${map.nextPage }">▶</a></li>
							</c:if>
							<c:if test='${map.nexttoPage > 0 }'>
								<li><a href="/gs25/user/mlist?p=${map.nexttoPage }">▶▶</a></li>
							</c:if>

						</ul>
					</div>
		        </div>
		    </div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</div>
</body>

<script>
	$( window ).scroll( function() {
	    if ( $( this ).scrollTop() > 200 ) {
	      $( '.top' ).fadeIn();
	    } else {
	      $( '.top' ).fadeOut();
	    }
	  } );
	  $( '.top' ).click( function() {
	    $( 'html, body' ).animate( { scrollTop : 0 }, 400 );
	    return false;
	  } );
</script>

<script>
$(function () {
    $(".tab_content").hide();
    $(".tab_content:first").show();
    $("ul.tabs li").click(function () {
        $("ul.tabs li").removeClass("active").css("color", "#333");
        $(this).addClass("active").css("color", "darkred");
        $(".tab_content").hide()
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn()
    });
});
</script>
</html>