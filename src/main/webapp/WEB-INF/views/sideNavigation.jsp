<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
  <style>
    .navBar {
      height: 100%;
      width: 200px;
      position: fixed;
      top: 0;
      left: 0;
      overflow: auto;
      background-color: #B5B9BF;   /* 색 조정 바람 */
      display: flex;
      z-index: 2;
      flex-direction: column;
      padding-bottom: 100px;
    }
  </style>
</head>
<body>

<nav class="navBar" id="navBar">
  <c:if test="${not empty sessionScope.loginUser}">
    <!-- 로그인 상태일때 보여줄 메뉴 -->
    로그인 정보<br>
    <img src="/resources/images/${userProfileImage.serverFilePath}"/><br>
    아이디:${loginUser.id}<br>
    이름:${loginUser.name}<br>
    닉네임:${loginUser.nickname}<br>
    전화번호:${loginUser.phone}<br>
  </c:if>
  <c:if test="${empty sessionScope.loginUser}">
    <!-- 비로그인 상태일때 보여줄 메뉴 -->
    로그인 안했음.
  </c:if>

  <h3>메뉴</h3><br>
  <a href="/front/musicVideoFileUpload">업로드</a><br>
  <a href="/front/login">로그인</a><br>
  <a href="/front/logout">로그아웃</a><br>
  <a href="/front/users/register">회원가입</a><br>
  <a href="/front/users/userFind">아이디/비밀번호 찾기</a><br>
  <a href="/front/board">게시판</a><br>
</nav>
</body>
</html>
