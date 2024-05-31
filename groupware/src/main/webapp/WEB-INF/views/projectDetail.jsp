<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
                        <li class="nav-item"><a class="nav-link"  href="#">문의</a></li>
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
                                        <button type="submit" class="btn btn-primary btn-primary-custom ms-2">후원하기</button>
                                    </form>
                                </div>
                                <div class="col text-center">
                                    <button class="btn btn-secondary btn-secondary-custom ms-2">찜</button>
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
                                <li class="list-group-item" id="end123" data-target-cost="${data.targetCost}">목표 금액: ${data.targetCost}원</li>
                                <li class="list-group-item" id="state123" data-state-cost="${count * data.cost}">현재 금액: ${count * data.cost}원</li>
                                <li class="list-group-item">프로젝트 시작일: ${data.startDate}</li>
                                <li class="list-group-item">프로젝트 종료일: ${data.endDate}</li>
                                <li class="list-group-item">프로젝트 남은 일수: ${daysBetween}일</li>
                                <li class="list-group-item" id="state">프로젝트 상태:</li>
                                <li class="list-group-item">프로젝트 카테고리: ${data.category}</li>
                                <li class="list-group-item" id="stateP"></li>
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
    const end = document.getElementById('end123');
    const state1 = document.getElementById('state123');

    const endCost = end.getAttribute('data-target-cost');
    const stateCost = state1.getAttribute('data-state-cost');
    if(Number(stateCost)<Number(endCost)){
    const data1 ="프로젝트 상태: 진행중";
    document.getElementById('state').innerText = data1;
    }
    else if(Number(stateCost)>=Number(endCost)){
        const data1 ="프로젝트 상태: 목표달성";
        document.getElementById('state').innerText = data1;
    }
    const data3 = Number(stateCost)/Number(endCost)*100;
    const data4 = "달성률: " + Math.floor(data3) + "%";
    document.getElementById('stateP').innerText = data4;
</script>
</body>
</html>