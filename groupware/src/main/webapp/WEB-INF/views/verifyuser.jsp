<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="/userPwCheck" id="pwform" method="post">
        <h4>비밀번호를 입력하세요</h4>
        <input type="password" name="password">
        <input type="submit">
    </form>
</body>
<!-- 세션 처리후 리다이렉트하면 팝업창은 닫고 원래창 새로고침 -->
<c:if test="${verify != null}">
    <script type="text/javascript">
        window.opener.location.reload();
        window.close();
        </script>
</c:if>
</html>