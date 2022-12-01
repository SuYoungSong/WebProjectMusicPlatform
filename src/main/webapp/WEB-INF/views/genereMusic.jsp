<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>2
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
                    url: "/api/music/genre/${genere}/" + (++page),
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
            url: "/api/music/genre/${genere}/" + (page),
            dataType: "json"
        }).done(function (result) {
            var count = 0   // 음악이 하나도 없는경우 문구 출력용
            Object.keys(result).map(function (key) {
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
            console.log("음악 로딩 실패")
            $("recently_music_zone").append("음악을 불러오는데 실패했습니다.");
        })
    }
    function inputItem(result, key) {
        // 게시글(이미지 제외) 뜨는 html 수정하려면 여기 수정하면 됌
        var string =
            "<div class=\"musicBox\">"+
            callMusicImage(key) +
            "<a class=\"music_a_tag\" href=/front/detailMusic?no="+ key +">" +
            "<div class=\"music_name\">" + result.musicName + "</div>"+
            "<div class=\"music_singer\">" + result.singer + "</div>"+
            "</div>"+
            "</a>" ;
        $("recently_music_zone").append(string);
    }
    function inputNoneItem() {
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
        $('recently_music_zone').append(string);
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
                "<img  class=\"musicImagIcon\" src=\"/resources/images/"+result.serverFilePath + "\"/>"+
                "<button class=\"music_play_button\" onclick='musicPlayButton("+key+")'><img src=\"/resources/images/defaultPlayImage.png\"/></button>" +
                "</div>";
        }).fail(function (error) {
        })
        return string;
    }
    function musicPlayButton(key){
        localStorage.setItem("playMusicNumber", key)
        setMusic()
        wavesurfer.setCurrentTime(0)
        setMusicPlayTime(0)
        setMusicPlayState(1)
        wavesurfer.play()
        playButtonIcon.src = "/resources/images/pause.png"

    }
</script>
<link rel="stylesheet" href="../../css/bodycss.css">
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
    .musicBox{
        display: flex;
        flex-direction: row;
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


<recently_music_zone class="recently_music_zone">
    <h2>${genere}장르 음악</h2><br>
    <script>
        callRecentlyMusic10(1);
    </script>
</recently_music_zone>

</body>
</html>
