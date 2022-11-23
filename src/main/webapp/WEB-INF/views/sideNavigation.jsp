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
  <c:if test="${not empty sessionScope.loginUser}">
    <!-- 로그인 상태일때 보여줄 메뉴 -->
    로그인 정보<br>
    <img src="../../resources/images/${userProfileImage.serverFilePath}"/><br>
    아이디:${loginUser.id}<br>
    이름:${loginUser.name}<br>
    닉네임:${loginUser.nickname}<br>
    전화번호:${loginUser.phone}<br>
  </c:if>
  <c:if test="${empty sessionScope.loginUser}">
    <!-- 비로그인 상태일때 보여줄 메뉴 -->
    로그인 안했음.
  </c:if>

  <div>
    <div class="navBarUser">
      <div class="navBarUserimg"> <!-- 로그인 했을시 출력문 -->
        <img src="../../resources/images/defaultMusicImage.png"/> <!-- 로그인시 여기에 이미지 삽입! -->
      </div>
    </div>
    <hr> <!------- -->

    <div class="navBarOut"> <!-- 로그아웃상태 출력문 -->
      <a href="/front/login">로그인</a>
      <a href="/front/users/register">회원가입</a>
    </div>

    <hr>
    <div class="navBarMenu">
      <a href="/front/musicVideoFileUpload">업로드</a>
      <a href="/front/login">로그인</a>
      <a href="/front/logout">로그아웃</a>
      <a href="/front/users/register">회원가입</a>
      <a href="/front/users/userFind">아이디/비밀번호 찾기</a>
      <a href="/front/board">게시판</a>
    </div>
  </div><!-- 만드는중 -->

</nav>
</body>
</html>
