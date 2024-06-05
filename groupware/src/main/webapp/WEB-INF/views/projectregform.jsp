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
 
    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
      -webkit-appearance: none;
      margin: 0;
    }


    input[type='number'] {
      -moz-appearance: textfield;
    }
  </style>
</head>

<body>
  <div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h4 class="mb-3">프로젝트 생성</h4>
        <form class="validation-form" novalidate action="/regRequest" method="post" enctype="multipart/form-data">
          <div class="row">
            <div class="col-md-3 mb-3">
              <label for="CATEGORY">카테고리</label>
              <select class="custom-select d-block w-100" id="CATEGORY" name="category">
                <option>전자제품</option>
                <option>푸드</option>
                <option>굿즈</option>

              </select>
            </div>
            
            <div class="col-md-9 mb-3">
              <label for="TITLE">프로젝트 명</label>
              <input type="text" class="form-control" id="TITLE" placeholder="프로젝트 명" name="title" required>
              <div class="invalid-feedback">
                프로젝트명을 입력해주세요.
              </div>
            </div>
            <div class="col-md-12 mb-3">
              <label for="DESCRIPTION">상세 정보</label>
              <textarea class="form-control" id="DESCRIPTION"  required rows="10" cols="20" style="resize: none;" name="description"></textarea>
              <div class="invalid-feedback">
                상세정보를 입력해주세요.
              </div>
            </div>
          </div>

          <div class="mb-3">
            <label for="TARGET_COST">목표 금액</label>
            <input type="number" step="100" class="form-control" id="TARGET_COST" placeholder="목표 금액" name="targetCost" required>
            <div class="invalid-feedback">
              목표 금액을 백 단위로 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="TARGET_COST">후원 금액 단위</label>
            <input type="number" step="100" class="form-control" id="TARGET_COST" placeholder="후원 금액" name="cost" required>
            <div class="invalid-feedback">
              금액을 백 단위로 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="END_DATE">종료일자</label>
            <input type="date" class="form-control" id="END_DATE"  name="strEndDate"required >
            <div class="invalid-feedback">
              종료일자를 선택해주세요.
            </div>
          </div>

          <div class="mb-12">
            <label for="image">이미지 파일</label>
            <input type="file" class="form-control" id="image" name="imageFile" accept="image/*" required/>
            <span class="text-muted"> *이미지 파일만 업로드 가능합니다.</span>
          </div>
          <div class="mb-4"></div>
          <button class="btn btn-primary btn-lg btn-block" type="submit" id="data11">등록완료</button>
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
          if(parseInt(form.cost.value) > parseInt(form.targetCost.value)){
            alert("후원 금액이 목표 금액보다 클 수 없습니다.");
            event.preventDefault();
            event.stopPropagation();
          }
          if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }
    
          form.classList.add('was-validated');
        }, false);
      });
    }, false);
    const data10= document.getElementById("TITLE").value;
    const data11= document.getElementById("data11").innerText;
    console.log(data10);
    console.log(data11);
    if(data10 !=""){
      document.getElementById("data11").innerText = "수정완료";
    }
    

  </script>
</body>
</html>