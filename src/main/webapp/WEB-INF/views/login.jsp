<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/bodycss.css">
    <link rel="stylesheet" href="/css/login/logBack.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
    <!-- 네비게이션 -->
    <div>
        <%@include file="sideNavigation.jsp"%>
    </div>
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
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
        </form>
    </div>
    <div class="loginFail">
        ${loginFailMessage}
    </div>
</div>
</body>
</html>