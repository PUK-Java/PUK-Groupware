<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 게시판 메인</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
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
                <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3 d-flex" method="GET" action="/boardOnSearchList">
                    <input type="search" class="form-control form-control-dark me-2" placeholder="Search..." aria-label="Search" name="searchText" value="${param.searchText}">
                    <button type="submit" class="btn btn-outline-light">검색</button>
                </form>
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
    <h3>공지사항</h3>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
            <th>조회수</th>
            
        </tr>
        </thead>
        <tbody>
            <c:forEach var="board" items="${boards}">
                <tr>
                    <td>${board.boardNo}</td>
                    <td><a href="${pageContext.request.contextPath}/detail?boardNo=${board.boardNo}">${board.title}</a></td>
                    <td>${board.writer}</td>
                    <td>${board.writeDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <hr/>
    <form action="/write" method="get" style="display: inline;">
        <button type="submit" class="btn btn-default pull-right">글쓰기</button>
    </form>
    <div class="center text-center">
        <nav aria-label="Page navigation example">
           <ul class="pagination justify-content-center">
               <c:if test="${totalPage != 0}">
                    <li class="page-item"><a class="page-link" href="/boardmain?page=${currentPage -1}">이전</a></li>
                    <li class="page-item"><a class="page-link" href="/boardmain?page=${currentPage +1}">다음</a></li>
               </c:if>
           </ul>
       </nav>
   </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.js"></script>
</body>
</html>
