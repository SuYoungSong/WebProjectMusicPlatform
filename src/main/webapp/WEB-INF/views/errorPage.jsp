<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true"%>
<html>
<head>
    <title>Dream</title>
</head>
<link rel="stylesheet" href="/css/bodycss.css">
<body>
<style>
    .errorZone{
        margin-top: 10%;
        text-align: center;
    }
</style>
<!-- 네비게이션 -->
<div>
  <%@include file="sideNavigation.jsp"%>
</div>
<div>
  <jsp:include page = "sideController.jsp"></jsp:include>
</div>


<div class="errorZone">
    <img src="/resources/images/warning.png"/>
    <h1>잘못된 요청입니다.</h1>
    <h1>메인 페이지로 돌아가세요</h1>
</div>
</body>
</html>
