<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
     <style>
        body{
            margin-left:210px;
            margin-bottom: 120px;
        }

        .myInfo {
            display: flex;
            flex-direction: row;
            alignment: center;
        }
        .myInfo_image img{
            width: 300px;
            height: 300px;
        }
        .myInfo_text{
           margin-top: 10%;
            font-size: 25px;
        }
        section {
            display: none;
            padding: 20px 0 0;
            border-top: 1px solid #ddd;}

          .radio_none{
              display: none;
          }

        label {
            display: inline-block;
            margin: 0 0 -1px;
            padding: 15px 25px;
            font-weight: 600;
            text-align: center;

            border: 1px solid transparent;}

        label:hover {
            color: #2e9cdf;
            cursor: pointer;}

        input:checked + label {
              color: #555;
              border: 1px solid #ddd;
              border-top: 2px solid #2e9cdf;
              border-bottom: 1px solid #ffffff;
          }
        input:checked + label {
            color: #555;
            border: 1px solid #ddd;
            border-top: 2px solid #2e9cdf;
            border-bottom: 1px solid #ffffff;
        }

        #myMusic:checked ~ #myMusic-content,
        #myVideo:checked ~ #myVideo-content,
        #userInfoEdit:checked ~ #userInfoEdit-content,
        #userExit:checked ~ #userExit-content
        {
            display: block;
        }
        .tab-value {
            padding-top: 30px;
        }
        p {
            margin: 0 0 20px;
            line-height: 1.5;
        }

        .box {
            width: 200px;
            height: 280px;
            display: inline-block;
            background: cornsilk;
            border-radius: 15px 50px;
            margin-left: 30px;
            margin-top: 30px;
        }
        .main_item_image img{
            width: 180px;
            height: 180px;
            padding-left: 5%;
        }
        .sub_item_image img{
            width: 30px;
            height: 30px;
            padding-top: 15px;
            padding-left: 45px;
        }
        .music_name{
            margin-top: 10px;
            text-align: center;
        }


     </style>
</head>
<body>
<!-- 네비게이션 -->
<div id="nav">
    <%@include file="sideNavigation.jsp"%>
</div>

<c:if test="${not empty sessionScope.loginUser}">
    <fieldset>
    <legend>내정보</legend>
    <div class="myInfo">
            <div class="myInfo_image"><img src="/resources/images/${userProfileImage.serverFilePath}"/><br></div>
            <div class="myInfo_text">
                아이디:${loginUser.id}<br>
                이름:${loginUser.name}<br>
                닉네임:${loginUser.nickname}<br>
                전화번호:${loginUser.phone}<br>
            </div>
    </div>
</fieldset>

    <div class="tab-value">
        <input class="radio_none" id="myMusic" type="radio" name="tabs" checked>
        <label for="myMusic">내 음악</label>
        <input class="radio_none" id="myVideo" type="radio" name="tabs">
        <label for="myVideo">내 비디오</label>
        <input class="radio_none" id="userInfoEdit" type="radio" name="tabs">
        <label for="userInfoEdit">회원 정보 수정</label>
        <input class="radio_none" id="userExit" type="radio" name="tabs">
        <label for="userExit">회원 탈퇴</label>


        <section id="myMusic-content">
            <c:if test="${not empty requestScope.MusicMap}">
                <c:forEach var="video" items="${MusicMap}" varStatus="status">
                    <section class="box">
                        <div class="main_item_image"><img src="/resources/images/${musicImageMap[music.key].value.serverFilePath}"/></div>
                        <div class="music_name">${music.videoName}</div>
                        <div class="sub_item_image">
                            <img src="/resources/images/defaultEditImage.png"/>
                            <img src="/resources/images/defaultDeleteImage.png"/>
                        </div>
                    </section>
                </c:forEach>
            </c:if>
            <c:if test="${empty requestScope.MusicMap}">
                <h2>업로드 한 노래가 없습니다.</h2>
            </c:if>
        </section>



        <section id="myVideo-content">
            <c:if test="${not empty requestScope.VideoMap}">
                <c:forEach var="video" items="${VideoMap}" varStatus="status">
                <section class="box">
                    <div class="main_item_image"><img src="/resources/images/${videoImageMap[video.key].value.serverFilePath}"/></div>
                    <div class="music_name">${video.videoName}</div>
                    <div class="sub_item_image">
                        <img src="/resources/images/defaultEditImage.png"/>
                        <img src="/resources/images/defaultDeleteImage.png"/>
                    </div>
                </section>
                </c:forEach>
            </c:if>
            <c:if test="${empty requestScope.VideoMap}">
                <h2>업로드 한 영상이 없습니다.</h2>
            </c:if>
        </section>



        <section id="userInfoEdit-content">
            <p>tab menu3의 내용</p>
        </section>

        <section id="userExit-content">
            <p>회원 탈퇴시 모든 기록이 사라지며 복구가 불가능 합니다.</p>
            <p>신중하게 선택해주세요.</p>

            <input type="submit" value="회원탈퇴">
        </section>

    </div>

</c:if>

<!-- 비로그인 상태일때 보여줄 메뉴 -->
<c:if test="${empty sessionScope.loginUser}">
로그인 후 이용 가능한 기능 입니다.
</c:if>

</body>
</html>


