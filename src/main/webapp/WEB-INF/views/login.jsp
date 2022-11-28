<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            margin-left:210px;
            margin-bottom: 120px;
            background-color: #0a0a0a;
        }
    </style>
    <link rel="stylesheet" href="../../css/login/logBack.css">
</head>
<div>
<!-- 네비게이션 -->
    <div>
        <%@include file="sideNavigation.jsp"%>
    </div>
    <div>
        <%@include file="sideController.jsp"%>
    </div>
<div class="log">
    <div class="logBack">
        <form action="/front/loginProcess" method="POST">
            <div class="logId">
                <input type="text" name="id" placeholder="아이디:" value="${returnId}"/>
                <input type="password" name="pass" placeholder="비밀번호:" value="${returnPass}"/>
            </div>
            <div class="sub">
                <input type="submit" value="로그인"/>
            </div>
            <br>
            <input type="hidden" name="loginReferer" value="${loginReferer}"/>
        <br>
            ${loginFailMessage}
        </form>
    </div>
</div>
</body>
</html>
