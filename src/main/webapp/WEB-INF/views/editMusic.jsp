<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
    <style>
      body{
        margin-left:210px;
        margin-bottom: 120px;
      }
    </style>
</head>
<body>
<div id="nav">
  <%@include file="sideNavigation.jsp"%>
</div>
<div>
  <%@include file="sideController.jsp"%>
</div>
<div id="music">
  <form action="/front/music/editProcess" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="musicNumber" value="${musicNumber}"/>
    음악 제목: <input type="text" name="musicName" value="${editMusic.musicName}"/><br>
    음악 설명: <textarea name="musicDescription">${editMusic.musicDescription}</textarea><br>
    음악 장르: <select name="genre" >
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
  </select><br>
    음악 가사: <textarea name="lyrics">${editMusic.lyrics}</textarea><br>
    음악 가수: <input type="text" name="singer" value="${editMusic.singer}"/><br>
    음악 작곡가: <input type="text" name="songwriter" value="${editMusic.songwriter}"/><br>
    음악 작사가: <input type="text" name="lyricwriter" value="${editMusic.lyricwriter}"/><br>
    음악 편곡가: <input type="text" name="musicArranger" value="${editMusic.musicArranger}"/><br>
    <c:set var="releaseDate" value="${fn:split(editMusic.releaseDate, ' ')}" />
    음악 발매일: <input type="date" name="releaseDate" value="${releaseDate[0]}"/><br>
    음악 사진:<input type="file" name="imageFile" accept="image/*"/><br>
    <input type="submit" value="음악 수정"/>
  </form>
</div>

</body>
</html>
