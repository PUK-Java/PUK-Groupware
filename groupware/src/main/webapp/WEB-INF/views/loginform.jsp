<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 로그인화면  -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
        min-height: 100vh;

        background: -webkit-gradient(linear, left bottom, right top, from(#92b5db), to(#1d466c));
        background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
        background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
        background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
        background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
        }

        .input-form {
            max-width: 680px;

            margin-top: 150px;
            padding: 32px;

            background: #fff;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            border-radius: 10px;
            -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
            -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
            box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
        }
    </style>
</head>
<body>
<div class="container">
    <div class="input-form-backgroud row">
        <div class="input-form col-md-12 mx-auto">
            <h2>로그인</h2>
            <!-- 메인페이지 -->
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <button class="btn btn-primary btn-lg btn-block " type="button" onclick="location.href='/'">메인으로</button>
            </div>
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
                <button class="btn btn-outline-primary btn-sm" type="button" onclick="location.href='/signupform'">회원가입</button>
            </form>
        </div>
    </div>
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