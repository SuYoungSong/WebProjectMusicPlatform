<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            margin-left:210px;
        }
    </style>
</head>
<body>
<!-- 네비게이션 -->
<div id="nav">
    <%@include file="sideNavigation.jsp"%>
</div>

${user.name}님 가입을 환영합니다<br>
<img src="../../resources/images/${userProfileImage.serverFilePath}"/><br>
아이디 : ${user.id}<br>
닉네임: ${user.nickname}<br>
연락처: ${user.phone}<br>

</body>
</html>
