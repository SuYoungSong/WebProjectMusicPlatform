<%--
  Created by IntelliJ IDEA.
  User: Samsung
  Date: 2022-12-07
  Time: 오후 2:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="/css/search_form.css">
  <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

</head>
<body>
<%--검색 폼 미리 만듦--%>

<section id="search">
  <form action="/front/search" method="get">
    <div class="selectBox">
      <select class="selectList" name="searchType">
        <option>제목</option>
        <option>가수</option>
        <option>업로더</option>
      </select>
      <i class="fas fa-caret-down"></i>
    </div>
    <input type="text" name="inputText" placeholder="키워드를 입력하세요."/>
    <button type="submit">
      <i class="fas fa-search"></i>
    </button>
  </form>
</section>

<%--검색 폼 미리 만듦--%>

</body>
</html>
