<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!-- 네비게이션 -->
<div>
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <%@include file="sideController.jsp"%>
</div>


<img src="/resources/images/${detailMusicImage.serverFilePath}"/><br>
음악 제목: ${detailMusicInfo.musicName}<br>
음악 업로더: ${detailMusicInfo.uploadUser}<br>
음악 설명: ${detailMusicInfo.musicDescription}<br>
음악 장르: ${detailMusicInfo.genre}<br>
음악 가사: ${detailMusicInfo.lyrics}<br>
음악 작곡가: ${detailMusicInfo.songwriter}<br>
음악 작사가: ${detailMusicInfo.lyricwriter}<br>
음악 편곡가: ${detailMusicInfo.musicArranger}<br>
음악 가수: ${detailMusicInfo.singer}<br>
음악 발매일: ${detailMusicInfo.releaseDate}<br>
</body>
</html>
