<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>userFind</title>
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
  <link rel="stylesheet" href="/css/bodycss.css">
  <link rel="stylesheet" href="/css/userFind/radio.css">
  <link rel="stylesheet" href="/css/userFind/find.css">
</head>
<body>
<!-- 네비게이션 -->
<div id="nav">
  <%@include file="sideNavigation.jsp"%>
</div>
<div>
  <jsp:include page = "sideController.jsp"></jsp:include>
</div>

<input type="radio" name="tabs" id="idTab" class="radio" checked/>
<label for="idTab">아이디 찾기</label>
<input type="radio" name="tabs" id="pwTab" class="radio"${checkedReturn}/>
<label for="pwTab">비밀번호 찾기</label>


<div id="userId" class="user">
  <form action="/front/users/userFindProcess" method="POST">
    <input type="hidden"  name="type" value="findId"/>
    <div class="box"><div class="text">이름: </div><div class="qv"><input type="text" name="name" value="${returnName}"/></div></div>
    <div class="box"><div class="text">연락처: </div><div class="qv"><input type="text" name="phoneFirst" value="${returnPhoneFirst}" maxlength="3"/>-<input type="text" name="phoneSecond" value="${returnPhoneSecond}" maxlength="4"/>-<input type="text" name="phoneThird" value="${returnPhoneThird}" maxlength="4"/></div></div>
    <div class="box"><div class="text"></div><div class="qvv"><input type="submit" value="찾기"/></div></div>
  </form>
</div>

<div id="userPw" class="user">
  <form action="/front/users/userFindProcess" method="POST">
    <input type="hidden"  name="type" value="findPassword"/>
    <div class="box"><div class="text">아이디: </div><div class="qv"><input type="text" name="id" value="${returnId}"/></div></div>
    <div class="box"><div class="text">이름: </div><div class="qv"><input type="text" name="name" value="${returnName}"/></div></div>
    <div class="box"><div class="text">연락처: </div><div class="qv"><input type="text" name="phoneFirst" value="${returnPhoneFirst}" maxlength="3"/>-<input type="text" name="phoneSecond" value="${returnPhoneSecond}" maxlength="4"/>-<input type="text" name="phoneThird"value="${returnPhoneThird}" maxlength="4"/></div></div>
    <div class="box"><div class="text"></div><div class="qvv"><input type="submit" value="찾기"/></div></div>
  </form>
  ${FailUserFindMessage}
</div>
</body>
</html>
