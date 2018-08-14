<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
</head>

<body>
	<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<!-- 采用转发方式 地址栏不变化 更友好     通过访问 localhost：8080/blog/其实是访问到此页面，即localhost：8080/blog/index.jsp 
	然后再此页面执行下面语句的转发 

-->
<jsp:forward page="portal/index.action"></jsp:forward>

<!-- 这种方法时重定向   请求路径会显示在地址栏 -->
<!-- <script>
	window.location.href = "${APP_PATH}/portal/index.action";
</script> -->
</body>


</html>