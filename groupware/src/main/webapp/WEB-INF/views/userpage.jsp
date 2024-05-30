<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
      <style>
        form {
        padding: 32px;
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;
        -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
        -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
        box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
        }
  </style>
</head>
<body>
    <h2>유저 페이지</h2>
    <!-- 회원 정보 수정 -->
    <form class="validation-form" action="/submitForm" method="post">
        <div class="row">
            <!-- id -->
            <div class="col-md-6 mb-3">
                <label for="ID">아이디</label>
                <input type="text" class="form-control" name = "userId" id="userId" placeholder="id" value="${userForm.userId}" readonly>
            </div>
            <!-- pw -->
            <div class="col-md-6 mb-3">
                <label for="PW">비밀번호</label>
                <input type="password" class="form-control" id="PW" name="userPw" placeholder="password" value="${userForm.userPw}">
            </div>
        </div>
        <div class="row">
            <!-- 이름 -->
            <div class="mb-3">
                <label for="NAME">이름</label>
                <input type="text" class="form-control" id="NAME" name="userName" placeholder="홍길동" value="${userForm.userName}">
            </div>
            <!-- 이메일 -->
            <div class="mb-3">
                <label for="EMAIL">이메일</label>
                <input type="email" class="form-control" id="EMAIL" name="email" placeholder="you@naver.com" value="${userForm.email}">
            </div>
            <!-- 주소 -->
            <div class="mb-3">
                <label for="ADDRESS">주소</label>
                <input type="text" class="form-control" id="ADDRESS"name="address" placeholder="부산광역시 남구" value="${userForm.address}">
            </div>
            <!-- 전화번호 -->
            <div class="mb-3">
                <label for="PHONE_NUMBER">휴대폰 번호</label>
                <input type="text" class="form-control" id="PHONE_NUMBER" name="phoneNumber"placeholder="휴대폰 번호" value="${userForm.phoneNumber}">
            </div>
        </div>
        <div class="row">
            <!-- 생년월일 -->
            <div class="col-md-5 mb-3">
            <label for="BIRTH">생년월일</label>
            <input type="date" class="form-control" id="BIRTH" name="birth" placeholder="생년월일" value="${userForm.birth}">
            </div>
            <!-- 계좌번호 -->
            <div class="col-md-7 mb-3">
            <label for="ACCOUNT">계좌 번호</label>
            <input type="text" class="form-control" id="ACCOUNT" name="account" placeholder="계좌 번호" value="${userForm.account}">
            </div>
            <!-- 가입 유형 -->
            <div class="col-md-12 mb-3">
            <label for="ADMIN">가입 유형</label>
            <select class="custom-select d-block w-100" id="ADMIN" name="admin">
                <option value="후원자">후원자</option>
                <option value="제작자">제작자</option>
            </select>
            </div>
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">회원 정보 수정</button>
    </form>
    <!-- 결제 내역 -->
    <div></div>
</body>
</html>