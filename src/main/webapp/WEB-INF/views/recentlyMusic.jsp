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
                    url: "/api/music/callMusic10/" + (++page),
                    dataType: "json"
                }).done(function (result) {
                    var count = 0   // 더이상 음악 없으면 DB호출 막는 용도
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
            "<a href=/front/detailMusic?no="+ key +">" +
            "<div class=\"musicBox\">"+
            callMusicImage(key) +
            "<div class=\"music_name\">제목:" + result.musicName + "</div>"+
            "<div class=\"music_singer\">가수:" + result.singer + "</div>"+
            "<div class=\"music_genere\">장르:" + result.genre + "</div>"+
            "</div>"+
            "</a>" ;
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
    body{
        margin-left:210px;
        margin-bottom: 120px;
    }
    .musicBox{
        display: flex;
        position: static;
    }
    .music_image img{
        width:50px;
        height:50px;
        object-fit:cover;
    }

    recently_music_zone{
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
    <%@include file="sideController.jsp"%>
</div>



<recently_music_zone class="recently_music_zone">
    <h2>최신 음악</h2><br>
    <script>
        callRecentlyMusic10(1);
    </script>
</recently_music_zone>

</body>
</html>
