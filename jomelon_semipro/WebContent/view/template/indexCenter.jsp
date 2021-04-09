<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section>
	<div class="sec1">
		<h1 class="sec1t">January New Product</h1>
		<a href="#"> <img id="img1" src="${pageContext.request.contextPath}/images/조말론이달의.jpg">
		</a>
	</div>
	<div class="sec2">
		<img id="introimg">
	</div>
	<div class="sec3">
		<img id="img2" src="${pageContext.request.contextPath}/images/메인3.jpg">
		<div class="img2t">
			<a href="#"> Promotion<br>
			<br>
			<br>
			<br>
			<br> 나만의 DIY 향수 체험해보기
			</a>
		</div>
	</div>
	<div class="sec4">
		<h1 class="sec4t">January Best Seller</h1>
		<div class="wrap">
			<!-- 전체 wrap 감싸안으며 -->
			<div class="slide">
				<button type="button" class="prev">
					<span class="fas fa-angle-left"></span>
				</button>
				<ul>
					<li><img class="slimg" src="${pageContext.request.contextPath}/images/향수1.PNG" alt="" /></li>
					<li><img class="slimg" src="${pageContext.request.contextPath}/images/향수2.PNG" alt="" /></li>
					<li><img class="slimg" src="${pageContext.request.contextPath}/images/향수3.PNG" alt="" /></li>
				</ul>
				<button type="button" class="next">
					<span class="fas fa-angle-right"></span>
				</button>
			</div>
		</div>
		<!-- wrap END -->

	</div>
</section>