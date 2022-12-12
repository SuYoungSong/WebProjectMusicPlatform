<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Dream</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <link rel="stylesheet" href="/css/bodycss.css">
    <link rel="stylesheet" href="/css/search/search_box.css">
</head>
<body>

<c:if test="${not empty searchErrorMessage}">
    <h1>${searchErrorMessage}</h1>
</c:if>

<c:if test="${empty searchErrorMessage}">
    <h1>${searchType}: ${inputText} 관련 검색 결과 입니다.</h1>
</c:if>
<div>
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
</div>
<div class="tab-value">
    <input class="radio_none" id="searchMusic" type="radio" name="tabs" checked>
    <label for="searchMusic">음악</label>
    <input class="radio_none" id="searchVideo" type="radio" name="tabs">
    <label for="searchVideo">비디오</label>

    <section id="searchMusic-content">
        <searchZone>
        <c:if test="${not empty requestScope.searchMusics}">
            <c:forEach var="music" items="${searchMusics}" varStatus="status">
                <div class="musicBox">
                    <div class="music_image">
                        <img  class="musicImagIcon" src="/resources/images/${searchMusicImages[music.key].serverFilePath}">
                        <button class="music_play_button" onclick='musicPlayButton("${music.key}")'><img src="/resources/images/defaultPlayImage.png"/></button>
                    </div>
                    <a class="music_a_tag" href="/front/detailMusic?no=${music.key}">
                    <div class="music_name">${music.value.musicName}</div>
                    <div class="music_singer">${music.value.singer}</div>
                    </a>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty requestScope.searchMusics}">
            <h1>검색 결과가 없습니다.</h1>
        </c:if>
        </searchZone>
    </section>

    <section id="searchVideo-content">
        <searchZone>
        <c:if test="${not empty requestScope.searchVideos}">
            <c:forEach var="video" items="${searchVideos}" varStatus="status">
                <div class="videoBox">
                    <a class="video_a_tag" href="/front/detailVideo?no=${video.key}">
                        <div class="video_image">
                            <img  src="/resources/images/${searchVideoImages[video.key].serverFilePath}"/>
                        </div>
                        <div class="video_text">
                            <div class="video_name">${video.value.videoName}</div>
                            <div class="video_uploder">${video.value.uploadUserId}</div>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty requestScope.searchVideos}">
            <h2>검색 결과가 없습니다.</h2>
        </c:if>
        </searchZone>
    </section>
</div>
</body>
</html>
