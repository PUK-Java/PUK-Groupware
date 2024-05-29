<!DOCTYPE html>
<html>
<head>
    <title>Signup Form</title>
</head>
<body>
    <h1>Signup Form</h1>
    <form action="/submitForm" method="post">
        <label for="ID">ID:</label>
        <input type="text" id="ID" name="ID" value="${param.ID}" required><br>
        <label for="NAME">Name:</label>
        <input type="text" id="NAME" name="NAME" value="${param.NAME}" required><br>
        <label for="PW">Password:</label>
        <input type="password" id="PW" name="PW" value="${param.PW}" required><br>
        <label for="ADDRESS">Address:</label>
        <input type="text" id="ADDRESS" name="ADDRESS" value="${param.ADDRESS}" required><br>
        <label for="EMAIL">Email:</label>
        <input type="email" id="EMAIL" name="EMAIL" value="${param.EMAIL}" required><br>
        <label for="PHONE_NUMBER">Phone Number:</label>
        <input type="text" id="PHONE_NUMBER" name="PHONE_NUMBER" value="${param.PHONE_NUMBER}" required><br>
        <label for="ACCOUNT">Account:</label>
        <input type="text" id="ACCOUNT" name="ACCOUNT" value="${param.ACCOUNT}" required><br>
        <label for="BIRTH">Birthdate:</label>
        <input type="date" id="BIRTH" name="BIRTH" value="${param.BIRTH}" required><br>
        <label for="ADMIN">Admin:</label>
        <input type="text" id="ADMIN" name="ADMIN" value="${param.ADMIN}" maxlength="1" required><br>

        <!-- 실패 이유가 있을 경우에만 표시 -->
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">회원가입 실패: <c:out value="${errorMessage}"/></p>
        </c:if>

        <button type="submit">Submit</button>
    </form>
</body>
</html>