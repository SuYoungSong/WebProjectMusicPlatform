<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script>
        function callRecentlyVideo5(page,tag){
            $.ajax({
                type: "get",
                url: "/api/video/callVideo5/" + page,
                dataType: "json"
            }).done(function (result) {
                var count = 0   // 영상이 하나도 없는경우 문구 출력용
                Object.keys(result).map(function (key) {
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
        function inputItem(result, key, tag) {
            // 게시글(이미지 제외) 뜨는 html 수정하려면 여기 수정하면 됌
            var string =
                "<div class=\"videoBox\">"+
                    "<a class=\"video_a_tag\" href=/front/detailVideo?no="+ key +">" +
                        callVideoImage(key) +
                    "<div class=\"video_text\">" +
                        "<div class=\"video_name\">" + result.videoName + "</div>"+
                        "<div class=\"video_uploder\">" + result.uploadUserId + "</div>"+
                    "</div>" +
                    "</a>" +
                "</div>";

            $(tag).append(string);
        }
        function inputNoneItem(tag) {
            // 게시글(이미지 제외) 뜨는 html 수정하려면 여기 수정하면 됌
            var string =
                "<div class=\"videoBox\">"+
                    "<a class=\"video_a_tag\">" +
                        "<div class=\"video_image\">"+
                            "<img  src=\"/resources/images/defaultVideoImage.png\"/>"+
                        "</div>" +
                        "<div class=\"video_text\">" +
                            "<div class=\"video_name\">등록된 영상이 없습니다</div>"+
                            "<div class=\"video_uploder\">미등록</div>"+
                        "</div>" +
                    "</a>" +
                "</div>";


            $(tag).append(string);
        }

        function callVideoImage(key){
            // 이미지 뜨는 html 수정하려면 여기 수정하면 됌
            var string = ""
            $.ajax({
                type: "get",
                url: "/api/video/image/" + (key),
                async: false,
                dataType: "json"
            }).done(function (result) {
                string +=
                    "<div class=\"video_image\">"+
                    "<img  src=\"/resources/images/"+result.serverFilePath + "\"/>"+
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
        .video_text{
            display: flex;
            flex-direction: column;
        }
        .video_a_tag{
            padding-top:15px;
            text-decoration: none;
            display: flex;
            flex-direction: row;
        }
        .videoBox:hover .video_name{
            color:#007bff;

        }
        .videoBox:hover .video_uploder{
            color:#6c757d;
        }
        .video_uploder{
            color:#9d9d9d;
        }
        .videoBox:hover img{
            opacity: 0.6;
        }
        .genre_video{
            width:100px;
            height:50px;
            justify-content: center;
            height:50px;
            line-height:50px;
        }
        .videoBox{
            display: flex;
            /*flex-direction: row;*/
            position: static;
            margin-bottom: 10px;
        }
        .video_image{
            position: relative;
            padding-right: 90px;
            padding-bottom: 75px;
        }
        .video_image img{
            position: absolute;
            width:75px;
            height:75px;
            object-fit:cover;
        }
        recently_video_zone{
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
<div id="nav">
  <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
</div>

<script>
    callRecentlyVideo5(1,"leftPane");
    callRecentlyVideo5(2,"middlePane");
    callRecentlyVideo5(3,"rightPane");
</script>
<div class="inLatest">
<%--    최신영ㅇ상 들어있는 공간  --%>
<h1><a href="/front/temp?nextPage=recentlyVideo">최신 영상</a></h1><br>
<recently_video_zone>
    <leftPane></leftPane>
    <middlePane></middlePane>
    <rightPane></rightPane>
</recently_video_zone>
</div>
<div>
    <h1>장르 영상</h1><br>
    <div class="inGenre">
        <button type="button" class="genre_video" onclick="location.href='/front/genereVideo?genere=발라드' ">발라드</button>
        <button type="button" class="genre_video" onclick="location.href='/front/genereVideo?genere=댄스' ">댄스</button>
        <button type="button" class="genre_video" onclick="location.href='/front/genereVideo?genere=힙합' ">힙합</button>
        <button type="button" class="genre_video" onclick="location.href='/front/genereVideo?genere=트로트' ">트로트</button>
        <button type="button" class="genre_video" onclick="location.href='/front/genereVideo?genere=클래식' ">클래식</button>
        <button type="button" class="genre_video" onclick="location.href='/front/genereVideo?genere=팝' ">팝</button>
        <button type="button" class="genre_video" onclick="location.href='/front/genereVideo?genere=재즈' ">재즈</button>
        <button type="button" class="genre_video" onclick="location.href='/front/genereVideo?genere=블루스' ">블루스</button>
        <button type="button" class="genre_video" onclick="location.href='/front/genereVideo?genere=EDM' ">EDM</button>
        <button type="button" class="genre_video" onclick="location.href='/front/genereVideo?genere=OST' ">OST</button>
        <button type="button" class="genre_video" onclick="location.href='/front/genereVideo?genere=인디' ">인디</button>
    </div>
</div>


</body>
</html>
