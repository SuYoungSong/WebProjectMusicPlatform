<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <style>
      .writeBox{
        width:600px;
        height:300px;
        margin:20px;
        padding: 10px;
        border: 1px solid #ccc;
      }
      .writeBox textarea{
        width: 100%;
        height: 70%;
        resize: none;
      }
      .writeZone{
          height: 40px;
          width: 100%;
          margin-bottom: 5px;
      }
      .writeButton{
          width: 100%;
          margin-top: 15px;
          height: 30px;
      }
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
<c:if test="${not empty sessionScope.loginUser}">
<legend>
  <form action="/front/" method="Post">
    <div class="writeBox">
      <input type="text" class="writeZone" name="title" placeholder="제목을 입력하세요."/>
      <textarea id="content" placeholder="내용을 입력하세요."></textarea>
      <input type="submit" class="writeButton" value="작성하기"/>
    </div>
  </form>
</legend>
</c:if>

게시판 글 내용들 보여 줄 예정

</body>
</html>
