<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QnA 게시판 메인</title>
    <link rel="stylesheet" href="/css/boardStyle.css">
</head>
<body>
    <main>
        <header class="navbar">
            <div class="logo">
                <a href="/">
                    <img src="/images/logo/logo.jpg" width="45" height="45" alt="Logo">
                </a>
            </div>
            <div class="nav-links">
                <a href="/">Home</a>
                <a href="#">About</a>
            </div>
            <form class="search-form" method="GET" action="/boardOnSearchList">
                <input type="search" placeholder="Search..." aria-label="Search" name="searchText" value="${param.searchText}">
                <button type="submit">검색</button>
            </form>
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
        <h3>QnA</h3>
        <table class="table">
            <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성 일자</th>
                <th>조회수</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="board" items="${qnaBoards}">
                    <tr>
                        <td>${board.qnaNo}</td>
                        <!-- <td>${board.qnaTitle}</td> -->
                        <td><a href="${pageContext.request.contextPath}/qnadetail?qnaNo=${board.qnaNo}">${board.qnaTitle}</a></td>
                        <td>${board.qnaWriter.userId}</td>
                        <td>${board.qnaWriteDate}</td>
                        <td>${board.viewCount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <hr/>
        <form action="/qnaWrite" method="get" style="display: inline;">
            <button type="submit" class="btn btn-default btn-normal pull-right">글쓰기</button>
        </form>
        <div class="center text-center">
            <nav aria-label="Page navigation example">
               <ul class="pagination">
                   <c:if test="${totalPage != 0}">
                        <li class="page-item"><a class="page-link" href="/QnAIndex?page=${currentPage -1}">이전</a></li>
                        <li class="page-item"><a class="page-link" href="/QnAIndex?page=${currentPage +1}">다음</a></li>
                   </c:if>
               </ul>
           </nav>
       </div>
    </div>
</body>
</html>