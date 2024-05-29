<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>회원가입</title>

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

  <style>
    body {
      min-height: 100vh;

      background: -webkit-gradient(linear, left bottom, right top, from(#92b5db), to(#1d466c));
      background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
    }

    .input-form {
      max-width: 680px;

      margin-top: 80px;
      padding: 32px;

      background: #fff;
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
  <div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-3">회원가입</h4>
        <form class="validation-form" action="/submitForm" method="post">
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="ID">아이디</label>
              <input type="text" class="form-control" name = "userId" id="userId" placeholder="id" value="${userForm.userId}" required>
              <div class="invalid-feedback">
                아이디를 입력해주세요.
              </div>
            </div>
            <div class="col-md-6 mb-3">
              <label for="PW">비밀번호</label>
              <input type="text" class="form-control" id="PW" name="userPw" placeholder="password" value="${userForm.userPw}" required>
              <div class="invalid-feedback">
                비밀번호를 입력해주세요.
              </div>
            </div>
          </div>
          <div>
              <p id="ms" style="color: red;">${idErrorMessage}</p> 
          </div>
          <div class="mb-3">
            <label for="NAME">이름</label>
            <input type="text" class="form-control" id="NAME" name="userName" placeholder="홍길동" value="${userForm.userName}">
            <div class="invalid-feedback">
              이름을 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="EMAIL">이메일</label>
            <input type="email" class="form-control" id="EMAIL" name="email" placeholder="you@naver.com" value="${userForm.email}" required>
            <div class="invalid-feedback">
              이메일을 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="ADDRESS">주소</label>
            <input type="text" class="form-control" id="ADDRESS"name="address" placeholder="부산광역시 남구" value="${userForm.address}" required>
            <div class="invalid-feedback">
              주소를 입력해주세요.
            </div>
          </div>

        
          <div class="mb-3">
            <label for="PHONE_NUMBER">휴대폰 번호</label>
            <input type="text" class="form-control" id="PHONE_NUMBER" name="phoneNumber"placeholder="휴대폰 번호" value="${userForm.phoneNumber}" required>
            <div class="invalid-feedback">
              휴대폰 번호를 입력해주세요.
            </div>
          </div>

          <div class="row">
            <div class="col-md-5 mb-3">
              <label for="BIRTH">생년월일</label>
              <input type="text" class="form-control" id="BIRTH" name="birth" placeholder="생년월일" value="${userForm.birth}" required>
              <div class="invalid-feedback">
                생년월일을 입력해주세요.
              </div>
            </div>

            <div class="col-md-7 mb-3">
              <label for="ACCOUNT">계좌 번호</label>
              <input type="text" class="form-control" id="ACCOUNT" name="account" placeholder="계좌 번호" value="${userForm.account}" required>
              <div class="invalid-feedback">
                계좌 번호를 입력해주세요.
              </div>
            </div>
            <div class="col-md-12 mb-3">
              <label for="ADMIN">가입 유형</label>
              <select class="custom-select d-block w-100" id="ADMIN" name="admin">
                <option value="후원자">후원자</option>
                <option value="제작자">제작자</option>
              </select>
            </div>
          </div>
          <hr class="mb-4">
          <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="aggrement" required>
            <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
          </div>
          <div class="mb-4"></div>
          <button class="btn btn-primary btn-lg btn-block" type="submit" onclick="ck">가입 완료</button>
        </form>
      </div>
    </div>
    <footer class="my-3 text-center text-small">
      <p class="mb-1">&copy; 2024 부울경</p>
    </footer>
  </div>
  <script>
    window.addEventListener('load', () => {
      const forms = document.getElementsByClassName('validation-form');

      Array.prototype.filter.call(forms, (form) => {
        form.addEventListener('submit', function (event) {
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add('was-validated');
        }, false);
      });
    }, false);

    
  </script>
</body>
</html>