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

<form action="/front/loginProcess" method="POST">
    아이디:<input type="text" name="id" value="${returnId}"/><br>
    비밀번호:<input type="password" name="pass" value="${returnPass}"/><br>
    <input type="submit" value="로그인"/><br>
    <input type="hidden" name="loginReferer" value="${loginReferer}"/>
<br>
    ${loginFailMessage}
</form>
</body>
</html>
