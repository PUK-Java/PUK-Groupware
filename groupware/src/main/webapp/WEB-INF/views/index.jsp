
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
    

    </style>
</head>
<body>
<div class="container d-flex align-items-center mb-1 mt-3">
    <a href="/">
        <div class="mb-2 mb-md-0">
            <h1>
                로고 위치
            </h1>
        </div>
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
<nav class="navbar border-bottom">
    <div class="container justify-content-center">
                <a class="nav-link" aria-current="page" href="/?projectCategory=굿즈">
                    <img src="/images/icons/goods.svg" class="bi" width="32" height="23">
                    <br>
                    <strong style="text-transform:uppercase">굿즈</strong>
                </a>
                <a class="nav-link " aria-current="page" href="/?projectCategory=푸드">
                    <img src="/images/icons/foodIcon.png" class="bi" width="32" height="23">
                    <br>
                    <strong style="text-transform:uppercase">푸드</strong></a>
                <a class="nav-link" aria-current="page" href="/?projectCategory=전자제품">
                    <img src="/images/icons/electricIcon.png" class="bi" width="32" height="23">
                    <br>
                    <strong style="text-transform:uppercase">전자제품</strong></a>    

    </div>
</nav>


<div class="container">
<div class="row row-cols-1 row-cols-md-3 g-10 mx-5 mt-5 mb-5">
<c:forEach var="project" items="${projects}">
    <div class="col mb-5">
            <div class="card h-100 position-relative border-0">
                <img src="images/projectThumbnails/${project.image}" class="card-img-top" alt="...">
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