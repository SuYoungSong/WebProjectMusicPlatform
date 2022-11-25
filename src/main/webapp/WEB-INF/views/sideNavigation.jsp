<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Title</title>
  <!-- <link rel="stylesheet" href="../../css/hong.css"> -->
  <link rel="stylesheet" href="../../css/navigation/navBarback.css">
  <link rel="stylesheet" href="../../css/navigation/navBar_1.css">
  <link rel="stylesheet" href="../../css/navigation/navBar_2.css">
  <link rel="stylesheet" href="../../css/navigation/navBarmenu.css">

  <style></style>
</head>
<body>

<nav class="navBarBack">
  <div class="navBarLogo">  <!-- 로고 -->
    <a href="#">
      <img src="/resources/images/defaultMusicImage.png"/>  <!-- 로고 img-->
      <b style="color: #ffffff">Logo</b>    <!-- 로고 name -->
    </a>
  </div>          <!-- /로고 -->
  <div><hr></div>
  <!-- 로그인 상태일때 보여줄 메뉴 -->
  <c:if test="${not empty sessionScope.loginUser}">
    <div class="navBarUserimg">
        <%--    아이디:${loginUser.id}<br>--%>
        <%--    이름:${loginUser.name}<br>--%>
        <%--    전화번호:${loginUser.phone}<br>--%>
      <!-- 로그인 했을시 출력문 -->
      <img src="/resources/images/${userProfileImage.serverFilePath}"/> <!-- 로그인시 여기에 이미지 삽입! -->
    </div>
    <div style="color: white; text-align: center;">${loginUser.nickname}님 환영합니다<br></div>
    <div class ="navBarOut">
      <a href="/front/logout">로그아웃</a>
    </div>
  </c:if>

  <!-- 비로그인 상태일때 보여줄 메뉴 -->
  <c:if test="${empty sessionScope.loginUser}">
    <div class="navBarOut"> <!-- 로그아웃상태 출력문 -->
      <a href="/front/login">로그인</a>
      <a href="/front/users/register">회원가입</a>
      <a href="/front/users/userFind">아이디/비밀번호 찾기</a>
    </div>
  </c:if>
  <div><hr></div>
  <div class="navBarMenu">
    <a href="/front/music">음악</a>
    <a href="/front/video">영상</a>
    <a href="/front/board">소식통</a>
    <br>
    <br>
    <hr>
    <a href="/front/musicVideoFileUpload">음악/영상 업로드</a>
  </div>
  <!-- 만드는중 -->
</nav>
</body>
</html>