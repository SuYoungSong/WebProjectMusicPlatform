<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Dream</title>
    <link rel="stylesheet" href="/css/bodycss.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
<!-- 네비게이션 -->
<div id="nav">
    <%@include file="sideNavigation.jsp"%>
</div>
<div>
    <jsp:include page = "sideController.jsp"></jsp:include>
</div>

${successMessage}<br>

<c:if test="${not empty requestScope.findId}">
    아이디 : ${findId}
</c:if>

</body>
</html>
