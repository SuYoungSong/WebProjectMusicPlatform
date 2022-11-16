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
${successMessage}<br>

<c:if test="${not empty requestScope.findId}">
    아이디 : ${findId}
</c:if>

</body>
</html>
