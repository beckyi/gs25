<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/gs25/assets/css/attendance_check.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<title>attendance_check</title>

</head>
<body>
	<form action="/gs25/event/test" method="POST">
		<input type="text" name="1" id="test1">
		<input type="text" name="2" id="test2">
		<button type="submit">전송</button>
	</form>

	<jsp:include page="/WEB-INF/views/include/subheader.jsp" />
	<div class="container">
		<div class="title"><h1>매일매일 출석체크</h1></div>
			<form class="event_cal" action="#">
				<div class="count">나의 출석횟수:<em>0</em> &nbsp;회</div>
			</form>
		
		<div class="float_clear"></div>
		
		<div class="cal_box">
			<table>
				<thead>
					<tr>
						<th><div class="th_wrap">일요일</div></th>
						<th><div class="th_wrap">월요일</div></th>
						<th><div class="th_wrap">화요일</div></th>
						<th><div class="th_wrap">수요일</div></th>
						<th><div class="th_wrap">목요일</div></th>
						<th><div class="th_wrap">금요일</div></th>
						<th><div class="th_wrap">토요일</div></th>
					</tr>
				</thead>
				
				<tbody>
					<script>
					var today = new Date();
					var y = today.getFullYear(); // 현재 연도
					var m = today.getMonth(); // 현재 월
					// 현재 (y)년(m)월의 1일의 요일을 구합니다.
					var theDate = new Date(y,m,1);	//달력 1일
					var theDay = theDate.getDay();	//1일 요일 (1 = 월요일)
					/* 현재 월의 마지막 일을 구합니다. */
					// 1월부터 12월까지의 마지막 일을 배열로 만듭니다.
					var last = [31,28,31,30,31,30,31,31,30,31,30,31];
					 
					// 4년마다 있는 윤년을 계산합니다.(100년||400년 주기는 제외)
					if (y%4 && y%100!=0 || y%400===0) {
					    lastDate = last[1] = 29;
					}
					 // 현재 월의 마지막 일이 며칠인지 구합니다.
					var lastDate = last[m];
					/* 현재 월의 달력에 필요한 행의 개수를 구합니다. */
					    // theDay(빈 칸의 수), lastDate(월의 전체 일수)
					var row = Math.ceil((theDay+lastDate)/7);
					
					var date = 1;
						for (var i = 1; i <= row; i++) {
							document.write(
									"<tr>"
								)
							for(var j = 1; j <= 7; j++){
								if(i===1 && j<=theDay || date>lastDate){
									document.write("<td> <div class='td_wrap'>&nbsp</div></td>")
								}else{
								document.write(
										"<td>"+
										"<div class='td_wrap' id="+date+" style='background-color: rgb(238, 251, 248);'>"+
											"<div class='td_date'>"+date+"</div>"+
											"<div class='td_img'>"+
												"<img src='https://hpsimg.gsretail.com/medias/sys_master/images/images/h7f/h2e/8927663915038.png' width='114px' height='77px' id='giftImgNum_25'>"+
											"</div>"+
											"<div class='gift_txt' id='giftTitleNum_25'>"+
											"</div>"+
											"<div class='prs_num'>3점</div>"+
										"</div>"+
										"</td>"
									)
									date++;
								}
							}
							document.write(
									"</tr>"
								)
						}
					</script>
				</tbody>
			</table>
		</div>
	</div>
		
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
<!-- 	<div class="fontTest">fontTestfontTest</div> -->
</body>
<script>
$(function() {	
	var today = new Date();
	var y = today.getFullYear(); // 현재 연도
	var m = today.getMonth(); // 현재 월
	var d = today.getDate();
	
	if( d == $("#email")){
		document.write("<div class='td_wrap'>기간만료</div>"+"<div><input type="button" value="출석체크"></div>")
	}
});
	
</script>
</html>