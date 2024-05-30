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
        .container {
            margin-top: 20px;
        }
    </style>
</head>
<body>
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
    <form action="/updateOnBoard" method="get" >
        <button tpye="button">수정</button>
    </form>
    <a><button tpye="button">삭제</button></a>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>