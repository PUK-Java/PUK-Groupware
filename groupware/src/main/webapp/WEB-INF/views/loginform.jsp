<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 로그인화면  -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <h2>로그인</h2>
    <form action="/loginRequest" method="post">
        <div class="mb-3">
            <label class="form-label" for="name">아이디</label>
            <input class="form-control" type="text" name="userId" id="name"/>
        </div>
        <div class="mb-3">
            <label class="form-label" for="password">비밀번호</label>
            <input class="form-control" type="password" name="userPw" id="password"/>
        </div>
        <button class="btn btn-outline-primary btn-sm" type="submit">로그인</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<!-- //로그인 실패원인을 알려주는 경고창 -->
<c:if test= "${msg != null}">
    <script>
        alert("${msg}");
    </script>
</c:if>
</body>
</html>