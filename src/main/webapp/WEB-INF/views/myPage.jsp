<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/bodycss.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <link rel="stylesheet" href="../../css/mypage/S.css">
    <link rel="stylesheet" href="../../css/mypage/myInfo.css">
    <link rel="stylesheet" href="../../css/mypage/userInfoEdit.css">
    <link rel="stylesheet" href="../../css/mypage/userExit.css">
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
        <div class="image"><img src="/resources/images/${userProfileImage.serverFilePath}"/><br></div>
        <div class="textbox">
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
            <div>
            <form action="/front/users/edit" method="post" enctype="multipart/form-data">
                <div class="row"><div class="text">아이디: </div><div class="edit_box">${loginUser.id}</div></div>
                <div class="row"><div class="text">비밀번호: </div><div class="edit_box"><input type="password" name="password" maxlength="20"/></div></div>
                <div class="row"><div class="text">비밀번호 확인: </div><div class="edit_box"><input type="password" name="passwordCheck" maxlength="20"/></div></div>
                <div class="row"><div class="text">연락처: </div><div class="edit_box"><input type="text" name="phoneFirst" value="${phone[0]}" maxlength="3"/>-<input type="text" name="phoneSecond" value="${phone[1]}" maxlength="4"/>-<input type="text" name="phoneThird"value="${phone[2]}" maxlength="4"/></div></div>
                <div class="row"><div class="text">이름: </div><div class="edit_box"><input type="text" name="name" value="${loginUser.name}"maxlength="10"/></div></div>
                <div class="row"><div class="text">닉네임: </div><div class="edit_box"><input type="text" name="nickname" value="${loginUser.nickname}" maxlength="10"/></div></div>
                <div class="row"><div class="text">프로필사진: </div><div class="edit_box"><input type="file" name="profileImage" accept="image/*"/></div></div>
                <div class="row"><div class="text"></div><input class="submit" type="submit" value="회원정보 수정"/></div>
                <br>
            </form>
            </div>
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


