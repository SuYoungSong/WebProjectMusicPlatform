<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
  <title>Dream</title>
  <link rel="stylesheet" href="/css/bodycss.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <link rel="stylesheet" href="/css/edit/music.css">
</head>
<body>
<div id="nav">
  <%@include file="sideNavigation.jsp"%>
</div>
<div>
  <jsp:include page = "sideController.jsp"></jsp:include>
</div>
<div id="music" class="edit">
  <form action="/front/music/editProcess" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="musicNumber" value="${musicNumber}"/>
    <div class="edit_box"><div class="text">음악 제목: </div><div class="qv"><input type="text" name="musicName" value="${editMusic.musicName}"/></div></div>
    <div class="edit_box"><div class="text">음악 설명: </div><div class="qv"><textarea name="musicDescription">${editMusic.musicDescription}</textarea></div></div>
      <div class="edit_box"><div class="text">음악 장르: </div><div class="qv"><select name="genre" >
    <option value="발라드" <c:if test="${editMusic.genre eq '발라드'}">selected</c:if> >발라드</option>
    <option value="댄스" <c:if test="${editMusic.genre eq '댄스'}">selected</c:if> >댄스</option>
    <option value="힙합" <c:if test="${editMusic.genre eq '힙합'}">selected</c:if> >힙합</option>
    <option value="발라드" <c:if test="${editMusic.genre eq '트로트'}">selected</c:if> >트로트</option>
    <option value="클래식" <c:if test="${editMusic.genre eq '클래식'}">selected</c:if> >클래식</option>
    <option value="팝" <c:if test="${editMusic.genre eq '팝'}">selected</c:if> >팝</option>
    <option value="재즈" <c:if test="${editMusic.genre eq '재즈'}">selected</c:if> >재즈</option>
    <option value="블루스" <c:if test="${editMusic.genre eq '블루스'}">selected</c:if> >블루스</option>
    <option value="EDM" <c:if test="${editMusic.genre eq 'EDM'}">selected</c:if> >EDM</option>
    <option value="OST" <c:if test="${editMusic.genre eq 'OST'}">selected</c:if> >OST</option>
    <option value="인디" <c:if test="${editMusic.genre eq '인디'}">selected</c:if> >인디</option>
      </select></div></div>
    <div class="edit_box"><div class="text">음악 가사: </div><div class="qv"><textarea name="lyrics">${editMusic.lyrics}</textarea></div></div>
    <div class="edit_box"><div class="text">음악 가수: </div><div class="qv"><input type="text" name="singer" value="${editMusic.singer}"/></div></div>
    <div class="edit_box"><div class="text">음악 작곡가: </div><div class="qv"><input type="text" name="songwriter" value="${editMusic.songwriter}"/></div></div>
    <div class="edit_box"><div class="text">음악 작사가: </div><div class="qv"><input type="text" name="lyricwriter" value="${editMusic.lyricwriter}"/></div></div>
    <div class="edit_box"><div class="text">음악 편곡가: </div><div class="qv"><input type="text" name="musicArranger" value="${editMusic.musicArranger}"/></div></div>
    <c:set var="releaseDate" value="${fn:split(editMusic.releaseDate, ' ')}" />
    <div class="edit_box"><div class="text">음악 발매일: </div><div class="qv"><input type="date" name="releaseDate" value="${releaseDate[0]}"/></div></div>
    <div class="edit_box"><div class="text">음악 사진: </div><div class="qv"><input type="file" name="imageFile" accept="image/*"/></div></div>
    <input class="edit_sub" type="submit" value="음악 수정"/>
  </form>
</div>

</body>
</html>
