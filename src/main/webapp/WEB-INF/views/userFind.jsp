<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="../../css/bodycss.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <style>
    #userId, #userPw{
      display:none;
    }
    #idTab:checked ~ #userId,
    #pwTab:checked ~ #userPw{
      display: flex;
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

<input type="radio" name="tabs" id="idTab" checked>아이디 찾기</input>
<input type="radio" name="tabs" id="pwTab" ${checkedReturn}>비밀번호 찾기</input>

<div id="userId">
  <form action="/front/users/userFindProcess" method="POST">
    <input type="hidden"  name="type" value="findId"/>
    이름: <input type="text" name="name" value="${returnName}"/><br>
    연락처: <input type="text" name="phoneFirst" value="${returnPhoneFirst}" maxlength="3"/>-<input type="text" name="phoneSecond" value="${returnPhoneSecond}" maxlength="4"/>-<input type="text" name="phoneThird" value="${returnPhoneThird}" maxlength="4"/><br>
    <input type="submit" value="찾기"/>
  </form>
</div>

<div id="userPw">
  <form action="/front/users/userFindProcess" method="POST">
    <input type="hidden"  name="type" value="findPassword"/>
    아이디: <input type="text" name="id" value="${returnId}"/><br>
    이름: <input type="text" name="name" value="${returnName}"/><br>
    연락처: <input type="text" name="phoneFirst" value="${returnPhoneFirst}" maxlength="3"/>-<input type="text" name="phoneSecond" value="${returnPhoneSecond}" maxlength="4"/>-<input type="text" name="phoneThird"value="${returnPhoneThird}" maxlength="4"/><br>

    <input type="submit" value="찾기"/>
  </form>

  <br>
  ${FailUserFindMessage}
</div>
</body>
</html>
