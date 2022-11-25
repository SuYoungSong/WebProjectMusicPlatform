<%--
  Created by IntelliJ IDEA.
  User: prodr
  Date: 2022-11-25-025
  Time: 오후 4:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 네비게이션 -->
<div>
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <%@include file="sideController.jsp"%>
</div>

영상 제목: ${video.videoName}<br>
영상 장르: ${video.videoGenre}<br>
영상 설명: ${videoDescription}<br>
업로더: ${video.uploadUserId}<br>

</body>
</html>
