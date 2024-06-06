
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>부울경 펀딩 사이트</title>
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
        /*카드에 마우스 올려두면 조금 커지게*/
        .card:hover{
            transform:scale(110%);
            transition: ease 0.2s;
        }

        /*a태그 텍스트 꾸미기 없애기*/
        a{
            text-decoration: none;
        }
        /*프로그래스 바 스타일 */
        .progress {
        height: 5px;
        background-color: #e9ecef;
        border-radius: 5px;
        overflow: hidden;
        margin-top: 10px;
    }
    /*프로그래스 바 스타일*/
    .progress-bar {
        height: 100%;
        background-color: #fd2b2b;
        transition: width 0.2s ease;
    }

    /* overlay를 가장 앞에 나오게 만들기*/
    .overlay{
        z-index: 1000;
        left:0;
        right: 0;
    }

    /*nav-link 글자 꾸미기 싹 다 없애기*/
    .nav-link{
        text-decoration: none;
        color: black;
    }

    /* nav-item (카테고리) 크기 동일하게 만들기*/
    .nav-item{
        width: 70px;
    }

    /* carousel-item(슬라이드 이미지) 높이 줄이기*/
    .carousel-inner > .carousel-item > a > img{
        top:0;
        left:0;
        min-width: 100%;
        height: 400px;
    }
    

    </style>
</head>
<body>

    <!-- 헤더 영역 -->
<div class="container d-flex align-items-center mb-1 mt-3">
    <a href="/">
         <img src="/images/logo/logo.jpg" width="100" height="100">
    </a>
    <div class="ms-auto me-auto col-4 col-md-4">
        <form role="search">
            <div class="input-group border border-1 rounded-pill border-info">
                <input class="form-control border-0 rounded-pill" type="search" placeholder="검색어를 입력해주세요" aria-label="Search" name="projectName">
                <span class="border-0">
                    <button class="btn btn-out-line-secondary" type="submit"><img src="/images/icons/search.svg" alt="Search"></button>
                </span>
            </div>
        </form>
    </div>
    <div class="d-flex">
        <c:choose>
            <c:when test="${loginUser == null}">
                <button type="button" class="btn border-0 text-nowrap" onclick="location.href='/login'">로그인</button>
                <button type="button" class="btn border-0 text-nowrap" onclick="location.href='/signupform'">회원가입</button>
                </c:when>
            <c:when test="${loginUser != null}">
                <div class="d-flex justify-content-center align-items-center text-white bg-primary rounded-circle fs-5" style="width: 40px; height: 40px;">
                        <span>${loginUser.userName.charAt(0)}</span>
                </div>
                <button type="button" class="btn border-0 text-nowrap" onclick="location.href='/logout'">로그아웃</button>
                <button type="button" class="btn border-0 text-nowrap" onclick="location.href='/userpage'">내 정보</button>
                
            </c:when>
        </c:choose>
            <button type="button" class="btn btn-info text-white text-nowrap" onclick="location.href='/projectregform'" id="projectProduce">프로젝트 생성</button>
    </div>
</div>
<!-- 헤더 영역 -->

<!-- 카테고리 영역 시작-->
<nav class="navbar border-bottom">
    <div class="container justify-content-center text-center">
        <ul class="d-flex list-unstyled">
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="#">
                    <img src="/images/icons/gameIcon.png" class="bi" width="25" height="25">
                    <br>
                    <span>굿즈</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="#">
                    <img src="/images/icons/fashionIcon.png" class="bi" width="25" height="25">
                    <br>
                    <span>패션</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="#">
                    <img src="/images/icons/petIcon.png" class="bi" width="25" height="25">
                    <br>
                    <span>반려동물</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link " aria-current="page" href="/?projectCategory=푸드">
                    <img src="/images/icons/foodIcon.png" class="bi" width="25" height="25">
                    <br>
                    <span>푸드</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="/?projectCategory=전자제품">
                    <img src="/images/icons/electricIcon.png" class="bi" width="25" height="25">
                    <br>
                    <span class="text-wrap">전자제품</span></a>
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="/?projectCategory=굿즈">
                    <img src="/images/icons/goodsIcon.png" class="bi" width="25" height="25">
                    <br>
                    <span>굿즈</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="#">
                    <img src="/images/icons/musicIcon.png" class="bi" width="25" height="25">
                    <br>
                    <span>음악</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="#">
                    <img src="/images/icons/homeIcon.png" class="bi" width="25" height="25">
                    <br>
                    <span>리빙</span>
                </a>
            </li>
        </ul>
    </div>
</nav>
<!-- 카테고리 영역 끝-->

<!-- 슬라이드 이미지 영역 시작-->
<div id="carouselExampleIndicators" class="carousel slide container" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" aria-label="Slide 4"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <a href="https://github.com/Koeyh">
        <img src="/images/nameCard/Heo.png" class="d-block w-100" alt="이미지 준비중">
      </div>
        </a>
      <div class="carousel-item">
        <a href="https://github.com//simwh123">
        <img src="/images/nameCard/Sim.png" class="d-block w-100" alt="이미지 준비중">
        </a>
      </div>
      <div class="carousel-item">
        <a href="https://github.com//vinca0224">
        <img src="/images/nameCard/Im.png" class="d-block w-100" alt="이미지 준비중">
        </a>
      </div>
      <div class="carousel-item">
        <a href="https://github.com/KangJeongTaek">
        <img src="/images/nameCard/Kang.png" class="d-block w-100" alt="이미지 준비중">
        </a>
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
<!-- 슬라이드 이미지 영역 끝-->

<!-- 카드 영역 시작-->
<div class="container">
<div class="row row-cols-1 row-cols-md-3 g-10 mx-5 mt-5 mb-5">
<c:forEach var="project" items="${projects}">
    <div class="col mb-5">
            <div class="card h-100 position-relative border-0">
                <img src="images/projectThumbnails/${project.image}" class="card-img-top" alt="이미지 준비중">
                <div class="card-body pb-0">
                    <p class="card-text">
                        <small><small>${project.category}</small></small>
                    </p>
                    <div class="card-text text-center mb-4">
                        <a href ="/projectDetail/${project.projectNo}" class="fs-4 card-title stretched-link link-dark">${project.title}</a>
                    </div>
                    <p class="card-text text-center"><small>${project.description}</small></p>
                    <p class="d-flex justify-content-center card-text text-muted">
                        <span class="flex-fill">
                            <span class="text-danger">
                                <small>
                                ${Math.floor((projectFundingMap[project.projectNo] * project.cost* 100) / project.targetCost)}%
                                </small>
                            </span>
                            <span>
                                <small>${String.format("%,d",project.targetCost)}원</small>
                            </span>
                        </span>
                        <span clas="flex-fill">
                            <small>종료일 : ${project.endDate}</small>
                        </span> 
                    </p>
                </div>
                <div class="card-body pt-0">
                    <div class="progress" role="progressbar">
                        <div class="progress-bar" aria-valuemax="100" aria-valuemin="0" 
                        style="width: ${Math.floor((projectFundingMap[project.projectNo] * project.cost* 100) / project.targetCost)}%"
                        aria-valuenow="${Math.floor((projectFundingMap[project.projectNo] * project.cost* 100) / project.targetCost)}"></div>
                    </div>
                </div>
            </div>
    </div>
</c:forEach>
</div>
<!-- 카드 영역 끝-->

<!-- 페이지네이션 영역 시작 -->
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
                    <c:if test="${projectPage < projectTotalPage-1}">
                        <li class="page-item"><a class="page-link" href="/?page=${projectPage +1}&projectCategory=${param.projectCategory}&projectName=${param.projectName}">다음</a></li>
                    </c:if>
                </form>
           </c:if>
       </ul>
   </nav>
</div>
</div>
<!-- 페이지네이션 영역 끝-->

<footer class="bg-dark text-white p-3 mt-5">
    <div class="container">
        <div class="col">
            <a class="text-white"href="/boardmain" style="text-decoration: none;">공지사항</a></li>
        </div>
        <div class="col">
            <a class="text-white" href="/qnamain" style="text-decoration: none;">QnA</a></li>
        </div>
    </div>
    <div class="container text-center">
        <p>© 2024 부울경</p>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>



//collapse가 마우스 클릭이 아니라 hover일 때 처리하기
const categoryLink = document.getElementById('categoryLink');
const categoryCollapse = document.getElementById('categoryCollapse');

//마우스가 올라가 있으면 
categoryLink.addEventListener('mouseenter',() =>{
    const bsCollapse = new bootstrap.Collapse(categoryCollapse,{
        show:true
    });
});
// 마우스가 떠난다면
categoryCollapse.addEventListener('mouseleave',() =>{
    const bsCollapse = bootstrap.Collapse.getInstance(categoryCollapse);
    bsCollapse.hide();
});

//스크롤 된다면
window.addEventListener('scroll',()=>{
    const bsCollapse = bootstrap.Collapse.getInstance(categoryCollapse);
    if(bsCollapse && categoryCollapse.classList.contains('show')){
        bsCollapse.hide();
    }
});

//프로젝트 생성 누를시 후원자 판단 만들꺼임 우현

var adminCheck = '${adminCheck}';
const projectButton = document.getElementById("projectProduce");
if(adminCheck == "null값"){
    projectButton.style.display ="none";
}
else if(adminCheck == "후원자"){
    projectButton.style.display ="none";
}
</script>
</body>

</html>