<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dream</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script>
        <%--var page = 1;--%>
        <%--var isNext = true;--%>
        <%--$(window).scroll(function() {--%>
        <%--    // 스크롤이 80% 이상이 되면 해당 컨텐츠가 자동 생성--%>
        <%--    if(((window.scrollY + window.innerHeight) / $('body').prop("scrollHeight") * 100) > 80)--%>
        <%--    {--%>
        <%--        if(isNext){--%>
        <%--            $.ajax({--%>
        <%--                type: "get",--%>
        <%--                url: "/api/detailMusic/callComment/${detailMusicNumber}/" + (++page),--%>
        <%--                dataType: "json"--%>
        <%--            }).done(function (result) {--%>
        <%--                var count = 0   // 더이상 음악 없으면 DB호출 막는 용도--%>
        <%--                for (var cmt of result) {--%>
        <%--                    inputItem(cmt);--%>
        <%--                    count++;--%>
        <%--                }--%>
        <%--                if (count < 1) {--%>
        <%--                    $("comment_zone").append("등록된 댓글이 없습니다.");--%>
        <%--                    isNext = false;--%>
        <%--                }--%>
        <%--                if( count<5 ){--%>
        <%--                    isNext = false;--%>
        <%--                }--%>
        <%--            }).fail(function (error) {--%>
        <%--            })--%>
        <%--        }--%>
        <%--    }--%>
        <%--});--%>
        function callMusicComment(page){
            $.ajax({
                type: "get",
                url: "/api/detailMusic/callComment/${detailMusicNumber}/" + page,
                dataType: "json"
            }).done(function (result) {
                var count = 0   // 댓글이 하나도 없는경우 문구 출력용
                for (var cmt of result) {
                    inputItem(cmt);
                    count++;
                }
                if (count < 1) {
                    $("comment_zone").append("등록된 댓글이 없습니다.");
                }
            }).fail(function (error) {
                $("comment_zone").append("댓글을 불러오는데 실패했습니다.");
            })
        }
        function inputItem(cmt) {
            // 게시글(이미지 제외) 뜨는 html 수정하려면 여기 수정하면 됌
            var string =
                "<hr><div class=\"comment\">" +
                "<div>댓글 작성자:" + cmt.writer + "</div><br>" +
                "<div>댓글 내용:" + cmt.commentText + "</div> " +
                "</div>";
            $("comment_zone").append(string);
        }
    </script>
    <link rel="stylesheet" href="/css/bodycss.css">
    <link rel="stylesheet" href="/css/deail/det_music.css">

</head>
<body>

<!-- 네비게이션 -->
<div>
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
</div>
<div class="music">
    <div class="music_image">
        <img class="musicImagIcon" src="/resources/images/${detailMusicImage.serverFilePath}"/>
        <button class="music_play_button" onclick="musicPlayButton(${detailMusicNumber})">
            <img src="/resources/images/defaultPlayImage.png"/>
        </button>
    </div>
    <div class="music_text">
        <div class="name">${detailMusicInfo.musicName}</div>
        <br>
        <div class="user">${detailMusicInfo.uploadUser}</div>
    </div>
</div>
<div class="box">
    <div class="break"><div class="name">음악 제목 </div><div class="text">${detailMusicInfo.musicName}</div></div>
    <div class="break"><div class="name">음악 업로더 </div><div class="text">${detailMusicInfo.uploadUser}</div></div>
    <div class="break"><div class="name">음악 설명 </div><div class="text">${detailMusicInfo.musicDescription}</div></div>
    <div class="break"><div class="name">음악 장르 </div><div class="text">${detailMusicInfo.genre}</div></div>
    <div class="break"><div class="name">음악 작곡가 </div><div class="text">${detailMusicInfo.songwriter}</div></div>
    <div class="break"><div class="name">음악 작사가 </div><div class="text">${detailMusicInfo.lyricwriter}</div></div>
    <div class="break"><div class="name">음악 편곡가 </div><div class="text">${detailMusicInfo.musicArranger}</div></div>
    <div class="break"><div class="name">음악 가수 </div><div class="text">${detailMusicInfo.singer}</div></div>
    <div class="break"><div class="name">음악 발매일 </div><div class="text">${detailMusicInfo.releaseDate}</div></div>
    <div class="break"><div class="name">음악 가사 </div><div class="text">${detailMusicInfo.lyrics}</div></div>
</div>

<%--로그인 한 경우 댓글 작성 가능하게--%>
<c:if test="${not empty sessionScope.loginUser}">
    <legend>
        <form action="/front/detailMusic/writeComment?musicNumber=${detailMusicNumber}" method="Post">
            <div class="writeBox">
                <textarea name="text" placeholder="내용을 입력하세요."></textarea><br>
                <input type="submit" class="writeButton" value="작성하기"/>
            </div>
        </form>
    </legend>
</c:if>

<%-- 작성된 댓글 공간 --%>
<comment_zone>
    <script>callMusicComment(0)</script>
</comment_zone>

</body>
</html>
