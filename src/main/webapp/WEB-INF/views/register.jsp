<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Dream</title>
  <link rel="stylesheet" href="/css/bodycss.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <link rel="stylesheet" href="/css/register/register.css">
</head>
<body>
<!-- 네비게이션 -->
<div id="nav">
  <%@include file="sideNavigation.jsp"%>
</div>
<div>
  <jsp:include page = "sideController.jsp"></jsp:include>
</div>
<div class="reg">
  <form action="/front/users/save" method="post" enctype="multipart/form-data">
    <div class="box"><div class="text">아이디: </div>       <div class="qv"><input type="text" name="id" value="${returnId}" maxlength="20"/></div></div>
    <div class="box"><div class="text">비밀번호: </div>      <div class="qv"><input type="password" name="password" value="${returnPassword}" maxlength="20"/></div></div>
    <div class="box"><div class="text">비밀번호 확인: </div>  <div class="qv"><input type="password" name="passwordCheck" value="${returnPasswordCheck}" maxlength="20"/></div></div>
    <div class="box"><div class="text">연락처: </div>        <div class="qv"><input type="text" name="phoneFirst" value="${returnPhoneFirst}" maxlength="3"/>-<input type="text" name="phoneSecond" value="${returnPhoneSecond}" maxlength="4"/>-<input type="text" name="phoneThird"value="${returnPhoneThird}" maxlength="4"/></div></div>
    <div class="box"><div class="text">이름: </div>         <div class="qv"><input type="text" name="name" value="${returnName}"maxlength="10"/></div></div>
    <div class="box"><div class="text">닉네임: </div>        <div class="qv"><input type="text" name="nickname" value="${returnName}" maxlength="10"/></div></div>
    <div class="box"><div class="text">프로필사진: </div>     <div class="qv"><input type="file" name="profileImage" accept="image/*"/></div></div>
    <div class="box"><div class="text"></div>               <div class="qvv"><input type="submit" value="회원가입"/></div></div>
    <br>
    ${failRegisterMessage}
  </form>
</div>
</body>
</html>
