<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<link href="/gs25/assets/css/product.css" rel="stylesheet" type="text/css">
<link href="/gs25/assets/css/manage.css" rel="stylesheet" type="text/css">
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
		<div id="content">
			<c:if test="${authUser.position == 'HEADQUARTERS'}">
			<div id="intro_main">
			<div id="intro_sub">
			<div> <!-- STATICMENU(삭제 버튼이 죽음) -->
			<div class="myarea_wrap" id="productLive">
			<div class="mymenu">
				<div class="couwrap_off">
					<p>
					안녕하세요<br>
					본사관리자님<br>
					</p>
					<p class="menu_tit">관리자 메뉴</p>
				</div>
				<ul class="my_lst">
					<li>
						<a href="/gs25/map/mlist" class="my_m0">매장관리</a>
					</li>
					<li>
						<a href="/gs25/user/mlist" class="my_m1">회원관리</a>
					</li>
					<li>
						<a href="/gs25/product/list" class="my_m2">상품관리</a>
					</li>
					<li>
						<a href="/gs25/custom/customboardManager" class="my_m3">고객센터</a>
					</li>
				</ul>
				<div class="menuwrap">
					<p class="menu_tit"></p>
				</div>
			</div>
			<a href="#" class="top" style="width: 85px;">TOP</a>
			</div>
			</div>
			</div>
			</div>
			</c:if>
			
			<div class="container_product">
				<div class="container_productsub">

					<div id="store_header">
						<div id="store">
							<h1 id="sub_h1">상품검색</h1>
						</div>
					</div>

					<div id="search">
						<form id="search_form" action="/gs25/product/list" method="get">
							<input type="text" id="kwd" name="kwd" value="${map.keyword }">
							<input type="submit" value="찾기">
						</form>
					</div>

					<%-- <c:if test='${authUser.no==1 }'> --%>
						<div class="insert">
							<a href="/gs25/product/insert" id="new-book">상품등록</a>
						</div>
					<%-- </c:if> --%>

					<div id="productboard">
						<div id="productlist">
							<ul>
								<c:forEach var='vo' items='${map.list}' varStatus='status'>
									<li id="product_li">
										<ul class="tag_list_01">
											<li class="ico_tag_03">
												<!--${vo.category}-->
											</li>
										</ul> 
										<div class="pic_product">
										<div id="del">
										<a href="/gs25/product/delete?no=${vo.no }" class="del">삭제</a>
										</div>
											<a href="/gs25/product/view?no=${vo.no }&name=${vo.name }"><img id="product" src="${vo.imageurl }" alt="형민)맛있는도시락"></a>
											<div class="infowrap">
												<div class="name">${vo.name }</div>
												<div class="price">${vo.price }원</div>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>

							<c:if test="${empty map.list}">
								<div id="right">
									<div id="risk">
										<img src="/gs25/assets/images/customcenter/risk.png">
									</div>
									<p class="list-right">
										검색된 결과를 찾을 수 없습니다. <br>
									</p>
								</div>
							</c:if>


							<c:if test='${not empty map.list }'>
								<!-- begin:paging -->
								<div class="pager">
									<ul>
										<c:if test="${map.prevtoPage >= 0 }">
											<li><a href="/gs25/product/list?p=${map.prevtoPage }">◀◀</a></li>
										</c:if>

										<c:if test="${map.prevPage >= 0 }">
											<li><a href="/gs25/product/list?p=${map.prevPage }">◀</a></li>
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
													<li><a href="/gs25/product/list?p=${i }">${i }</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>

										<c:if test='${map.nextPage > 0 }'>
											<li><a href="/gs25/product/list?p=${map.nextPage }">▶</a></li>
										</c:if>
										<c:if test='${map.nexttoPage > 0 }'>
											<li><a href="/gs25/product/list?p=${map.nexttoPage }">▶▶</a></li>
										</c:if>
									</ul>
								</div>
							</c:if>
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
</html>