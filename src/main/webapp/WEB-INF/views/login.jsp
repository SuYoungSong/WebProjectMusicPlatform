<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
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
