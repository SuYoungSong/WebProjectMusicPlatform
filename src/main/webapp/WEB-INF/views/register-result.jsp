<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

${user.name}님 가입을 환영합니다<br>
<img src="../../resources/images/${userProfileImage.serverFilePath}"/><br>
아이디 : ${user.id}<br>
닉네임: ${user.nickname}<br>
연락처: ${user.phone}<br>

</body>
</html>
