<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>index</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script>
        function callRecentlyMusic5(page, tag){
            $.ajax({
                type: "get",
                url: "/api/music/callMusic5/" + page,
                dataType: "json"
            }).done(function (result) {
                var count = 0   // 음악이 하나도 없는경우 문구 출력용
                Object.keys(result).sort().reverse().map(function (key) {
                    inputItem(result[key], key,tag);
                    count++;
                });
                for ( let i = 0 ; i < 5-count ; i++ ){
                    inputNoneItem(tag)
                }
            }).fail(function (error) {
                for ( let i = 0 ; i < 5 ; i++ ){
                    inputNoneItem(tag)
                }
                console.log("음악 로딩 실패")
            })
        }
        function inputItem(result, key,tag) {
            // 게시글(이미지 제외) 뜨는 html 수정하려면 여기 수정하면 됌
            var string =
                "<div class=\"musicBox\">"+
                    callingMusicImage(key) +
                    "<a class=\"music_a_tag\" href=/front/detailMusic?no="+ key +">" +
                    "<div class=\"music_name\">" + result.musicName + "</div>"+
                    "<div class=\"music_singer\">" + result.singer + "</div>"+
                    "</a>"+
                    "</div>" ;
            $(tag).append(string);
        }

        function inputNoneItem(tag) {
            // 게시글(이미지 제외) 뜨는 html 수정하려면 여기 수정하면 됌
            var string =
                "<div class=\"musicBox\">"+
                "<div class=\"music_image\">"+
                "<img  class=\"musicImagIcon\" src=\"/resources/images/defaultMusicImage.png\"/>"+
                "</div>" +
                "<a class=\"music_a_tag\">" +
                "<div class=\"music_name\">등록된 음악이 없습니다</div>"+
                "<div class=\"music_singer\">미등록</div>"+
                "</div>"+
                "</a>" ;
            $(tag).append(string);
        }


        function callingMusicImage(key){
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
                    "<img  class=\"musicImagIcon\" src=\"/resources/images/"+result.serverFilePath + "\"/>"+
                    "<button class=\"music_play_button\" onclick='musicPlayButton("+key+")'><img src=\"/resources/images/defaultPlayImage.png\"/></button>" +
                    "</div>";
            }).fail(function (error) {
            })
            return string;
        }

    </script>
    <link rel="stylesheet" href="/css/bodycss.css">
    <link rel="stylesheet" href="/css/index/inGenre.css">
    <link rel="stylesheet" href="/css/index/inLatest.css">
   <style>
        .music_play_button{
            position: absolute;
            background-color: transparent;
            border: 0px;
            left:2px;
            top:3px;
            width: 70px;
            height: 70px;
            display:none;
        }
        .music_a_tag{
            padding-top:15px;
            text-decoration: none;
        }
        .music_a_tag:hover .music_name{
            color:#007bff;
        }
        .music_a_tag:hover .music_singer{
            color:#6c757d;
        }
        .music_singer{
            color:#9d9d9d;
        }

        .music_image:hover .music_play_button{
            cursor: pointer;
            display: block;
            opacity: 100%;
        }
        .music_image:hover .musicImagIcon{
            opacity: 0.5;
        }
        .genre_music{
            width:100px;
            height:50px;
            justify-content: center;
            height:50px;
            line-height:50px;
        }
        .musicBox{
            display: flex;
            position: static;
            margin-bottom: 10px;
        }
        .music_image{
            position: relative;
            padding-right: 90px;
            padding-bottom: 75px;
        }
        .music_image img{
            position: absolute;
            width:75px;
            height:75px;
            object-fit:cover;
        }
        .music_play_button img{
            position: absolute;
            top:25%;
            left:30%;
            width:35px;
            height:35px;
        }
        recently_music_zone{
            display:flex;
            flex-direction: row;
            flex-wrap: wrap;
        }
        leftPane{
            display:flex;
            flex-direction: column;
            width:500px;
        }
        middlePane{
            display:flex;
            flex-direction: column;
            width:500px;
        }
        rightPane{
            display:flex;
            flex-direction: column;
            width:500px;
        }
    </style>

</head>
<body>
<!-- 네비게이션 -->
<div>
    <jsp:include page = "sideNavigation.jsp"></jsp:include>
</div>
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
</div>
<div>
    <jsp:include page= "sideSearch.jsp"></jsp:include>
</div>

<script>
    callRecentlyMusic5(1,"leftPane");
    callRecentlyMusic5(2,"middlePane");
    callRecentlyMusic5(3,"rightPane");
</script>

<div class="inLatest">
    <%--    최신음악 들어있는 공간  --%>
    <h1><a href="/front/temp?nextPage=recentlyMusic">최신 음악</a><br></h1><br>
    <recently_music_zone>
        <leftPane></leftPane>
        <middlePane></middlePane>
        <rightPane></rightPane>

    </recently_music_zone>
<div>
    <h1>장르 음악</h1><br>
    <div class="inGenre">
        <button type="button" class="genre_music" onclick="location.href='/front/genereMusic?genere=발라드' "><label>발라드</label></button>
        <button type="button" class="genre_music" onclick="location.href='/front/genereMusic?genere=댄스' "><label>댄스</label></button>
        <button type="button" class="genre_music" onclick="location.href='/front/genereMusic?genere=힙합' "><label>힙합</label></button>
        <button type="button" class="genre_music" onclick="location.href='/front/genereMusic?genere=트로트' "><label>트로트</label></button>
        <button type="button" class="genre_music" onclick="location.href='/front/genereMusic?genere=클래식' "><label>클래식</label></button>
        <button type="button" class="genre_music" onclick="location.href='/front/genereMusic?genere=팝' "><label>팝</label></button>
        <button type="button" class="genre_music" onclick="location.href='/front/genereMusic?genere=재즈' "><label>재즈</label></button>
        <button type="button" class="genre_music" onclick="location.href='/front/genereMusic?genere=블루스' "><label>블루스</label></button>
        <button type="button" class="genre_music" onclick="location.href='/front/genereMusic?genere=EDM' "><label>EDM</label></button>
        <button type="button" class="genre_music" onclick="location.href='/front/genereMusic?genere=OST' "><label>OST</label></button>
        <button type="button" class="genre_music" onclick="location.href='/front/genereMusic?genere=인디' "><label>인디</label></button>
    </div>
</div>
</div>

</body>

</html>
