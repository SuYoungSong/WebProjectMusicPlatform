<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dream</title>
    <link rel="stylesheet" href="/css/bodycss.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
<!-- 네비게이션 -->
<div id="nav">
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
</div>

${user.name}님 가입을 환영합니다<br>
<img src="../../resources/images/${userProfileImage.serverFilePath}"/><br>
아이디 : ${user.id}<br>
닉네임: ${user.nickname}<br>
연락처: ${user.phone}<br>

</body>
</html>
