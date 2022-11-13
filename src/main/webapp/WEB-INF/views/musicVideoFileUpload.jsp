<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #music, #video{
            display:none;
        }
        #musictab:checked ~ #music,
        #videotab:checked ~ #video{
            display: flex;
        }
    </style>
</head>
<body>


<input type="radio" name="tabs" id="musictab" checked>음악</input>
<input type="radio" name="tabs" id="videotab" checked>영상</input>

    <div id="music">
        <form action="/front/fileUploadProcess" method="POST" enctype="multipart/form-data">
            <input type="text" name="fileType" value="musics" style="display:none" readonly/>
            음악 제목: <input type="text" name="musicName"/><br>
            음악 설명: <textarea name="musicDescription"></textarea><br>
            음악 장르: <select name="genere">
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
                    </select><br>
            음악 가사: <textarea name="lyrics"></textarea><br>
            음악 가수: <input type="text" name="singer"/><br>
            음악 작곡가: <input type="text" name="songwriter"/><br>
            음악 작사가: <input type="text" name="lyricwriter"/><br>
            음악 편곡가: <input type="text" name="musicArranger"/><br>
            음악 발매일: <input type="date" name="releaseDate"/><br>
            파일:<input type="file" name="file" accept="audio/*"/>
            <input type="submit" value="업로드"/>
        </form>
    </div>


    <div id="video">
        <form action="/front/fileUploadProcess" method="POST" enctype="multipart/form-data">
            <input type="text" name="fileType" value="videos" style="display:none" readonly/>
            영상 제목: <input type="text" name="videoName"/><br>
            영상 설명: <textarea name="videoDescription"></textarea><br>
            영상 장르: <select name="genere">
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
                    </select><br>
            파일:<input type="file" name="file" accept="video/*"/>
            <input type="submit" value="업로드"/>
        </form>
    </div>


</body>
</html>
