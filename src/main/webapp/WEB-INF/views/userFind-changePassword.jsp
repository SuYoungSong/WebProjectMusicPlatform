<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/bodycss.css">
</head>
<body>
<!-- 네비게이션 -->
<div id="nav">
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
</div>
<h2>변경할 비밀번호를 입력하세요</h2>
<form action="/front/users/userFindPChangePassword" method="post">
    아이디 : ${findId}<br>
    변경할 비밀번호 : <input type="password" name="password" value="${returnPassword}"/><br>
    변경할 비밀번호 확인 : <input type="password" name="passwordCheck" value="${returnPasswordCheck}"/><br>
    <input type="submit" value="변경하기"/>
</form>
${failChangePassword}
</body>
</html>
