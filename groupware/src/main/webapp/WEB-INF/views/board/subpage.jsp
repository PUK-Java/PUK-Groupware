<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 상세</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* .container {
            margin-top: 20px;
        } */
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }
        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
        .btn-outline-light {
            white-space: nowrap;
        }
    </style>
</head>
<body>
    <main>
        <header class="p-3 bg-dark text-white">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
                    </a>
                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="/" class="nav-link px-2 text-white">Home</a></li>
                        <li><a href="#" class="nav-link px-2 text-white">About</a></li>
                    </ul>
                    <!-- <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3 d-flex" method="GET" action="/boardOnSearchList">
                        <input type="search" class="form-control form-control-dark me-2" placeholder="Search..." aria-label="Search" name="searchText" value="${param.searchText}">
                        <button type="submit" class="btn btn-outline-light">검색</button>
                    </form> -->
                    <div class="text-end">
                        <c:choose>
                            <c:when test="${loginUser == null}">
                                <button type="button" class="btn btn-outline-light me-2" onclick="location.href='/login'">Login</button>
                                <button type="button" class="btn btn-warning" onclick="location.href='/signupform'">Sign-up</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn btn-outline-light me-2" onclick="location.href='/logout'">Logout</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </header>
    </main>
    <div class="container">
        <br>
        <h3>게시물 상세</h3>
        <br>
        <div class="card">
            <div class="card-header">
                <h5>${board.title}</h5>
            </div>
            <div class="card-body">
                <p class="card-text">${board.content}</p>
            </div>
            <div class="card-footer text-muted">
                작성자: ${board.writer}  |  작성일: ${board.writeDate}  |  조회수 : ${board.viewCount}회 | 
            </div>
        </div>
        <br>
        <a href="${pageContext.request.contextPath}/boardmain" class="btn btn-primary">목록</a>
        <c:choose>    
            <c:when test="${loginUser.userId == board.writer}">
                <button type="button" class="btn btn-warning" onclick="location.href='/deleteOnBoard?boardNo=${board.boardNo}'">삭제</button>
                <button type="button" class="btn btn-warning" onclick="location.href='/update?boardNo=${board.boardNo}'">수정</button>
            </c:when>
        </c:choose>
    </div>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

    
<!-- <body>
<div class="container">
    <h3>게시물 상세</h3>
    <div class="card">
        <div class="card-header">
            <h5>${board.title}</h5>
        </div>
        <div class="card-body">
            <p class="card-text">${board.content}</p>
        </div>
        <div class="card-footer text-muted">
            작성자: ${board.writer} | 작성일: ${board.writeDate} | 
        </div>
    </div>
    <br>
    <a href="${pageContext.request.contextPath}/boardmain" class="btn btn-primary">목록</a>
    <c:choose>    
        <c:when test="${loginUser.userId == board.writer}">
            <button type="button" class="btn btn-warning" onclick="location.href='/deleteOnBoard?boardNo=${board.boardNo}'">삭제</button>
            <button type="button" class="btn btn-warning" onclick="location.href='/update?boardNo=${board.boardNo}'">수정</button>
        </c:when>
    </c:choose>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body> -->
</html>