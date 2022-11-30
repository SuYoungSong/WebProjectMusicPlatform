<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
    var page = 0;
    var isNext = true;
    $(window).scroll(function() {
        // 스크롤이 80% 이상이 되면 해당 컨텐츠가 자동 생성
        if(((window.scrollY + window.innerHeight) / $('body').prop("scrollHeight") * 100) > 80)
        {
            if(isNext){
                $.ajax({
                    type: "get",
                    url: "/api/video/genre/${genere}/" + (++page),
                    dataType: "json"
                }).done(function (result) {
                    var count = 0   // 더이상 게시글이 없으면 DB호출 막는 용도
                    Object.keys(result).map(function (key) {
                        inputItem(result[key], key);
                        count++;
                    });
                    if( count<10 ){
                        isNext = false;
                    }
                }).fail(function (error) {
                })
            }
        }
    });
    function callRecentlyVideo10(page){
        $.ajax({
            type: "get",
            url: "/api/video/genre/${genere}/" + (page),
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
            "<a href=/front/detailVideo?no="+ key +">" +
            "<div class=\"videoBox\">"+
            callVideoImage(key) +
            "<div class=\"video_name\">제목:" + result.videoName + "</div>"+
            "<div class=\"video_uploder\">가수:" + result.uploadUserId + "</div>"+
            "</div>"+
            "</a>";
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
<link rel="stylesheet" href="../../css/bodycss.css">
<style>
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
<body>
<!-- 네비게이션 -->
<div>
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <%@include file="sideController.jsp"%>
</div>



<recently_video_zone class="recently_video_zone">
    <h2>${genere} 장르 영상</h2><br>
    <script>
        callRecentlyVideo10(1);
    </script>
</recently_video_zone>

</body>
</html>
