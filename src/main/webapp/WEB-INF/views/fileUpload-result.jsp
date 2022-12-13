<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Dream</title>
    <link rel="stylesheet" href="/css/bodycss.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <link rel="stylesheet" href="/css/fileupload/upload.css">
</head>
<body>
<!-- 네비게이션 -->
<div id="nav">
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
</div>
<div class="box">
<div class="result">
<c:if test="${not empty requestScope.uploadFileInfo}">
    [ ${uploadFileInfo.userUploadFileName} ] 파일 등록을 성공하였습니다.
</c:if>
<c:if test="${not empty requestScope.uploadError}">
    ${uploadError}
</c:if>
</div>
</div>

</body>
</html>
