<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .centered-form {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .form-container {
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="container centered-form">
        <form class="form-container" action="/userPwCheck" id="pwform" method="post">
            <div class="text-center mb-3"><h4>비밀번호를 입력하세요</h4></div>
            <div class="row justify-content-center">
                <div class="col-8">
                    <input class="form-control mb-2" type="password" name="password" placeholder="비밀번호">
                </div>
                <div class="col-4">
                    <input class="btn btn-secondary w-100" type="submit" value="확인">
                </div>
            </div>
        </form>
    </div>
<!-- 세션 처리후 리다이렉트하면 팝업창은 닫고 원래창 새로고침 -->
<c:if test="${verify != null}">
    <script type="text/javascript">
        window.opener.location.reload();
        window.close();
        </script>
</c:if>
</html>