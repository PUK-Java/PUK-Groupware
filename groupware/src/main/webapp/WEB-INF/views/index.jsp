<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .card-img-top{
            height: 15rem;
            object-fit: fill;
        }
          /* 검색 버튼 가로로 출력 */
          .btn-primary {
            white-space: nowrap; /* 텍스트 줄바꿈 방지 */
        }
    </style>
</head>
<body>
<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between h-100 mb-1">
 
    <h1 class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        로고 위치
        
    </h1>
    <div class="col-md-3 text-end">

        <c:choose>
            <c:when test="${loginUser == null}">
                <button type="button" class="btn btn-outline-primary" onclick="location.href='/login'">로그인</button>
                <button type="button" class="btn btn-primary" onclick="location.href='/signupform'">회원가입</button>
                </c:when>
            <c:when test="${loginUser != null}">
                ${loginUser.userName}님 반갑습니다!
                <button type="button" class="btn btn-outline-primary" onclick="location.href='/logout'">로그아웃</button>
                <button type="button" class="btn btn-outline-primary" onclick="location.href='/userpage'">내 정보</button>
            </c:when>
        </c:choose>

        <button type="button" class="btn btn-info" onclick="location.href='/projectregform'">프로젝트 생성</button>
    </div>
</div>
<nav class="navbar navbar-expand-lg border-bottom">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/">홈</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">펀딩</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">투자</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        더보기
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/boardmain">공지사항</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <!-- 작성중 -->
                        <li><a class="dropdown-item" href="/qnamain">QnA</a></li>
                    </ul>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="projectName">
                <button class="btn btn-primary" type="submit">검색</button>
            </form>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li class="nav-item">
      <a class="nav-link" aria-current="page" href="/?projectCategory=굿즈"><strong style="text-transform:uppercase">굿즈</strong></a>
    </li>
    <li class="nav-item">
        <a class="nav-link " aria-current="page" href="/?projectCategory=푸드"><strong style="text-transform:uppercase">푸드</strong></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" aria-current="page" href="/?projectCategory=전자제품"><strong style="text-transform:uppercase">전자제품</strong></a>
      </li>
  </ul>

<div class="row row-cols-2 row-cols-md-3 g-10 mx-5 mt-5">
<c:forEach var="project" items="${projects}">
    <div class="col">
        <a href="/projectDetail/${project.projectNo}">
            <div class="card h-100">
                <img src="images/projectThumbnails/${project.image}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${project.title}</h5>
                    <p class="card-text">${project.description}</p>
                </div>
            </div>
        </a>
    </div>
</c:forEach>
</div>
<div class="center text-center">
    <nav aria-label="Page navigation example">
       <ul class="pagination justify-content-center">
           <c:if test="${projectTotalPage != 0}">
                <form action="/" method="get">
                    <c:if test="${projectPage !=0}">
                        <li class="page-item"><a class="page-link" href="/?page=${projectPage -1}&projectCategory=${param.projectCategory}&projectName=${param.projectName}">이전</a></li>
                    </c:if>
                </form>
                <form action="/" method="get">
                    <c:if test="${projectPage != projectTotalPage}">
                        <li class="page-item"><a class="page-link" href="/?page=${projectPage +1}&projectCategory=${param.projectCategory}&projectName=${param.projectName}">다음</a></li>
                    </c:if>
                </form>
           </c:if>
       </ul>
   </nav>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    document.querySelectorAll(".nav-link").forEach((link) => {
    if (link.href === window.location.href) {
        link.classList.add("active");
        link.setAttribute("aria-current", "page");
    }
});
</script>
</body>
</html>