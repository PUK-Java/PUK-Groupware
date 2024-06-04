<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QnA 상세</title>
    <link rel="stylesheet" href="/css/boardStyle.css">
</head>
<body>
    <main>
        <header class="navbar">
            <div class="logo">
                <a href="/">
                    <img src="/images/logo/logo.png" width="45" height="45" alt="Logo">
                </a>
            </div>
            <div class="nav-links">
                <a href="/">Home</a>
                <a href="#">About</a>
            </div>
            <div class="auth-buttons">
                <c:choose>
                    <c:when test="${loginUser == null}">
                        <button type="button" class="btn-outline-light" onclick="location.href='/login'">Login</button>
                        <button type="button" class="btn-warning" onclick="location.href='/signupform'">Sign-up</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn-outline-light" onclick="location.href='/logout'">Logout</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </header>
    </main>
    <div class="container">
        <br>
        <h3>QnA 상세</h3>
        <br>
        <div class="card">
            <div class="card-header">
                <h5>${board.qnaTitle}</h5>
            </div>
            <div class="card-body">
                <p class="card-text">${board.qnaContent}</p>
            </div>
            <div class="card-footer text-muted">
                작성자: ${board.qnaWriter.userId}  |  작성일: ${board.qnaWriteDate}  |  조회수 : ${board.viewCount}회 |
            </div>
        </div>
        <br>
        <!-- <a href="${pageContext.request.contextPath}/boardmain" class="btn btn-default">목록</a> -->
        <button type="button" class="btn btn-normal" onclick="location.href='${pageContext.request.contextPath}/qnamain'">목록</button>
        <c:choose>
            <c:when test="${loginUser.userId == board.qnaWriter.userId}">
                <button type="button" class="btn btn-warning" onclick="location.href='/deleteQna?qnaNo=${board.qnaNo}'">삭제</button>
                <button type="button" class="btn btn-warning" onclick="location.href='/preQnaUpdate?qnaNo=${board.qnaNo}'">수정</button>
            </c:when>
        </c:choose>
    </div>
</body>
</html>
