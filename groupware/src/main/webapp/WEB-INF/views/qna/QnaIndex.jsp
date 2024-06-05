<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QnA 게시판 메인</title>
    <link rel="stylesheet" href="/css/boardStyle.css">
    <script>
        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.get('success') === 'true') {
                alert('QnA가 성공적으로 수정되었습니다!');
            }
        };
    </script>
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
                <a href="/boardmain">공지사항</a>
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
        <h3>QnA 게시판</h3>
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
                <c:forEach var="qnaBoard" items="${qnaBoards}">
                    <tr>
                        <td>${qnaBoard.qnaNo}</td>
                        <td><a href="${pageContext.request.contextPath}/qnadetail?qnaNo=${qnaBoard.qnaNo}">${qnaBoard.qnaTitle}</a></td>
                        <td>${qnaBoard.qnaWriter.userId}</td>
                        <td>${qnaBoard.qnaWriteDate}</td>
                        <td>${qnaBoard.viewCount}</td>
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
                <ul class="pagination justify-content-center">
                    <c:if test="${qnaTotalPage != 0}">
                         <form action="/" method="get">
                             <c:if test="${qnaCurrentPage !=0}">
                                <li class="page-item"><a class="page-link" href="/qnamain?page=${qnaCurrentPage - 1}">이전</a></li>
                            </c:if>
                         </form>
                         <form action="/" method="get">
                             <c:if test="${qnaCurrentPage != qnaTotalPage}">
                                <li class="page-item"><a class="page-link" href="/qnamain?page=${qnaCurrentPage + 1}">다음</a></li>
                            </c:if>
                         </form>
                    </c:if>
                </ul>
            </nav>
       </div>
    </div>
</body>
</html>