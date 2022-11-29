<%@ page import="webApplication.musicPlatform.web.Repository.board.BoardImageRepository" %>
<jsp:useBean id="paging" class="webApplication.musicPlatform.web.api.BoardPaging" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <style>
        body {
            margin-left: 210px;
            margin-bottom: 120px;
            background-color: #0a0a0a;
        }
    </style>
    <link rel="stylesheet" href="../../css/board/boaBox.css">
    <!-- <link rel="stylesheet" href="../../css/board/boa_SSY.css"> -->
    <link rel="stylesheet" href="../../css/board/boa_Box.css">
</head>
<body>
<!-- 네비게이션 -->
<div>
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <%@include file="sideController.jsp"%>
</div>
<!-- test용 실행 -->
<div class="boaBox">
    <h2> 제목:123 </h2>
    <img src="/resources/images/defaultMusicImage.png">
    <p>1231941927349871289305798 27349613295798123749061293587912347961532</p>
</div>

<%--로그인시 게시글 작성칸 보이기 --%>
<c:if test="${not empty sessionScope.loginUser}">
    <div class="postBox">
        <form action="/front/board/write" method="Post" enctype="multipart/form-data">
            <div class="writeBox">
                <input type="text" class="writeZone" name="title" placeholder="제목을 입력하세요."/>
                <textarea name="content" placeholder="내용을 입력하세요."></textarea><br>
                이미지1:<input type="file" name="file" accept="image/*"/><br>
                이미지2:<input type="file" name="file" accept="image/*"/><br>
                이미지3:<input type="file" name="file" accept="image/*"/><br>
                <input type="submit" class="writeButton" value="작성하기"/>

            </div>
        </form>
    </div>
</c:if>



<%--    처음 게시물 불러오기 --%>
<script>
    $.ajax({
        type: "get",
        url: "/api/board/callPost5/1",
        dataType: "json"
    }).done(function (result) {
        var count = 0   // 게시글이 하나도 없는경우 문구 출력용
        Object.keys(result).map(function (key) {
            inputItem(result[key], key);
            count++;
        });
        if(count<1){
            $("body").append("등록된 게시글이 없습니다.");
        }
    }).fail(function (error) {
        $("body").append("게시글을 불러오는데 실패했습니다.");
    })
</script>

<%-- 무한스크롤시 게시글 불러오기--%>
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
                    url: "/api/board/callPost5/" + (++page),
                    dataType: "json"
                }).done(function (result) {
                    var count = 0   // 더이상 게시글이 없으면 DB호출 막는 용도
                    Object.keys(result).map(function (key) {
                        inputItem(result[key], key);
                        count++;
                    });
                    if( count<5 ){
                        isNext = false;
                    }
                }).fail(function (error) {
                })
            }
        }
    });
    function inputItem(result, key) {
        // 게시글(이미지 제외) 뜨는 html 수정하려면 여기 수정하면 됌
        var string =
            "<div class=\"postBox\">"+
            "<div class=\"post_write\">글쓴이:" + result.writer + "</div>"+
            "<div class=\"post_title\">제목:" + result.title + "</div>"+
            "<div class=\"post_img\">" + callPostImage(key) + "</div>"+
            "<div class=\"post_content\">내용:" + result.content + "</div>"+
            "<c:if test="${not empty sessionScope.loginUser}">"+
            "<form action=\"/front/board/comment/save\" method=\"post\" >" +
            "<input type=\"hidden\" name=\"writer\" value=\"${loginUser.id}\"/>" +
            "<input type=\"hidden\" name=\"boardNumber\" value=\""+ key+"\"/>" +
            "<div class=\"comment_write\">" +
            "<textarea name=\"text\"></textarea>"+
            "<input type=\"submit\" value=\"댓글작성\"/>" +
            "</div>"+
            "</form>" +
            "</c:if>"+
            callComment(key) +
            "</div>";
        $("body").append(string);
    }
    function callComment(key){
        var comment = "";
        // 댓글 불러오기
        $.ajax({
            type: "get",
            url: "/api/board/comment/" + key,
            async: false,
            dataType: "json"
        }).done(function (result) {
            if(result.length == 0){
            }else {
                comment += "댓글<br>"
                for (var cmt of result) {
                    comment +=
                        "<hr><div class=\"post_comment\">" +
                        "<div>댓글 작성자:" + cmt.writer + "</div><br>" +
                        "<div>댓글 내용:" + cmt.commentText + "</div> " +
                        "</div>";
                }
            }
        }).fail(function (error) {
            comment +="댓글을 불러오는데 실패했습니다.";
        })
        return comment;
    }
    function callPostImage(key){
        // 이미지 뜨는 html 수정하려면 여기 수정하면 됌
        var string = ""
        $.ajax({
            type: "get",
            url: "/api/board/callPostImage/" + (key),
            async: false,
            dataType: "json"
        }).done(function (result) {
            if(result.length == 0){
            }else{
                for(var images of result) {
                    string +=
                        "<div class=\"post_image\">"+
                        "<image height=\"300\" width=\"300\" src=\"/resources/images/"+images.serverFilePath + "\"/><br>"+
                        "</div><br><br>";
                }
            }
        }).fail(function (error) {
        })
        return string;
    }
</script>
</body>
</html>