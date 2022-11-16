<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            margin-left:210px;
        }
    </style>
</head>
<body>
<!-- 네비게이션 -->
<div id="nav">
    <%@include file="sideNavigation.jsp"%>
</div>
<c:if test="${not empty requestScope.uploadFileInfo}">
    [ ${uploadFileInfo.userUploadFileName} ] 파일 등록을 성공하였습니다.
</c:if>
<c:if test="${not empty requestScope.uploadError}">
    ${uploadError}
</c:if>



</body>
</html>
