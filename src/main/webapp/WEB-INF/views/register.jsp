<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="../../css/bodycss.css">
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
  <form action="/front/users/save" method="post" enctype="multipart/form-data">
    아이디: <input type="text" name="id" value="${returnId}" maxlength="20"/><br>
    비밀번호: <input type="password" name="password" value="${returnPassword}" maxlength="20"/><br>
    비밀번호 확인: <input type="password" name="passwordCheck" value="${returnPasswordCheck}" maxlength="20"/><br>
    연락처: <input type="text" name="phoneFirst" value="${returnPhoneFirst}" maxlength="3"/>-<input type="text" name="phoneSecond" value="${returnPhoneSecond}" maxlength="4"/>-<input type="text" name="phoneThird"value="${returnPhoneThird}" maxlength="4"/><br>
    이름: <input type="text" name="name" value="${returnName}"maxlength="10"/><br>
    닉네임: <input type="text" name="nickname" value="${returnName}" maxlength="10"/><br>
    프로필사진: <input type="file" name="profileImage" accept="image/*"/><br>
    <input type="submit" value="회원가입"/><br>
    <br>
    ${failRegisterMessage}
  </form>
</body>
</html>
