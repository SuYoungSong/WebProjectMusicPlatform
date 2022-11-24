<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>index</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script>
        function callRecentlyMusic10(page){
            $.ajax({
                type: "get",
                url: "/api/music/callMusic10/" + page,
                dataType: "json"
            }).done(function (result) {
                var count = 0   // 음악이 하나도 없는경우 문구 출력용
                Object.keys(result).map(function (key) {
                    inputItem(result[key], key);
                    count++;
                });
                if(count<1){
                    $("recently_music_zone").append("등록된 음악이 없습니다.");
                }
            }).fail(function (error) {
                $("recently_music_zone").append("음악을 불러오는데 실패했습니다.");
            })
        }
        function inputItem(result, key) {
            // 게시글(이미지 제외) 뜨는 html 수정하려면 여기 수정하면 됌
            var string =
                "<div class=\"musicBox\">"+
                callMusicImage(key) +
                "<div class=\"music_name\">제목:" + result.musicName + "</div>"+
                "<div class=\"music_singer\">가수:" + result.singer + "</div>"+
                "<div class=\"music_genere\">장르:" + result.genre + "</div>"+
                "</div>";
            $("recently_music_zone").append(string);
        }

        function callMusicImage(key){
            // 이미지 뜨는 html 수정하려면 여기 수정하면 됌
            var string = ""
            $.ajax({
                type: "get",
                url: "/api/music/image/" + (key),
                async: false,
                dataType: "json"
            }).done(function (result) {
                string +=
                    "<div class=\"music_image\">"+
                    "<img  src=\"/resources/images/"+result.serverFilePath + "\"/>"+
                    "</div>";
            }).fail(function (error) {
            })
            return string;
        }
    </script>
    <style>
        .genre_music{
             width:100px;
             height:50px;
             flex-direction: row;
             justify-content: center;
        }
        body{
            margin-left:210px;
            margin-bottom: 120px;
        }
        .musicBox{
            display: flex;
            position: static;
        }

        recently_music_zone{
            width: 100%;
            display: flex;
            flex-direction: column;
            margin-top: 50px;
        }

        .music_image img{
            width:50px;
            height:50px;
            object-fit:cover;
        }
    </style>
</head>
<body>
<!-- 네비게이션 -->
<div>
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <%@include file="sideController.jsp"%>
</div>
<div class="viewZone">
    <%--    최신음악 들어있는 공간  --%>
    <recently_music_zone class="recently_music_zone">
    <h2>최신 음악</h2><br>
    <script>
        callRecentlyMusic10(1);
    </script>
    </recently_music_zone>

    <h2>장르 음악</h2><br>
    <button type="button" class="genre_music" onclick="location.href='#' ">발라드</button>
    <button type="button" class="genre_music" onclick="location.href='#' ">댄스</button>
    <button type="button" class="genre_music" onclick="location.href='#' ">힙합</button>
    <button type="button" class="genre_music" onclick="location.href='#' ">트로트</button>
    <button type="button" class="genre_music" onclick="location.href='#' ">클래식</button>
    <button type="button" class="genre_music" onclick="location.href='#' ">팝</button>
    <button type="button" class="genre_music" onclick="location.href='#' ">재즈</button>
    <button type="button" class="genre_music" onclick="location.href='#' ">블루스</button>
    <button type="button" class="genre_music" onclick="location.href='#' ">EDM</button>
    <button type="button" class="genre_music" onclick="location.href='#' ">OST</button>
    <button type="button" class="genre_music" onclick="location.href='#' ">인디</button>

    <h2>인기 음악</h2><br>
    고려중

</div>

</body>

</html>
