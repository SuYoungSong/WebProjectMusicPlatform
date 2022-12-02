<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="../../css/bodycss.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
<div id="nav">
  <%@include file="sideNavigation.jsp"%>
</div>
<div>
  <jsp:include page = "sideController.jsp"></jsp:include>
</div>
<div id="video">
  <form action="/front/video/editProcess" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="videoNumber" value="${videoNumber}"/>
    영상 제목: <input type="text" name="videoName" value="${editVideo.videoName}"/><br>
    영상 설명: <textarea name="videoDescription">${editVideo.videoDescription}</textarea><br>
    영상 장르: <select name="genere">
    <option value="발라드" <c:if test="${editVideo.videoGenre eq '발라드'}">selected</c:if> >발라드</option>
    <option value="댄스" <c:if test="${editVideo.videoGenre eq '댄스'}">selected</c:if> >댄스</option>
    <option value="힙합" <c:if test="${editVideo.videoGenre eq '힙합'}">selected</c:if> >힙합</option>
    <option value="발라드" <c:if test="${editVideo.videoGenre eq '트로트'}">selected</c:if> >트로트</option>
    <option value="클래식" <c:if test="${editVideo.videoGenre eq '클래식'}">selected</c:if> >클래식</option>
    <option value="팝" <c:if test="${editVideo.videoGenre eq '팝'}">selected</c:if> >팝</option>
    <option value="재즈" <c:if test="${editVideo.videoGenre eq '재즈'}">selected</c:if> >재즈</option>
    <option value="블루스" <c:if test="${editVideo.videoGenre eq '블루스'}">selected</c:if> >블루스</option>
    <option value="EDM" <c:if test="${editVideo.videoGenre eq 'EDM'}">selected</c:if> >EDM</option>
    <option value="OST" <c:if test="${editVideo.videoGenre eq 'OST'}">selected</c:if> >OST</option>
    <option value="인디" <c:if test="${editVideo.videoGenre eq '인디'}">selected</c:if> >인디</option>
  </select><br>
    영상 사진:<input type="file" name="imageFile" accept="image/*"/><br>
    <input type="submit" value="영상 수정"/>
  </form>
</div>
</body>
</html>
