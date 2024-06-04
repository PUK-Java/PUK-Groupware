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
    <form id="checkform"  method="post">
        <input type="password" name="password">
        <input type="button" onclick="check()">
    </form>
</body>
<script>
    function check(){
        form = document.getElementById("checkform");
        form.submit("/userPwCheck");
        window.close();
    }
</script>
</html>