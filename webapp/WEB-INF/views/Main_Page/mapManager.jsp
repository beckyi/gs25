<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>
<title>GS25</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/gs25/assets/css/map.css" rel="stylesheet" type="text/css">
<link href="/gs25/assets/css/manage.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=c0be589b60311ceeea226dd7d2e0e990"></script>
<script type="text/javascript"	src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>

<script>
$(function(){

if('${map.keyword}'=='서울' || '${map.keyword}'== '서울역' || '${map.keyword}'== 'GS25서울역점'  || '${map.keyword}'== '' ){	
	
var mapContainer = document.getElementById('mapM'), // 지도를 표시할 div 
mapOption = { 
    center: new daum.maps.LatLng(37.5547992, 126.9684953),
    level: 4 // 지도의 확대 레벨
};

var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

}else if('${map.keyword}'=='인천' || '${map.keyword}'=='GS25래미안아파트점' || '${map.keyword}'=='래미안아파트' ){	
	
	var mapContainer = document.getElementById('mapM'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.503463, 126.72378),
	    level: 4// 지도의 확대 레벨
	};

	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	}else if('${map.keyword}'=='안양' || '${map.keyword}'=='GS25성결점' || '${map.keyword}'=='성결대' ){	
	
	var mapContainer = document.getElementById('mapM'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(37.3800181, 126.9264755),
	    level: 4 // 지도의 확대 레벨
	};

	var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	}else if('${map2.keyword}'==null){
		
		var mapContainer = document.getElementById('mapM'), // 지도를 표시할 div 
		mapOption = { 
		    center: new daum.maps.LatLng(37.5547992, 126.9684953),
		    level: 4 // 지도의 확대 레벨
		};
	}
	
//마커를 표시할 위치와 title 객체 배열입니다 
var positions = [
	<c:forEach var = 'vo' items='${map2.list}' varStatus='s'>
	 
	{
	   content: '${vo.name}', 
	   latlng: new daum.maps.LatLng('${vo.localx}', '${vo.localy}')
	    },
	</c:forEach>
];

for (var i = 0; i < positions.length; i ++) {
    // 마커를 생성합니다
	  var marker = new daum.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng // 마커의 위치
	        
	    });
	    // 마커에 표시할 인포윈도우를 생성합니다 
	    var infowindow = new daum.maps.InfoWindow({
	        content: positions[i].content // 인포윈도우에 표시할 내용
	    });
	    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
	    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	}

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}

});
</script>

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
 
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
			<c:if test="${authUser.position == 'HEADQUARTERS'}">
			<div id="intro_main">
				<div id="intro_sub">
					<div id="STATICMENU" style="right: 130px;">
						<div class="myarea_wrap">
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
							<a href="#" class="top">TOP</a>
						</div>
					</div>
				</div>
			</div>
		</c:if>
			
		<div id="content">

			<div class="container_map">
				<div>
					<div id="store_header">
						<div id="store">
							<h1 id="sub_h1">매장관리</h1>
						</div>
					</div>

					<div id="search">
						<form id="search_form" action="/gs25/map/mlist" method="get">
							<input type="text" id="kwd" name="kwd" value="${map.keyword }">
							<input type="submit" value="찾기">
						</form>
					</div>
					
					<div id="mapM"></div>
					
					<div id=map_container>
						<div id="boardM">
							<table class="tbl-exM">
								<tr>
									<th>지역</th>
									<th>매장정보</th>
									<th>주소/제공서비스</th>
									<th>지점</th>
									<th>삭제</th>
								</tr>
								<c:forEach var='vo' items='${map.list}' varStatus='status'>
									<tr>
										<td>${vo.region_name }</td>
										<td>${vo.name }</td>
										<td>${vo.address }</td>
										<c:choose>
											<c:when test='${vo.no  == 0}'>
												<td><a href="/gs25/main">바로가기</a></td>
											</c:when>
											<c:otherwise>
												<td><a href="/gs25/sub/main?store_no=${vo.no }">바로가기</a></td>
											</c:otherwise>
										</c:choose>
										<td><a href="/gs25/map/delete?no=${vo.no }">삭제</a></td>
									</tr>
								</c:forEach>
							</table>

							<c:if test="${empty map.list}">
								<div id="submap_right">
									<div id="submap_risk">
										<img src="/gs25/assets/images/customcenter/risk.png">
									</div>
									<p class="submap_list-right">
										검색된 결과를 찾을 수 없습니다. <br>
									</p>
								</div>
							</c:if>

							<c:if test='${not empty map.list }'>
								<!-- begin:paging -->
								<div class="pager">
									<ul>

										<c:if test="${map.prevtoPage >= 0 }">
											<li><a href="/gs25/map/mlist?p=${map.prevtoPage }">◀◀</a></li>
										</c:if>

										<c:if test="${map.prevPage >= 0 }">
											<li><a href="/gs25/map/mlist?p=${map.prevPage }">◀</a></li>
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
													<li><a href="/gs25/map/mlist?p=${i }">${i }</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>

										<c:if test='${map.nextPage > 0 }'>
											<li><a href="/gs25/map/mlist?p=${map.nextPage }">▶</a></li>
										</c:if>
										<c:if test='${map.nexttoPage > 0 }'>
											<li><a href="/gs25/map/mlist?p=${map.nexttoPage }">▶▶</a></li>
										</c:if>
									</ul>
								</div>
							</c:if>
						</div>
					</div>
					<div class="insert">
						<a href="/gs25/map/insertForm" id="insertStore">매장등록</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>