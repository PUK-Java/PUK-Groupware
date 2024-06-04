<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QnA 상세</title>
    <link rel="stylesheet" href="/css/boardStyle.css">
    <style>
        .container {
            max-width: 800px;
            margin: auto;
        }
        .comments {
            margin-top: 30px;
        }
        .comment-form {
            margin-top: 20px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background-color: #f9f9f9;
        }
        .comment-form textarea {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            resize: vertical;
        }
        .comment-form input[type="submit"] {
            margin-top: 10px;
            background-color: #5cb85c;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .comment-form input[type="submit"]:hover {
            background-color: #4cae4c;
        }
        .comment-list {
            margin-top: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background-color: #fff;
        }
        .comment-item {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .comment-item:last-child {
            border-bottom: none;
        }
        .comment-author {
            font-weight: bold;
            color: #333;
        }
        .comment-date {
            font-size: 12px;
            color: #999;
        }
    </style>
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

        <!-- 댓글 섹션 시작 -->
        <div class="comments">
            <h4>댓글</h4>
            <!-- 댓글 목록 -->
            <div class="comment-list">
                <c:forEach var="comment" items="${comments}">
                    <div class="comment-item">
                        <p>관리자</p>
                        <p>${comment.qnaCommentContent}</p>
                        <div class="commentDate">${comment.qnaCommentWriteDate}</div>
                    </div>
                </c:forEach>
            </div>
            
            <!-- 댓글 작성 폼 -->
            <c:if test="${isAdmin}">
                <div class="comment-form">
                    <!-- <form action="${pageContext.request.contextPath}/addComment" method="post"> -->
                    <form action="/addComment" method="get">
                        <input type="hidden" name="qnaNo" value="${board.qnaNo}">
                        <textarea name="content" rows="4" placeholder="댓글을 입력하세요..." required></textarea> 
                        <input type="submit" value="댓글 작성">
                    </form>
                </div>
            </c:if>
        </div>
        <!-- 댓글 섹션 끝 -->

    </div>
</body>
</html>
