<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="puk.groupware.model.user.User_Info" %>
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
   
    </style>
</head>
<body>
    <header>
        <h1></h1>
    </header>
    <main>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <div class="container">
                <a class="navbar-brand href="#">프로젝트 상세 페이지</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link active"  href="http://localhost:8080/">홈</a></li>
                        <li class="nav-item"><a class="nav-link"  href="http://localhost:8080/boardmain">문의</a></li>
                    </ul>
                </div>
            </div>
        </nav> 
        <div class="container mt-5">
            <div class="row align-items-stretch">
                <div class="col-md-6 d-flex">
                    <div class="card w-100">
                        <img src="/images/projectThumbnails/${data.image}" class="card-img-top" alt="준비중">

                        <div class="container mt-5">
                            <div class="card-footer row">
                                <div class="col text-center">
                                    <form action="/sponTable/${data.projectNo}" method="post">
                                        <button type="submit" class="btn btn-secondary btn-primary-custom ms-2" onclick="huCheck">후원하기</button>
                                    </form>
                                </div>
                                <div class="col text-center">
                                <c:choose>
                                    <c:when test="${wishListCheck}">
                                        <button class="btn btn-secondary w-50 ms-2" id ="supportButton" data-project-no=${data.projectNo} onclick="toggleWishList(this)" data-user-id=${sessionScope.loginUser.userId}>찜해제</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-success w-50 ms-2" id="supportButton" data-project-no="${data.projectNo}" onclick="toggleWishList(this)" data-user-id="${sessionScope.loginUser.userId}">찜하기</button>
                                    </c:otherwise>
                                </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 d-flex">
                    <div class="card w-100">
                        <div class="card-body">
                            <h5 class="card-title" style="text-align: center; line-height: 5vh;">${data.title}</h5>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">후원가격: ${data.cost}</li>
                                <li class="list-group-item" id="end123" data-target-cost="${data.targetCost}">목표 금액: ${data.targetCost}원</li>
                                <li class="list-group-item" id="state123" data-state-cost="${count * data.cost}">현재 금액: ${count * data.cost}원</li>
                                <li class="list-group-item">프로젝트 시작일: ${data.startDate}</li>
                                <li class="list-group-item">프로젝트 종료일: ${data.endDate}</li>
                                <li class="list-group-item">프로젝트 남은 일수: ${daysBetween}일</li>
                                <li class="list-group-item" id="state">프로젝트 상태:</li>
                                <li class="list-group-item">프로젝트 카테고리: ${data.category}</li>
                                <li class="list-group-item" id="stateP"></li>
                                <li class="list-group-item">                                                                   
                                    <a href="/projectModify/${data.projectNo}">                                     
                                        <button class="btn btn-primary btn-primary-custom w-100" id="checkId">수정</button>
                                    </a>                                                                       
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div>
                    <p><h2>제품설명</h2></p>
                    ${data.description}
                </div>
            </div>
        </div>
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
    let loginUser = document.getElementById('supportButton').getAttribute('data-user-id');
    async function toggleWishList(button){
        if(loginUser !== ''){
            const loginUser = button.getAttribute('data-user-id');
            const project = button.getAttribute('data-project-no');
            const user = button.getAttribute('data-user-id');
            const response = await fetch('/wishList/toggle',{
                method :'POST',
                headers : {
                    'Content-Type' : 'application/json'
                },
                body : JSON.stringify({
                    projectNo : project,
                    userId : user
                })
            });
            if(response.ok){
                const data = await response.json();
                if(data.isWished){
                    button.innerText = '찜해제';
                    button.classList.replace('btn-success','btn-secondary');
                    alert('목록에 추가 됐습니다.');
                }else{
                    button.innerText= '찜하기';
                    button.classList.replace('btn-secondary','btn-success');
                    alert('목록에서 제거 됐습니다.');
                }
            }else{
                alert("서버 오류가 발생했습니다.");
            }
        }else{
            alert("로그인 해주세요.");
            location.replace("/login");
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
    
     
    
</script>

</body>
</html>