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
      .postBox{
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
  <form action="/front/board/write" method="Post">
    <div class="writeBox">
      <input type="text" class="writeZone" name="title" placeholder="제목을 입력하세요."/>
      <textarea name="content" placeholder="내용을 입력하세요."></textarea>
      <input type="submit" class="writeButton" value="작성하기"/>
    </div>
  </form>
</legend>
</c:if>

<!-- 등록된 게시물이 없거나 에러로 못불러온 경우 -->
<c:if test="${posts.size() == 0}">
    <h1> 등록된 게시글이 없거나 게시글을 불러오는데 실패했습니다.</h1>
</c:if>

<!-- 게시글 호출 추후 자바스크립트를 통해서 스크롤 내릴때마다 일정 게시글 불러오게 변경할 예정(시간남으면) -->
<c:if test="${posts.size() > 0}">
    <c:forEach var="post" items="${posts}">
        <div class="postBox">
            <div class="post_write">글쓴이: ${post.value.writer}</div><br>
            <div class="post_title">제목: ${post.value.title}</div><br><br>
            <div class="post_content">내용: ${post.value.content}</div><br>
        </div>
    </c:forEach>
</c:if>

</body>
</html>
