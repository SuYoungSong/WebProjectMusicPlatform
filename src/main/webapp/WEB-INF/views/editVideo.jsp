<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Dream</title>
  <link rel="stylesheet" href="/css/bodycss.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <link rel="stylesheet" href="/css/edit/video.css">
</head>
<body>
<div id="nav">
  <%@include file="sideNavigation.jsp"%>
</div>
<div>
  <jsp:include page = "sideController.jsp"></jsp:include>
</div>
<div id="video" class="edit">
  <form action="/front/video/editProcess" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="videoNumber" value="${videoNumber}"/>
    <div class="edit_box"><div class="text">영상 제목: </div><div class="qv"><input type="text" name="videoName" value="${editVideo.videoName}"/></div></div>
    <div class="edit_box"><div class="text">영상 설명: </div><div class="qv"><textarea name="videoDescription">${editVideo.videoDescription}</textarea></div></div>
      <div class="edit_box"><div class="text">영상 장르: </div><div class="qv"><select name="genere">
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
      </select></div></div>
    <div class="edit_box"><div class="text">영상 사진: </div><div class="qv"><input type="file" name="imageFile" accept="image/*"/></div></div>
    <input class="edit_sub" type="submit" value="영상 수정"/>
  </form>
</div>
</body>
</html>
