<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>프로젝트 생성</title>

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
        <h4 class="mb-3">프로젝트 생성</h4>
        <form class="validation-form" novalidate action="/">
          <div class="row">

            <div class="col-md-3 mb-3">
              <label for="CATEGORY">카테고리</label>
              <select class="custom-select d-block w-100" id="CATEGORY">
                <option>전자제품</option>
                <option>푸드</option>
                <option>굿즈</option>

              </select>
            </div>
            
            <div class="col-md-9 mb-3">
              <label for="TITLE">프로젝트 명</label>
              <input type="text" class="form-control" id="TITLE" placeholder="프로젝트 명" value="" required>
              <div class="invalid-feedback">
                프로젝트명을 입력해주세요.
              </div>
            </div>

            <div class="col-md-12 mb-3">
              <label for="DESCRIPTION">상세 정보</label>
              <textarea class="form-control" id="DESCRIPTION"  required rows="10" cols="20" style="resize: none;"></textarea>
              <div class="invalid-feedback">
                상세정보를 입력해주세요.
              </div>
            </div>
          </div>

         

          <div class="mb-3">
            <label for="TARGET_COST">목표 금액</label>
            <input type="text" class="form-control" id="TARGET_COST" placeholder="목표 금액" required>
            <div class="invalid-feedback">
              목표 금액을 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="END_DATE">종료일자</label>
            <input type="date" class="form-control" id="END_DATE"  required>
            <div class="invalid-feedback">
              종료일자를 선택해주세요.
            </div>
          </div>

          <div class="mb-12">
            <label for="image">이미지 파일</label>
            <input type="file" class="form-control" id="image" accept="image/*" required/>
            <span class="text-muted"> *이미지 파일만 업로드 가능합니다.</span>
          </div>



          <div class="mb-4"></div>
          <button class="btn btn-primary btn-lg btn-block" type="submit">등록 완료</button>
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