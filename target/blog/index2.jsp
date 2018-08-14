<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Home</title>
<meta charset="utf-8">
<link rel="stylesheet" href="Awebsit/css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="Awebsit/css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="/Awebsit/css/style.css" type="text/css" media="all">
<script type="text/javascript" src="${pageContext.request.contextPath }/Awebsit/js/jquery-1.4.2.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Awebsit/js/cufon-yui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Awebsit/js/cufon-replace.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath }/Awebsit/js/Copse_400.font.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Awebsit/js/jquery.nivo.slider.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/Awebsit/js/imagepreloader.js"></script>
<script type="text/javascript">
	preloadImages([
	'Awebsit/images/menu1_active.gif',
	'Awebsit/images/menu2_active.gif',
	'Awebsit/images/menu3_active.gif',
	'Awebsit/images/menu4_active.gif',
	'Awebsit/images/marker_right_active.jpg',
	'Awebsit/images/marker_left_active.jpg',
	'Awebsit/images/menu5_active.gif']);
</script>
<!--[if lt IE 9]>
	<script type="text/javascript" src="http://info.template-help.com/files/ie6_warning/ie6_script_other.js"></script>
	<script type="text/javascript" src="js/html5.js"></script>
<![endif]-->
</head>
<body id="page1">
<div class="body1">
	<div class="body2">
		<div class="main">
<!-- header -->
			<header>
				<div class="wrapper">
					   
					<div class="right">
						<nav>
							<ul id="top_nav">
								<li><a href="#">User Area</a></li>
								<li><a href="#">Sitemap</a></li>
							</ul>
							<form id="search" method="post">
								<div>
									<input type="submit" class="submit" value="">
									<input type="text" class="input">
								</div>
							</form>
						</nav>
					</div>
				</div>
				<nav id="menu">
					<ul>
						<li class="nav1" id="active"><a href="index.jsp">Home</a></li>
						<li class="nav2"><a href="Awebsit/祖玛.html">GAME</a></li>
						<li class="nav3"><a href="portal/index.action">BLOG</a></li>
						<li class="nav4"><a href="#">None</a></li>
						<li class="nav5"><a href="#">None</a></li>
					</ul>
				</nav>
			</header>
		</div>
	</div>
</div>
	<div class="body3">
		<div class="body4">
			<div class="main">
				<div class="slogan">
					<div id="slider">
					
						<a href="portal/index.action"><img src="Awebsit/images/blog1.png" alt=""></a>
						<a href="Awebsit/祖玛.html"><img src="Awebsit/images/youxi.png" alt=""></a>
							
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- / header -->
<!-- content -->
	<div class="body5">
		<div class="body6">
			<div class="main">
				<div class="wrapper">
					<figure class="left"><img src="Awebsit/images/letter1.png" alt=""></figure>
					<h2>Welcome to <span>My WebSite</span>!</h2>
					<p style="font-style:normal; color:important;">本网站为黄渊和郭文婧两人纯手工打造，前端后端数据库一手包办，包括祖玛小游戏及博客系统。<br></p>
				</div>
			</div>
		</div>
	</div>
	
	
<script type="text/javascript"> Cufon.now(); </script>
<script type="text/javascript">
	$(window).load(function() {
	$('#slider').nivoSlider({
        effect:'sliceDown', //Specify sets like: 'fold,fade,sliceDown, sliceDownLeft, sliceUp, sliceUpLeft, sliceUpDown, sliceUpDownLeft'    
        slices:20,
        animSpeed:300,
        pauseTime:9999999999,
        startSlide:0, //Set starting Slide (0 index)
        directionNav:true, //Next & Prev
        directionNavHide:false, //Only show on hover
        controlNav:false, //1,2,3...
        controlNavThumbs:false, //Use thumbnails for Control Nav
        controlNavThumbsFromRel:false, //Use image rel for thumbs
        controlNavThumbsSearch: '.jpg', //Replace this with...
        controlNavThumbsReplace: '_thumb.jpg', //...this in thumb Image src
        keyboardNav:true, //Use left & right arrows
        pauseOnHover:true, //Stop animation while hovering
        manualAdvance:false, //Force manual transitions
        captionOpacity:1, //Universal caption opacity
        beforeChange: function(){},
        afterChange: function(){},
        slideshowEnd: function(){} //Triggers after all slides have been shown
    });
    });
</script>
</body>
</html>