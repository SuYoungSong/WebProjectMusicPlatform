<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>index</title>
</head>
<body>
<h1>index page</h1><br>
<h1>홍 브런치</h1>
<c:if test="${not empty sessionScope.loginUser}">
    로그인 정보<br>
    아이디:${loginUser.id}<br>
    이름:${loginUser.name}<br>
    닉네임:${loginUser.nickname}<br>
    전화번호:${loginUser.phone}<br>
</c:if>
<c:if test="${empty sessionScope.loginUser}">
    로그인 안했음.
</c:if>

<h3>메뉴</h3><br>
<a href="/front/musicVideoFileUpload">업로드</a><br>
<a href="/front/login">로그인</a><br>
<a href="/front/logout">로그아웃</a><br>
<a href="/front/users/register">회원가입</a><br>

</body>
</html>
