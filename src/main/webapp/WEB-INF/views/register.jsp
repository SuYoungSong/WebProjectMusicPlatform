<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form action="save" method="post">
    아이디: <input type="text" name="id"/><br>
    비밀번호: <input type="password" name="password"/><br>
    연락처: <input type="text" name="phoneFirst"/>-<input type="text" name="phoneSecond"/>-<input type="text" name="phoneThird"/><br>
    이름: <input type="text" name="name"/><br>
    닉네임: <input type="text" name="nickname"/><br>
    프로필사진: <input type="file" name="profileImageUrl"/><br>
    <input type="submit" value="회원가입"/><br>

  </form>
</body>
</html>
