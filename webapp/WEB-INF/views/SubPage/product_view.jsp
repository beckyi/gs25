<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/product.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/gs25/assets/js/jquery/jquery-1.9.0.js"></script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
	<div class="container">
		<div id="title_head">
			<div id="title1">
				<h3>상품 상세 페이지</h3>
			</div>
		<div id ="title1_p">
			<p>매월 새롭게 진행되는 다양한 행사상품과 함께 고객님께 즐거움을 제공해드립니다.^^</p>
			<div class="go_see">
				<a href="/gs25/product/list">전체 상품 보기</a>
			</div>
		</div>
		</div>
	</div>

	<div id="product_back">
		<div class="container">
			<div class="inner">
			<div class="product_view">
				<div class="product_img">
					<span> <img id="pro_img"
						src="http://gs25appimg.gsretail.com/imgsvr/item/GD_8801056076719_002.jpg"
						alt="덴마크)요거트킹자몽오렌지" />
					</span>

					<ul class="tag_list_02">
						<li class='ico_tag_06'>1+1</li>
					</ul>
					
				</div>
				<div class="productView_content">
					<dl>
						<dt>
							<!-- D: 제목 서브 내용 길이 1줄 - 51byte -->
							<span class="tit_3depth"></span>
							<!-- D: 제목 타이틀 길이 1줄 - 25byte -->
							<strong class="tit_product_view">덴마크)요거트킹자몽오렌지</strong>
							<!-- D: 메이커 길이 1줄 - 51byte -->
							<span class="tit_3depth">동원에프앤비 덴마크</span>
						</dt>

						<dd class="productView_content_dd_01">
						
							<ul class="productView_content_ul">
								<li><strong>유통기한</strong> : <span>230ml</span></li>
								<li></li>
							</ul>
							
							<span class="txt"> 덴마크)요거트킹자몽오렌지230ml </span>
						
						</dd>
						<dd class="productView_content_dd_02">
							<span class="product_price"> <strong>2,200</strong><span>원</span></span>
						</dd>
					</dl>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //cont_top -->
	<div class="container">
		<div id="Nsearch">
			<h5 id="tit">네이버 블로그 후기</h5>
			<table class="tbl_wtype1">
				<tr>
					<td id="table_head">
						<image src="https://ssl.pstatic.net/sstatic/search/2015/h_logo.png" width="99" height="18" alt="NAVER"></image>
					</td>
				</tr>
				<tr align="center">
					<td>
						<item>
				                <title>메고/<b>SAMPLE</b> SALE</title>
				                <link> http://openapi.naver.com/l?AAABWISw6DIBgGT/OzNAoWVyygsffg8VGSalWKJtxeOpnFZI4TuSqaDWlJ+vkPM5HpWak7lFu2N/ugKj+4sZdO8OAaAB6RWyFD8EP7U2QpI6pUyk5CE381f7DZp+5rL+TOb+sNWgbE7WoAAAA=
				            	</link>
				                <description>
							                   장마라더니 비가 잠시 소강 상태네요~~~ 패브릭 가방 <b>SAMPLE</b> SALE 들어갑니다. 
							                   한 개씩만 있는 제품이구요~~ 재고 소진 후에 주문시에는 SALE 가격 적용 안 되네요~~~^^* 
							                   찜콩 안 되구요~ 반품,교환 안되니깐...
				                </description>
				                <bloggername>MEGO</bloggername>
				                <bloggerlink>http://blog.naver.com/mego72</bloggerlink>
				        </item>
					</td>
				</tr>
				<tr align="center">
					<td>2행 1열</td>
				</tr> 	
			</table>
		</div>
	</div>


	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>