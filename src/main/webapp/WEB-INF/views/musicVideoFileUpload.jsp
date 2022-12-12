<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dream</title>
    <link rel="stylesheet" href="/css/bodycss.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <style>
        #music, #video{
            display:none;
        }
        #musictab:checked ~ #music,
        #videotab:checked ~ #video{
            display: flex;
        }
    </style>
    <link rel="stylesheet" href="/css/musicvideofileupload/vfuBack.css">
    <link rel="stylesheet" href="/css/musicvideofileupload/vfuRadio.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>
<!-- 네비게이션 -->
<div id="nav">
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
</div>


<input type="radio" name="tabs" id="musictab" class="none" checked/>
<label for="musictab">음악</label>
<input type="radio" name="tabs" id="videotab" class="none"/>
<label for="videotab">영상</label>

<div id="music" class="vfu">
    <form action="/front/fileUploadProcess" method="POST" enctype="multipart/form-data">
        <input type="text" name="fileType" value="musics" style="display:none" readonly/>
        <div class="vfu_box"><div class="text">음악 제목: </div><div class="qv"><input type="text" name="musicName"/></div></div>
        <div class="vfu_box"><div class="text">음악 설명: </div><div class="qv"><textarea name="musicDescription"></textarea></div></div>
        <div class="vfu_box"><div class="text">음악 장르: </div><div class="qv"><select name="genre">
            <option value="발라드">발라드</option>
            <option value="댄스">댄스</option>
            <option value="힙합">힙합</option>
            <option value="발라드">트로트</option>
            <option value="클래식">클래식</option>
            <option value="팝">팝</option>
            <option value="재즈">재즈</option>
            <option value="블루스">블루스</option>
            <option value="EDM">EDM</option>
            <option value="OST">OST</option>
            <option value="인디">인디</option>
        </select></div></div>
        <div class="vfu_box"><div class="text">음악 가사: </div><div class="qv"><textarea name="lyrics"></textarea></div></div>
        <div class="vfu_box"><div class="text">음악 가수: </div><div class="qv"><input type="text" name="singer"/></div></div>
        <div class="vfu_box"><div class="text">음악 작곡가: </div><div class="qv"><input type="text" name="songwriter"/></div></div>
        <div class="vfu_box"><div class="text">음악 작사가: </div><div class="qv"><input type="text" name="lyricwriter"/></div></div>
        <div class="vfu_box"><div class="text">음악 편곡가: </div><div class="qv"><input type="text" name="musicArranger"/></div></div>
        <div class="vfu_box"><div class="text">음악 발매일: </div><div class="qv"><input type="date" name="releaseDate"/></div></div>
        <div class="vfu_box"><div class="text">음악 파일: </div><div class="qv"><input type="file" name="file" accept="audio/*"/></div></div>
        <div class="vfu_box"><div class="text">음악 사진: </div><div class="qv"><input type="file" name="imageFile" accept="image/*"/></div></div>
        <input class="vfu_sub" type="submit" value="업로드"/>
    </form>
</div>

<div id="video" class="vfu">
    <form action="/front/fileUploadProcess" method="POST" enctype="multipart/form-data">
        <input type="text" name="fileType" value="videos" style="display:none" readonly/>
        <div class="vfu_box"><div class="text">영상 제목: </div><div class="qv"><input type="text" name="videoName"/></div></div>
        <div class="vfu_box"><div class="text">영상 설명: </div><div class="qv"><textarea name="videoDescription"></textarea></div></div>
        <div class="vfu_box"><div class="text">영상 장르: </div><div class="qv"><select name="genere">
            <option value="발라드">발라드</option>
            <option value="댄스">댄스</option>
            <option value="힙합">힙합</option>
            <option value="발라드">트로트</option>
            <option value="클래식">클래식</option>
            <option value="팝">팝</option>
            <option value="재즈">재즈</option>
            <option value="블루스">블루스</option>
            <option value="EDM">EDM</option>
            <option value="OST">OST</option>
            <option value="인디">인디</option>
        </select></div></div>
        <div class="vfu_box"><div class="text">영상 파일:</div><div class="qv"><input type="file" name="file" accept="video/*"/></div></div>
        <div class="vfu_box"><div class="text">영상 사진:</div><div class="qv"><input type="file" name="imageFile" accept="image/*"/></div></div>
        <input class="vfu_sub" type="submit" value="업로드"/>
    </form>
</div>

</div>
</body>
</html>