
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zxx" class="no-js">
<head>
<!-- Mobile Specific Meta -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Favicon-->
<link rel="shortcut icon" href="img/fav.png">
<!-- Author Meta -->
<meta name="author" content="colorlib">
<!-- Meta Description -->
<meta name="description" content="">
<!-- Meta Keyword -->
<meta name="keywords" content="">
<!-- meta character set -->
<meta charset="UTF-8">
<!-- Site Title -->
<title>Travel</title>

<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700"
	rel="stylesheet">
<!--
			CSS
			============================================= -->
<link rel="stylesheet" href="css/linearicons.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/magnific-popup.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/nice-select.css">
<link rel="stylesheet" href="css/animate.min.css">
<link rel="stylesheet" href="css/owl.carousel.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="fonts/icomoon/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="fonts/icomoon/style.css">
<link rel="stylesheet" href="css/owl.carousel.min.css">
</head>
<body>
	<header id="header">
		<div class="header-top">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-lg-6 col-sm-6 col-6 header-top-left">
						<ul>
							<li><a href="#">Visit Us</a></li>
							<li><a href="#">Buy Tickets</a></li>
						</ul>
					</div>
					<div class="col-lg-6 col-sm-6 col-6 header-top-right">
						<div class="header-social">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-dribbble"></i></a> <a href="#"><i
								class="fa fa-behance"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container main-menu">
			<div class="row align-items-center justify-content-between d-flex">
				<div id="logo">
					<a href="index.jsp"><img src="img/logo.png" alt="" title="" /></a>
				</div>
				<nav id="nav-menu-container">
					<ul class="nav-menu">
						<c:choose>
							<c:when test="${sessionScope.principal != null }">
								<li><a href="<%=request.getContextPath()%>/user?cmd=logout">로그아웃</a></li>
								<li class="menu-has-children"><a href="">회원정보</a>
				            		<ul>
				              			<li><a href="<%=request.getContextPath()%>/user?cmd=infoForm">회원정보보기</a></li>
				              			<li><a href="<%=request.getContextPath()%>/user?cmd=updateForm">회원정보변경</a></li>
				            		</ul>
				          		</li>
							</c:when>
							<c:otherwise>
								<li><a href="<%=request.getContextPath()%>/user?cmd=loginForm">로그인</a></li>
								<li><a href="<%=request.getContextPath()%>/user?cmd=joinForm">회원가입</a></li>
							</c:otherwise>
						</c:choose>
						<li><a href="#">항공권 예매</a></li>
						<li><a href="#">예약 조회</a></li>
						<li><a href="#">항공편 조회</a></li>
					</ul>
				</nav>
				<!-- #nav-menu-container -->
			</div>
		</div>
	</header>
	<!-- #header -->


	<!-- start banner Area -->
	<section class="about-banner relative">
		<div class="overlay overlay-bg"></div>
		<div class="container">
			<div class="row d-flex align-items-center justify-content-center">
				<div class="about-content col-lg-12">
					<h1 class="text-white">항공편</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- End banner Area -->

	<div class="content">
		<div class="container">
			<c:if test="${go ne null }">
			<div>가는 편 </div>
			<br/>
			<table class="table">
    			<thead class="thead-light">
      				<tr>
        				<th>출도착시간</th>
        				<th>Economy</th>
        				<th>Prestige</th>
        				<th>항공편id</th>
      				</tr>
    			</thead>
   				 <tbody>
   				 	<c:forEach var="item" items="${go }">
   				 		<fmt:parseDate value="${item.depPlandTime}" var="depPlandTime" pattern="yyyyMMddHHmm"/>
    					<fmt:formatDate pattern="HH:mm" value="${depPlandTime }" var="depPlandTime"/>
    					<fmt:parseDate value="${item.arrPlandTime}" var="arrPlandTime" pattern="yyyyMMddHHmm"/>
    					<fmt:formatDate pattern="HH:mm" value="${arrPlandTime }" var="arrPlandTime"/>
   				 		<tr>
   				 			<td>${depPlandTime } ~ ${arrPlandTime }</td>
   				 			<td><input type="radio" name="goFlightCheck" onclick="go(${item.depPlandTime},${item.arrPlandTime },${item.economyCharge },'${item.vihicleId }','economy')"> <fmt:formatNumber value="${item.economyCharge }" pattern="#,###" /> KRW</td>
   				 			<td><input type="radio" name="goFlightCheck" onclick="go(${item.depPlandTime},${item.arrPlandTime },${item.prestigeCharge }, '${item.vihicleId }','prestige')"> <fmt:formatNumber value="${item.prestigeCharge }" pattern="#,###" /> KRW</td>
   				 			<td>${item.vihicleId }</td>  				 			
   				 	</c:forEach>    
    			</tbody>
  			</table>
  			</c:if>
  			
  			
			<c:if test="${back ne null }">
			<div>오는 편 </div>
			<br/>
			<table class="table">
    			<thead class="thead-light">
      				<tr>
        				<th>출도착시간</th>
        				<th>Economy</th>
        				<th>Prestige</th>
        				<th>항공편id</th>
      				</tr>
    			</thead>
   				 <tbody>
   				 	<c:forEach var="item" items="${back }">
   				 		<fmt:parseDate value="${item.depPlandTime}" var="depPlandTime" pattern="yyyyMMddHHmm"/>
    					<fmt:formatDate pattern="HH:mm" value="${depPlandTime }" var="depPlandTime"/>
    					<fmt:parseDate value="${item.arrPlandTime}" var="arrPlandTime" pattern="yyyyMMddHHmm"/>
    					<fmt:formatDate pattern="HH:mm" value="${arrPlandTime }" var="arrPlandTime"/>
   				 		<tr>
   				 			<td>${depPlandTime } ~ ${arrPlandTime }</td>
   				 			<td><input type="radio" name="backFlightCheck" onclick="back(${item.depPlandTime},${item.arrPlandTime },${item.economyCharge },'${item.vihicleId }','economy')"> <fmt:formatNumber value="${item.economyCharge }" pattern="#,###" /> KRW</td>
   				 			<td><input type="radio" name="backFlightCheck" onclick="back(${item.depPlandTime},${item.arrPlandTime },${item.prestigeCharge }, '${item.vihicleId }','prestige')"> <fmt:formatNumber value="${item.prestigeCharge }" pattern="#,###" /> KRW</td>
   				 			<td>${item.vihicleId }</td>  				 			
   				 	</c:forEach>    
    			</tbody>
  			</table>
  			</c:if>
  			<table class="table">
    			<thead class="thead-light">
      				<tr>
      					<th></th>
      					<th>인원</th>
        				<th>출발시간</th>
        				<th>도착시간</th>
        				<th>항공편</th>
        				<th>좌석등급</th>
        				<th>가격</th>
      				</tr>
    			</thead>
   				 <tbody id="select__list">
   				 	
    			</tbody>
  			</table>
  			<div class="btn-group btn-group-sm pull-right">
 				 <button type="button" class="btn btn-secondary" onclick="flightReserve(${sessionScope.principal.id})">예약하기</button>
  				<a href="index.jsp"><button type="button" class="btn btn-secondary" >취소</button></a>
			</div>
		</div>
	</div>
	<!-- start footer Area -->
	<footer class="footer-area section-gap">
		<div class="container">

			<div class="row">
				<div class="col-lg-3  col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>About Agency</h6>
						<p>The world has become so fast paced that people donât want
							to stand by reading a page of information, they would much rather
							look at a presentation and understand the message. It has come to
							a point</p>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>Navigation Links</h6>
						<div class="row">
							<div class="col">
								<ul>
									<li><a href="#">Home</a></li>
									<li><a href="#">Feature</a></li>
									<li><a href="#">Services</a></li>
									<li><a href="#">Portfolio</a></li>
								</ul>
							</div>
							<div class="col">
								<ul>
									<li><a href="#">Team</a></li>
									<li><a href="#">Pricing</a></li>
									<li><a href="#">Blog</a></li>
									<li><a href="#">Contact</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3  col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>Newsletter</h6>
						<p>For business professionals caught between high OEM price
							and mediocre print and graphic output.</p>
						<div id="mc_embed_signup">
							<form target="_blank"
								action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
								method="get" class="subscription relative">
								<div class="input-group d-flex flex-row">
									<input name="EMAIL" placeholder="Email Address"
										onfocus="this.placeholder = ''"
										onblur="this.placeholder = 'Email Address '" required=""
										type="email">
									<button class="btn bb-btn">
										<span class="lnr lnr-location"></span>
									</button>
								</div>
								<div class="mt-10 info"></div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-lg-3  col-md-6 col-sm-6">
					<div class="single-footer-widget mail-chimp">
						<h6 class="mb-20">InstaFeed</h6>
						<ul class="instafeed d-flex flex-wrap">
							<li><img src="img/i1.jpg" alt=""></li>
							<li><img src="img/i2.jpg" alt=""></li>
							<li><img src="img/i3.jpg" alt=""></li>
							<li><img src="img/i4.jpg" alt=""></li>
							<li><img src="img/i5.jpg" alt=""></li>
							<li><img src="img/i6.jpg" alt=""></li>
							<li><img src="img/i7.jpg" alt=""></li>
							<li><img src="img/i8.jpg" alt=""></li>
						</ul>
					</div>
				</div>
			</div>

			<div
				class="row footer-bottom d-flex justify-content-between align-items-center">
				<p class="col-lg-8 col-sm-12 footer-text m-0">
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					Copyright &copy;
					<script>
						document.write(new Date().getFullYear());
					</script>
					All rights reserved | This template is made with <i
						class="fa fa-heart-o" aria-hidden="true"></i> by <a
						href="https://colorlib.com" target="_blank">Colorlib</a>
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				</p>
				<div class="col-lg-4 col-sm-12 footer-social">
					<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
						class="fa fa-twitter"></i></a> <a href="#"><i
						class="fa fa-dribbble"></i></a> <a href="#"><i
						class="fa fa-behance"></i></a>
				</div>
			</div>
		</div>
	</footer>
	<!-- End footer Area -->

	<script src="js/vendor/jquery-2.2.4.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/easing.min.js"></script>
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.min.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/mail-script.js"></script>
	<script src="js/main.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script>
				function go(depPlandTime, arrPlandTime, charge, vihicleId, grade){
						var goData = {
								personnel: ${flightSearch.personnel},
								depPlandTime: depPlandTime,
								arrPlandTime: arrPlandTime,
								charge: charge * ${flightSearch.personnel},
								vihicleId: vihicleId,
								grade: grade
						}
						console.log(goData);

						$("#go__flight").remove();
												
						var goSelect = '<tr id="go__flight">';
						goSelect += '<td>가는 편</td>';
						goSelect += '<td id="go__personnel">'+goData.personnel+'</td>';
						goSelect += '<td id="go__depTime">'+goData.depPlandTime+'</td>';
						goSelect += '<td id="go__arrTime">'+goData.arrPlandTime+'</td>';
						goSelect += '<td id="go__vihicleId">'+goData.vihicleId+'</td>';
						goSelect += '<td id="go__grade">'+goData.grade+'</td>';
						goSelect += '<td id="go__charge">'+goData.charge+'</td>';
						goSelect += '</tr>';

						$("#select__list").prepend(goSelect);
						
					}	

					function back(depPlandTime, arrPlandTime, charge, vihicleId, grade){
						var backData = {
								personnel: ${flightSearch.personnel},
								depPlandTime: depPlandTime,
								arrPlandTime: arrPlandTime,
								charge: charge *${flightSearch.personnel},
								vihicleId: vihicleId,
								grade: grade
						}
						console.log(backData);

						$("#back__flight").remove();
						
						var backSelect = '<tr id="back__flight">';
						backSelect += '<td>오는 편</td>';
						backSelect += '<td id="back__personnel">'+backData.personnel+'</td>';
						backSelect += '<td id="back__depTime">'+backData.depPlandTime+'</td>';
						backSelect += '<td id="back__arrTime">'+backData.arrPlandTime+'</td>';
						backSelect += '<td id="back__vihicleId">'+backData.vihicleId+'</td>';
						backSelect += '<td id="back__grade">'+backData.grade+'</td>';
						backSelect += '<td id="back__charge">'+backData.charge+'</td>';
						backSelect += '</tr>';

						$("#select__list").prepend(backSelect);
				}

				function flightReserve(userId){
					var data = [];
					
					var goData = {
						userId: userId,
						personnel: ${flightSearch.personnel},
						depAirportNm: '${flightSearch.depAirportNm}',
						arrAirportNm: '${flightSearch.arrAirportNm}',
						depPlandTime: document.getElementById('go__depTime').innerText,
						arrPlandTime: document.getElementById('go__arrTime').innerText,
						vihicleId: document.getElementById('go__vihicleId').innerText,
						grade: document.getElementById('go__grade').innerText,
						charge: document.getElementById('go__charge').innerText
					}

					data.push(goData);
					console.log(goData);
					
					<c:if test="${back != null}">
					var backData = {
							userId: userId,
							personnel: ${flightSearch.personnel},
							depAirportNm: '${flightSearch.arrAirportNm}',
							arrAirportNm: '${flightSearch.depAirportNm}',
							depPlandTime: document.getElementById('back__depTime').innerText,
							arrPlandTime: document.getElementById('back__arrTime').innerText,
							vihicleId: document.getElementById('back__vihicleId').innerText,
							grade: document.getElementById('back__grade').innerText,
							charge: document.getElementById('back__charge').innerText
						}

						data.push(backData);
						console.log(backData);
					</c:if>
					
					console.log(data);
				
					$.ajax({
						type : "post",
						url : "/project/book?cmd=book",
						data : JSON.stringify(data),
						contentType : "application/json; charset=utf-8",
						dataType : "json"
					}).done(function(result){
						if(result.statusCode == 1){
							location.href = "index.jsp";
						} else {
							alert("항공권 예약에 실패하셨습니다.");
						}

					});
				}

				
  		</script>
</body>
</html>