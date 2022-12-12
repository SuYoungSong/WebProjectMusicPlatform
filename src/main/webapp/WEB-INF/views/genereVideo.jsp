<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dream</title>
</head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
    var page = 1;
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
                    Object.keys(result).sort().reverse().map(function (key) {
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
            Object.keys(result).sort().reverse().map(function (key) {
                inputItem(result[key], key);
                count++;
            });
            for ( let i = 0 ; i < 10-count ; i++ ){
                inputNoneItem()
            }
        }).fail(function (error) {
            for ( let i = 0 ; i < 10 ; i++ ){
                inputNoneItem()
            }
            console.log("영상 로딩 실패")
            $("recently_music_zone").append("영상을 불러오는데 실패했습니다.");
        })
    }
    function inputItem(result, key) {
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
        $("recently_video_zone").append(string);
    }
    function inputNoneItem() {
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
<link rel="stylesheet" href="/css/bodycss.css">
<style>
    .video_text{
        padding-top:15px;
        display: flex;
        flex-direction: column;
    }
    .video_a_tag{
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
    .videoBox{
        display: flex;
        flex-direction: row;
        position: static;
        margin-bottom: 10px;
    }
    .videoBox img {
        width: 75px;
        height: 75px;
        padding-right: 15px;
    }
    .videoBox img:hover {
        opacity: 0.5;
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
        width: 100%;
        display: flex;
        flex-direction: column;
        margin-top: 50px;
    }
</style>
<body>
<!-- 네비게이션 -->
<div>
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
</div>



<recently_video_zone class="recently_video_zone">
    <h2>${genere} 장르 영상</h2><br>
    <script>
        callRecentlyVideo10(1);
    </script>
</recently_video_zone>

</body>
</html>
