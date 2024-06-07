<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>프로젝트 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        header {
            background-image: url('https://via.placeholder.com/1920x400');
            background-size: cover;
            background-position: center;
            height: 200px;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
        }
        header h1 {
            font-size: 3rem;
        }
        .nav-link {
            color: #fff !important;
        }
        .nav-link:hover {
            background-color: #0056b3;
            color: #fff !important;
        }
        .card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            height: 100%;
        }
        .card-body {
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        .card-title {
            margin-top: 0;
            margin-bottom: 10px;
        }
        .card-footer {
            display: flex;
            justify-content: space-between;
            padding: 10px;
        }
        footer .social-icons {
            margin-top: 10px;
        }
        footer .social-icons a {
            color: white;
            margin: 0 10px;
            font-size: 1.5rem;
        }
        .card-img-top{
            height: 25rem;
            object-fit: fill;
        }
        .btn-primary-custom {
            background-color: #007bff;
            border-color: #007bff;
            width: 75%;
        }
        .btn-primary-custom:hover {
            background-color: #0056b3;
            border-color: #004085;
        }
        .btn-secondary-custom {
            background-color: #6c757d;
            border-color: #6c757d;
            width: 75%;
        }
        .btn-secondary-custom:hover {
            background-color: #5a6268;
            border-color: #4e555b;
        }
        /* 로그인 해주십시오.에 대한 글씨 위치,색 테두리, 테두리 색 css */
        .btn-login{
            text-align: left;
            color: #6c757d;
            border-radius: 0.25rem;
            border: 1px solid rgba(0, 0, 0, 0.1);
            padding: 13px 20px;
            align-items: center;
            transition: box-shadow 0.2s;
        }
        /* 배경화면 바뀌는게 맘에 안 들어서 그냥 쉐도우 효과만 주기 */
        .btn-login:hover{
            border: 1px solid rgba(0, 0, 0, 0.1);
            box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.2);
            background-color: white;
            color : #6c757d; 
        }
        /*버튼에서 밑줄 없애기*/
        a.d-block{
            text-decoration: none;
        }
        
        /*commentTextAreaResize 불가능하게*/
        #commentContent{
            resize: none;
        }

        /*modal-comment-all의 최대 크기 설정하고 스크롤 생성되게*/
        .modal-comment-all{
            max-height: 500px;
            overflow-y: auto;
        }

        .btn-primary {
            white-space: nowrap; /* 텍스트 줄바꿈 방지 */
        }
        .progress-container {
    width: 80%;
    background-color: #e0e0e0;
    border-radius: 25px;
    overflow: hidden;
    margin: 20px auto; /* Center align */
}

.progress-bar {
    width: 0;
    height: 8px;
    background-color: red;
    border-radius: 25px;
}

    </style>
</head>
<body>
    <div class="container d-flex flex-wrap align-items-center justify-content-center justify-content-md-between mb-1 mt-3">
        <h1 class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            로고 위치
        </h1>
        <div class="col text-end">
            <c:choose>
                <c:when test="${loginUser == null}">
                    <button type="button" class="btn btn-outline-primary" onclick="location.href='/login'">로그인</button>
                    <button type="button" class="btn btn-primary" onclick="location.href='/signupform'">회원가입</button>
                    </c:when>
                <c:when test="${loginUser != null}">
                    <span class="text-primary">${loginUser.userName}</span>
                    <small>님 반갑습니다!</small>
                    <button type="button" class="btn btn-outline-primary" onclick="location.href='/logout'">로그아웃</button>
                    <button type="button" class="btn btn-outline-primary" onclick="location.href='/userpage'">내 정보</button>
                    
                </c:when>
            </c:choose>
                <button type="button" class="btn btn-info" onclick="location.href='/projectregform'" id="projectProduce">프로젝트 생성</button>
        </div>
    </div>
    <nav class="navbar navbar-expand-lg border-bottom">
        <div class="container">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/"><strong>홈</strong></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-bs-toggle="collapse" data-bs-target="#categoryCollapse" aria-expanded="false" id="categoryLink"><strong>카테고리</strong></a>
                        <div class="collapse position-fixed overlay bg-light border" id="categoryCollapse">
                            <div class="row justify-content-center text-center">
                                <div class="col-1">
                                    <a class="nav-link" aria-current="page" href="/?projectCategory=굿즈">
                                        <img src="/images/icons/goods.svg" class="bi" width="32" height="23">
                                        <br>
                                        <strong style="text-transform:uppercase">굿즈</strong>
                                    </a>
                                </div>
                                <div class="col-1">
                                    <a class="nav-link " aria-current="page" href="/?projectCategory=푸드">
                                        <img src="/images/icons/food.svg" class="bi" width="32" height="23">
                                        <br>
                                        <strong style="text-transform:uppercase">푸드</strong></a>
                                </div>
                                <div class="col-1">
                                    <a class="nav-link" aria-current="page" href="/?projectCategory=전자제품">
                                        <img src="/images/icons/electric.svg" clas="bi" width="32" height="23">
                                        <br>
                                        <strong style="text-transform:uppercase">전자제품</strong></a>    
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            더보기
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/boardmain">공지사항</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/qnamain">QnA</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="검색어를 입력해주세요" aria-label="Search" name="projectName">
    
                    <button class="btn btn-primary" type="submit">검색</button>
                </form>
            </div>
        </div>
    </nav>
    <main>

        <div class="container mt-5">
            <div class="row align-items-stretch">
                <div class="col-md-6 d-flex">
                    <div class="card w-100">
                        <img src="/images/projectThumbnails/${data.image}" class="card-img-top" alt="준비중">

                        <div class="container mt-5">
                            <div class="card-footer row">

                                <a href="/projectModify/${data.projectNo}">
                                    <button class="btn btn-primary btn-primary-custom w-100" id="checkId">수정</button>
                                </a>

                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-6 d-flex">
                    <div class="card w-100" >
                        
                        <div class="d-flex justify-content-between">
                            <h6 style="font-size: small; margin-top: 10px; padding-left: 10px;">카테고리 > ${data.category}</h6>                                                  
                            <c:choose>
                                <c:when test="${wishListCheck}">
                                    <button class="btn" id ="wishButton" data-project-no=${data.projectNo} onclick="toggleWishList(this)" style="font-size: 32px;" data-user-id=${sessionScope.loginUser.userId}>❤️</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn" id="wishButton" data-project-no="${data.projectNo}" onclick="toggleWishList(this)" style="font-size: 32px;" data-user-id="${sessionScope.loginUser.userId}">🤍</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                           
                        <div style="padding-left: 10px; width: 9%; background-color: tomato; border-radius: 30px; color: white; margin-left: 10px;">D-${daysBetween}</div>
                        <div class="card-body" >
                            <h4 class="card-title" style="text-align: center; line-height: 5vh; margin-top: -30px;">${data.title}</h4>
                            <table class="table">
                                <tr>
                                    <td>후원가격</td>
                                    <td>${data.cost}</td>
                                </tr>
                                <tr>
                                    <td>목표 금액</td>
                                    <td id="end123" data-target-cost="${data.targetCost}">${data.targetCost}원</td>
                                </tr>
                                <tr>
                                    <td>현재 금액</td>
                                    <td id="state123" data-state-cost="${count * data.cost}">${count * data.cost}원</td>
                                </tr>
                                <tr>
                                    <td>프로젝트 시작일</td>
                                    <td>${data.startDate}</td>
                                </tr>
                                <tr>
                                    <td>프로젝트 종료일</td>
                                    <td>${data.endDate}</td>
                                </tr>
                                <tr>
                                    <td>프로젝트 상태</td>
                                    <td id="state"></td>
                                </tr>
                            </table>
                            <p id="stateP" class="text-center"></p>
                            <div class="progress-container">
                                <div class="progress-bar" id="progressBar"></div>
                            </div>
                            <div class="card-footer row">
                                <div class="col text-center">
                                    <form action="/sponTable/${data.projectNo}" method="post" id="eventCheck">
                                        <button type="submit" class="btn btn-secondary btn-primary-custom w-50 ms-2" onclick="huCheck()" id="huCheck">후원하기</button>  
                                    </form>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                <div class="h2 my-3">
                    <strong>프로젝트 소개</strong>
                </div>
                <div class="fs-6 mx-5 my-5">
                    <p>${data.description}</p>
                </div>
            <div class="h2 my-3">
                <strong>프로젝트 커뮤니티</strong>
            </div>
            <div class="mt-2 mx-5 mb-5">
                <c:choose>
                    <c:when test="${loginUser == null}">
                        <div class="row mb-4">
                            <div class="col-8">
                                <a href="/login" class="d-block">
                                    <button class="d-flex btn btn-outline-secondary w-100 btn-login justify-content-between">
                                    <span>로그인해주세요.</span>
                                    </button>
                                </a>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${!supportCheck}">
                        <div class="row mb-4">
                            <div class="col-8">
                                <a class="d-block">
                                    <button class="d-flex btn btn-outline-secondary w-100 btn-login justify-content-between" disabled>
                                    <span>후원자만 글을 쓸 수 있습니다.</span>
                                    </button>
                                </a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="row mb-4">
                            <div class="col-8">
                                <button class="d-flex btn btn-outline-secondary w-100 btn-login justify-content-between" data-bs-toggle="modal" data-bs-target="#commentModal">
                                    <span>글쓰기</span>
                                </button>
                            </div>
                        </div>

                    </c:otherwise>
                </c:choose>
                    <c:forEach var="projectComment" items="${projectComments}">
                        <div class="comment-card card col-8 my-0">
                            <div class="card-body">
                                <div class="row mb-1 d-flex align-items-center">
                                    <img class="col-1 pe-0" src="/images/icons/userIcon.png" alt="...준비중" height="32px">
                                    <p class="col-3 card-title h6">${projectComment.userInfo.userId}</p>
                                </div>
                                <p class="card-subtitle mb-2 text-muted"><small>작성일 : ${projectComment.projectCommentWritDateTime}</small></p>
                                <div class="row">
                                    <div class="col-10">
                                        <p class="card-text">${projectComment.projectCommentContent}</p>
                                    </div>
                                    <c:if test="${projectComment.userInfo.userId == sessionScope.loginUser.userId}">
                                    <div class="col-2 d-flex justify-content-end align-items-end">
                                        <a class="card-text text-muted" href="#" onclick="deleteComment('${projectComment.projectCommentNo}')"><small>삭제</small></a>
                                    </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${isCommentTotalPages}">
                            <div>
                                <div class="col-8">
                                        <button class="d-flex btn btn-outline-secondary w-100 btn-login justify-content-center" data-bs-toggle="modal" data-bs-target="#commentAllModal">
                                        <span>전체보기</span>
                                        </button>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
          <!-- 코멘트 작성 모달 시작 -->
          <div class="modal fade" id="commentModal" data-bs-backdrop="static" data-bs-keyboard="false">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="commentModalTitle">글쓰기</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="코멘트를 남겨주세요" id="commentContent" style="height: 300px" maxlength="100"></textarea>
                        <label for="commentContent">코멘트(100자 제한)</label>
                    </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-primary" onclick="commentReg(this)">등록</button>
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                </div>
              </div>
            </div>
          </div>
          </div>
          <!-- 코멘트 작성 모달 끝 -->

          <!-- 전체 보기 모달 시작 -->
          <div class="modal fade" id="commentAllModal" data-bs-backdrop="static" data-bs-keyboard="false">
            <div class="modal-dialog modal-lg">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="commentAllModalTitle">전체 코멘트</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body modal-comment-all">
                    <c:forEach var="projectAllComment" items="${projectAllComments}">
                        <div class="comment-card card my-0">
                            <div class="card-body">
                                <div class="row mb-1 d-flex align-items-center">
                                    <img class="col-1 pe-0" src="/images/icons/userIcon.png" alt="...준비중" height="32px">
                                    <p class="col-3 card-title h6">${projectAllComment.userInfo.userId}</p>
                                </div>
                                <p class="card-subtitle mb-2 text-muted"><small>작성일 : ${projectAllComment.projectCommentWritDateTime}</small></p>
                                <div class="row">
                                    <div class="col-10">
                                        <p class="card-text">${projectAllComment.projectCommentContent}</p>
                                    </div>
                                    <c:if test="${projectAllComment.userInfo.userId == sessionScope.loginUser.userId}">
                                    <div class="col-2 d-flex justify-content-end align-items-end">
                                        <a class="card-text text-muted" href="#" onclick="deleteComment('${projectAllComment.projectCommentNo}')"><small>삭제</small></a>
                                    </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                </div>
              </div>
            </div>
          </div>
          <!-- 전체보기 모달 끝 -->
    </main>
    <footer class="bg-dark text-white p-3 mt-5">
        <div class="container text-center">
            <p>© 2024 부울경</p>
            <div class="social-icons">
                <a href="#"><i class="fab fa-facebook"></i></a>
                <a href="#"><i class="fab fa-twitter"></i></a>
                <a href="#"><i class="fab fa-instagram"></i></a>
            </div>
        </div>
    </footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>

    //로그인한 유저 id 찾기. 없으면 빈 문자열입니다.
    let loginUser = document.getElementById('wishButton').getAttribute('data-user-id');

    //toggleWishList 찜하기/ 찜해제 버튼을 왔다갔다하기 위해 Fetch API를 사용해서 보냅니다.
    async function toggleWishList(button){
        //로그인 했는지 체크
        if(loginUser !== ''){
            //필요한 데이터를 변수에 저장합니다.
            const loginUser = button.getAttribute('data-user-id');
            const project = button.getAttribute('data-project-no');
            const user = button.getAttribute('data-user-id');
            //여기서 response는 fetch API를 보내고 컨트롤러에서 반환한 데이터를 가지고 있습니다.
            const response = await fetch('/wishList/toggle',{
                //보내는 방식은 POST
                method :'POST',
                //헤더에서 이 컨텐츠 타입이 json 타입이라는 명시합니다.
                headers : {
                    'Content-Type' : 'application/json'
                },
                //바디에 원하는 데이터들은 실어 보냅니다.
                body : JSON.stringify({
                    projectNo : project,
                    userId : user
                })
            });
            //돌려받은 response가 ok라면 response를 json으로 변화시켜 작성합니다.
            //response 안의 데이터 접근은 객체.속성 과 같은 방식으로 접근합니다.
            if(response.ok){
                const data = await response.json();
                if(data.isWished){
                    //조건에 따라 버튼의 색, 텍스트를 변화시킵니다.
                    
                    button.innerText = '❤️';
                    button.classList.replace('btn-success','btn-secondary');
                    
                }else{
                    //조건에 따라 버튼의 색, 텍스트를 변화시킵니다.
                    button.innerText= '🤍';
                    button.classList.replace('btn-secondary','btn-success');
                    
                }
            }else{
                alert("서버 오류가 발생했습니다.");
            }
        }else{
            alert("로그인 해주세요.");
            location.replace("/login");
        }
    }


    //코멘트 등록을 위한 API를 보냅니다.
    async function commentReg(button){
        //세션에서 로그인 유저의 아이디를 찾습니다.
        //글쓰기는 반드시 로그인 해야 가능하므로 null값이나 빈 문자열이 될 일은 없습니다.
        const longinUserId = "${sessionScope.loginUser.userId}";
        const projectNo = "${data.projectNo}";
        const commentContent = document.getElementById('commentContent').value;
        const response = await fetch('/projectCommentReg',{
            method : 'POST',
            headers : {
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify({
                projectNo : projectNo,
                loginUserId : longinUserId,
                commentContent : commentContent
            })
        });
        //응답을 받아서 처리합시다. json 파일을 받아서 처리할 것은 없으므로 페이지 새로고침만 해줍시다.
        if(response.ok){
            const data = await response.json();
            location.reload(true);
        }

        if(response.status === 500){
            alert('서버에서 오류가 발생했습니다.');
        }
    }

    //코멘트 삭제를 위한 API를 보냅니다.
    async function deleteComment (projectCommentId){
        const response = await fetch('/deleteComment/' + projectCommentId,
        {
            method : "DELETE",
            headers: {
                'Content-Type' : 'application/json'
            }
        })
        if(response.ok){
            alert('댓글이 삭제됐습니다.');
            location.reload();
        }else{
            alert('댓글 삭제에 실패했습니다.');
        }
    }
    


    // body에서 모델에 저장된 값을 스크립트로 가져와서 처리하기위해, ID,data로 가져와서 변수에 넣는다

    const end = document.getElementById('end123');
    const state1 = document.getElementById('state123');

    const endCost = end.getAttribute('data-target-cost');
    const stateCost = state1.getAttribute('data-state-cost');
    if(Number(stateCost)<Number(endCost)){
    const projectCondition ="프로젝트 상태: 진행중";
    document.getElementById('state').innerText = projectCondition;
    }
    else if(Number(stateCost)>=Number(endCost)){
        const projectCondition ="프로젝트 상태: 목표달성";
        document.getElementById('state').innerText = projectCondition;
    }
    // 달성률을 나타내기위해서 변수를 숫자로 바꿔서 계산
    const costCheck = Number(stateCost)/Number(endCost)*100;
    const Attainment = "달성률: " + Math.floor(costCheck) + "%";
    const stateData =document.getElementById('stateP');
    // 프로젝트 상세페이지에 100프로를 기준으로 넘을시 글자색 빨간색으로
    if(costCheck >100){
        stateData.style.cssText = 'color:red';
        stateData.innerText = Attainment + "  초과달성!!";
    }
    else if(costCheck < 100){
        stateData.innerText = Attainment;
    }
    else if(costCheck == 100){
        stateData.style.cssText = 'color:red';
        stateData.innerText = Attainment + "  목표달성";
    }

    //세션에 저장된 로그인된 id와 프로젝트테이블에있는 id를 비교하여 같으면 프로젝트 상세페이지에 수정하기 출력
    const loginUserIdCheck = "${loginUser.userId}";
    var projectUserId = '${checkId}';
    console.log(loginUserIdCheck);
    if(loginUserIdCheck == ''){
        document.getElementById("checkId").style.display='none';
    }else if(loginUserIdCheck != projectUserId){
        document.getElementById("checkId").style.display='none';
    }
    document.getElementById('eventCheck').addEventListener('submit', function(event) {
            event.preventDefault(); // 폼 제출 시 새로고침 방지
            const button = document.getElementById('huCheck');
            button.disabled=true;

            huCheck(); // huCheck 함수 호출
            
            // 2초 딜레이 후 폼 제출
            setTimeout(() => {
                this.submit(); // 폼 제출
            }, 1000); // 1000 밀리초 = 1초
        });
    function huCheck(){
        const canvas = document.getElementById('custom_canvas');
    const button = document.getElementById('huCheck');

    const jsConfetti = new JSConfetti({canvas});
        jsConfetti.addConfetti()     
    }

    function updateProgressBar() {
    var progressBar = document.getElementById('progressBar');
    var targetCost = document.getElementById('end123').dataset.targetCost;
    var currentCost = document.getElementById('state123').dataset.stateCost;
    var progress = (currentCost / targetCost) * 100;

    progressBar.style.width = progress + '%';
}

// Call the function to update the progress bar on page load
updateProgressBar();

    
     
    
</script>
<script src="https://cdn.jsdelivr.net/npm/js-confetti@latest/dist/js-confetti.browser.js"></script>
</body>
</html>