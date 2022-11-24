<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script>
        function callRecentlyVideo10(page){
            $.ajax({
                type: "get",
                url: "/api/video/callVideo10/" + page,
                dataType: "json"
            }).done(function (result) {
                var count = 0   // 영상이 하나도 없는경우 문구 출력용
                Object.keys(result).map(function (key) {
                    inputItem(result[key], key);
                    count++;
                });
                if(count<1){
                    $("recently_video_zone").append("등록된 영상이 없습니다.");
                }
            }).fail(function (error) {
                $("recently_video_zone").append("영상을 불러오는데 실패했습니다.");
            })
        }
        function inputItem(result, key) {
            // 게시글(이미지 제외) 뜨는 html 수정하려면 여기 수정하면 됌
            var string =
                "<div class=\"videoBox\">"+
                callVideoImage(key) +
                "<div class=\"video_name\">제목:" + result.videoName + "</div>"+
                "<div class=\"video_uploder\">가수:" + result.uploadUserId + "</div>"+
                "</div>";
            $("recently_video_zone").append(string);
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
    <style>
        .genre_video{
            width:100px;
            height:50px;
            flex-direction: row;
            justify-content: center;
        }
        body{
            margin-left:210px;
            margin-bottom: 120px;
        }
        .videoBox{
            display: flex;
            position: static;
        }

        recently_video_zone{
            width: 100%;
            display: flex;
            flex-direction: column;
            margin-top: 50px;
        }

        .video_image img{
            width:50px;
            height:50px;
            object-fit:cover;
        }
    </style>
</head>
<body>
<!-- 네비게이션 -->
<div id="nav">
  <%@include file="sideNavigation.jsp"%>
</div>
<%--    최신영ㅇ상 들어있는 공간  --%>
<recently_video_zone class="recently_video_zone">
    <h2><a href="/front/temp?nextPage=recentlyVideo">최신 영상</a></h2><br>
    <script>
        callRecentlyVideo10(1);
    </script>
</recently_video_zone>

<h2>장르 영상</h2><br>
<button type="button" class="genre_music" onclick="location.href='/front/genereVideo?genere=발라드' ">발라드</button>
<button type="button" class="genre_music" onclick="location.href='/front/genereVideo?genere=댄스' ">댄스</button>
<button type="button" class="genre_music" onclick="location.href='/front/genereVideo?genere=힙합' ">힙합</button>
<button type="button" class="genre_music" onclick="location.href='/front/genereVideo?genere=트로트' ">트로트</button>
<button type="button" class="genre_music" onclick="location.href='/front/genereVideo?genere=클래식' ">클래식</button>
<button type="button" class="genre_music" onclick="location.href='/front/genereVideo?genere=팝' ">팝</button>
<button type="button" class="genre_music" onclick="location.href='/front/genereVideo?genere=재즈' ">재즈</button>
<button type="button" class="genre_music" onclick="location.href='/front/genereVideo?genere=블루스' ">블루스</button>
<button type="button" class="genre_music" onclick="location.href='/front/genereVideo?genere=EDM' ">EDM</button>
<button type="button" class="genre_music" onclick="location.href='/front/genereVideo?genere=OST' ">OST</button>
<button type="button" class="genre_music" onclick="location.href='/front/genereVideo?genere=인디' ">인디</button>

<h2>인기 영상</h2><br>
고려중

</div>


</body>
</html>
