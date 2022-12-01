<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/bodycss.css">
     <style>
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
        /*input:checked + label {*/
        /*    color: #555;*/
        /*    border: 1px solid #ddd;*/
        /*    border-top: 2px solid #2e9cdf;*/
        /*    border-bottom: 1px solid #ffffff;*/
        /*}*/

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
            box-shadow: 1px 1px 5px rgba(220, 175, 255, 0.6);
        }
        .main_item_image img{
            width: 160px;
            height: 160px;
            padding-left: 10%;
        }
        .sub_item_image{
            display:flex;
        }
        .sub_item_image button{
            cursor: pointer;
            background: transparent;
            width: 30px;
            height: 30px;
            border: none;
            margin-top: 15px;
            margin-left: 45px;
        }
        .sub_item_image img{
            width: 30px;
            height: 30px;

        }
        .music_name{
            margin-top: 10px;
            text-align: center;
        }
        .video_name{
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
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
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
        <input class="radio_none" id="myMusic" type="radio" name="tabs" checked ${myMusicChecked}>
        <label for="myMusic">내 음악</label>
        <input class="radio_none" id="myVideo" type="radio" name="tabs" ${myVideoChecked}>
        <label for="myVideo">내 비디오</label>
        <input class="radio_none" id="userInfoEdit" type="radio" name="tabs" ${userInfoChecked}>
        <label for="userInfoEdit">회원 정보 수정</label>
        <input class="radio_none" id="userExit" type="radio" name="tabs">
        <label for="userExit">회원 탈퇴</label>


        <section id="myMusic-content">
            <c:if test="${not empty requestScope.musicMap}">
                <c:forEach var="music" items="${musicMap}" varStatus="status">
                    <section class="box">
                        <div class="main_item_image"><img src="/resources/images/${musicImageMap[music.key].serverFilePath}"/></div>
                        <div class="music_name">${music.value.musicName}</div>
                        <div class="sub_item_image">
                            <form action="/front/music/edit" method="post">
                                <input type="hidden" name="musicNumber" value="${music.key}"/>
                                <button type="submit"><img src="/resources/images/defaultEditImage.png"/></button>
                            </form>
                            <form action="/front/music/delete" method="post">
                                <input type="hidden" name="musicNumber" value="${music.key}"/>
                                <button type="submit"><img src="/resources/images/defaultDeleteImage.png"/></button>
                            </form>
                        </div>
                    </section>
                </c:forEach>
            </c:if>
            <c:if test="${empty requestScope.musicMap}">
                <h2>업로드 한 노래가 없습니다.</h2>
            </c:if>
        </section>



        <section id="myVideo-content">
            <c:if test="${not empty requestScope.videoMap}">
                <c:forEach var="video" items="${videoMap}" varStatus="status">
                <section class="box">
                    <div class="main_item_image"><img src="/resources/images/${videoImageMap[video.key].serverFilePath}"/></div>
                    <div class="video_name">${video.value.videoName}</div>
                    <div class="sub_item_image">
                        <form action="/front/video/edit" method="post">
                            <input type="hidden" name="videoNumber" value="${video.key}"/>
                            <button type="submit"><img src="/resources/images/defaultEditImage.png"/></button>
                        </form>
                        <form action="/front/video/delete" method="post">
                            <input type="hidden" name="videoNumber" value="${video.key}"/>
                            <button type="submit"><img src="/resources/images/defaultDeleteImage.png"/></button>
                        </form>
                    </div>
                </section>
                </c:forEach>
            </c:if>
            <c:if test="${empty requestScope.videoMap}">
                <h2>업로드 한 영상이 없습니다.</h2>
            </c:if>
        </section>



        <section id="userInfoEdit-content">
            <c:set var="phone" value="${fn:split(loginUser.phone, '-')}" />
            <form action="/front/users/edit" method="post" enctype="multipart/form-data">
                아이디: ${loginUser.id}<br>
                비밀번호: <input type="password" name="password" maxlength="20"/><br>
                비밀번호 확인: <input type="password" name="passwordCheck" maxlength="20"/><br>
                연락처: <input type="text" name="phoneFirst" value="${phone[0]}" maxlength="3"/>-<input type="text" name="phoneSecond" value="${phone[1]}" maxlength="4"/>-<input type="text" name="phoneThird"value="${phone[2]}" maxlength="4"/><br>
                이름: <input type="text" name="name" value="${loginUser.name}"maxlength="10"/><br>
                닉네임: <input type="text" name="nickname" value="${loginUser.nickname}" maxlength="10"/><br>
                프로필사진: <input type="file" name="profileImage" accept="image/*"/><br>
                <input type="submit" value="회원정보 수정"/><br>
                <br>
            </form>
            ${failUserEditMessage}
        </section>

        <section id="userExit-content">
            <p>회원 탈퇴시 모든 기록이 사라지며 복구가 불가능 합니다.</p>
            <p>신중하게 선택해주세요.</p>
            <form action="/front/users/exit" method="post">
                <input type="submit" value="회원탈퇴">
            </form>
        </section>

    </div>

</c:if>

<!-- 비로그인 상태일때 보여줄 메뉴 -->
<c:if test="${empty sessionScope.loginUser}">
로그인 후 이용 가능한 기능 입니다.
</c:if>

</body>
</html>


