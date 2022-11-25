<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
                        url: "/api/detailVideo/callComment/${detailVideoNumber}/" + (++page),
                        dataType: "json"
                    }).done(function (result) {
                        var count = 0   // 더이상 음악 없으면 DB호출 막는 용도
                        for (var cmt of result) {
                            inputItem(cmt);
                            count++;
                        }
                        if (count < 1) {
                            $("comment_zone").append("등록된 댓글이 없습니다.");
                        }
                        if( count<5 ){
                            isNext = false;
                        }
                    }).fail(function (error) {
                    })
                }
            }
        });
        function callVideoComment(page){
            $.ajax({
                type: "get",
                url: "/api/detailVideo/callComment/${detailVideoNumber}/" + page,
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
    <style>
        body{
            margin-left:210px;
        }
        .writeBox{
            width:600px;
            height:150px;
            margin:20px;
            padding: 10px;
            border: 1px solid #ccc;
            display: flex;
        }
        .writeBox textarea{
            width: 85%;
            height: 100%;
            resize: none;
            margin-right: 5px;
        }
        .writeButton{
            width: 15%;
            height: 100%;
        }
        .comment_write textarea{
            width:520px;
            height: 65px;
            resize: none;
        }
        .comment_write input{
            height: 65px;
            vertical-align: top;

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

<video controls="controls" poster="/resources/images/${videoImage.serverFilePath}">
    <source src="/resources/videos/${videoFile.serverFileName}" type="video/mp4" />
</video><br>

영상 제목: ${videoInfo.videoName}<br>
영상 장르: ${videoInfo.videoGenre}<br>
영상 설명: ${videoInfo.videoDescription}<br>
업로더: ${videoInfo.uploadUserId}<br>


<%--로그인 한 경우 댓글 작성 가능하게--%>
<c:if test="${not empty sessionScope.loginUser}">
    <legend>
        <form action="/front/detailVideo/writeComment?videoNumber=${detailVideoNumber}" method="Post">
            <div class="writeBox">
                <textarea name="text" placeholder="내용을 입력하세요."></textarea><br>
                <input type="submit" class="writeButton" value="작성하기"/>
            </div>
        </form>
    </legend>
</c:if>

<%-- 작성된 댓글 공간 --%>
<comment_zone>
    <script>callVideoComment(0)</script>
</comment_zone>

</body>
</html>
