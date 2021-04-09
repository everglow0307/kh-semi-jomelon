<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jo Melon</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="${pageContext.request.contextPath}/css/imgslide.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"
	media="all" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.20.3/TweenMax.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/controller.js"></script>
<script type="text/javascript"> 
        var imgArray=new Array(); 
        imgArray[0]="${pageContext.request.contextPath}/images/조말론이달의2.jpg"; 
        imgArray[1]="${pageContext.request.contextPath}/images/조말론이달의3.jpg";
        imgArray[2]="${pageContext.request.contextPath}/images/조말론이달의4.jpg";
        function showImage(){ var imgNum=Math.round(Math.random()*2); 
        var objImg=document.getElementById("introimg"); 
        objImg.src=imgArray[imgNum]; setTimeout(showImage,3000); }

            (function(){
            var current = 0;
            var max = 0;
            var container;
            var interval;
            var xml;
            var animateTarget = null;
            function init(){
            container = jQuery(".slide ul");
            max = container.children().length;
            //console.log();
            events();
            interval = setInterval(next, 3000);
            }
            // 무한 롤링 셋팅
            function setting(){
            container.css("margin-left","-600px");
            container.prepend(container.children()[max-1]);
            }
            function events(){
            jQuery("button.prev").on("click", prev);
            jQuery("button.next").on("click", next);
            jQuery(window).on("keydown", keydown);
            }
            function prev( e ){
            current--;
            if( current < 0 )  current = max-1;
            animate("prev");    // pram
            }
            function next( e ){
            current++;
            if( current > max-1 ) current = 0;
            animate("next");    // pram
            }
            function animate( $direction ){
            if( animateTarget !=null ){
            TweenMax.killTweensOf( animateTarget );
            animateTarget.css("margin-left","0");
            }
            if( $direction == "next"){
            jQuery(container.children()[1]).css("margin-left","600px");
            container.append( container.children()[0] );
            } else if( $direction == "prev"){
            container.prepend( container.children()[max-1] );
            jQuery(container.children()[0]).css("margin-left","-600px");
            }
            animateTarget = jQuery(container.children()[0]);
            TweenMax.to( animateTarget, 0.8, { marginLeft:0, ease:Expo.easeOut });
            clearInterval(interval);  // 누적된어 있는것을 클리어 함
            interval = setInterval(next, 3000);  // Interval 누적됨
            }
            function keydown( e ){
            //console.log(e); 키보드 이벤트 로그
            if( e.which == 39 /*right*/ ){
                next();
            }else if( e.which == 37 /*left*/ ){
                prev();
                }
            }
            jQuery( document ).ready( init );
            })();

        </script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
<!-- My style Sheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainstyle.css" type="text/css">
</head>
<body onload="showImage()">


	<c:import url="indexHeader.jsp"></c:import>
	<c:if test="${contentPage eq null}">
		<c:import url="indexCenter.jsp" />
	</c:if>
	<c:if test="${!empty contentPage}">
		<c:import url="${contentPage}"></c:import>
	</c:if>
	<c:import url="footer.jsp"></c:import>



</body>
</html>