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
        .container {
            padding: 32px;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            border-radius: 10px;
            /* -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
            -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
            box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15) */
        }

        
  </style>
</head>
<body>
    <div class="container">
        <!-- 회원 정보 수정 -->
        <form class="validation-form" action="/updateUser" method="post" onsubmit="return confirm('회원정보를 수정하시겠습니까?')">
        <!-- 메인페이지 -->
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button class="btn btn-primary btn-lg btn-block " type="button" onclick="location.href='returnMain'">메인으로</button>
        </div>
            <h2>유저 페이지</h2><hr>
                <div class="row">
                    <!-- id -->
                    <div class="col-md-6 mb-3">
                        <label for="ID">아이디</label>
                        <input type="text" class="form-control" name = "userId" id="userId" value="${loginUser.userId}" readonly>
                    </div>
                    <!-- pw -->
                    <div class="col-md-6 mb-3">
                        <label for="PW">비밀번호</label>
                        <input type="password" class="form-control" id="PW" name="userPw" placeholder="password" value="${userForm.userPw}" required readonly>
                    </div>
                </div>
                <div class="row">
                    <!-- 이름 -->
                    <div class="mb-3">
                        <label for="NAME">이름</label>
                        <input type="text" class="form-control" id="NAME" name="userName" value="${loginUser.userName}" readonly>
                    </div>
                    <!-- 이메일 -->
                    <div class="mb-3">
                        <label for="EMAIL">이메일</label>
                        <input type="email" class="form-control" id="EMAIL" name="email" value="${loginUser.email}" readonly>
                    </div>
                    <!-- 주소 -->
                    <div class="mb-3">
                        <label for="ADDRESS">주소</label>
                        <input type="text" class="form-control" id="ADDRESS"name="address" value="${loginUser.address}" readonly>
                    </div>
                    <!-- 전화번호 -->
                    <div class="mb-3">
                        <label for="PHONE_NUMBER">휴대폰 번호</label>
                        <input type="text" class="form-control" id="PHONE_NUMBER" name="phoneNumber" value="${loginUser.phoneNumber}" readonly>
                    </div>
                </div>
                <div class="row">
                    <!-- 생년월일 -->
                    <div class="col-md-5 mb-3">
                    <label for="BIRTH">생년월일</label>
                    <input type="date" class="form-control" id="BIRTH" name="birth" value="${loginUser.birth}" readonly>
                    </div>
                    <!-- 계좌번호 -->
                    <div class="col-md-7 mb-3">
                    <label for="ACCOUNT">계좌 번호</label>
                    <input type="text" class="form-control" id="ACCOUNT" name="account" value="${loginUser.account}" readonly>
                    </div>
                    <!-- 가입 유형 -->
                    <div class="col-md-12 mb-3">
                    <label for="ADMIN">가입 유형</label>
                    <select class="custom-select d-block w-100" id="ADMIN" name="admin">
                        <option value="후원자" 
                            <c:if test="${loginUser.admin == '후원자'}">selected</c:if>>후원자</option>
                        <option value="제작자" 
                            <c:if test="${loginUser.admin == '제작자'}">selected</c:if>>제작자</option>
                    </select>
                    </div>
                </div>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <c:choose>
                        <c:when test="${verify == null}">
                            <button class="btn btn-primary btn-lg btn-block " type="button" onclick="pwcheck()">회원 정보 수정</button>
                        </c:when>
                        <c:when test="${verify != null}">
                            <!-- 수정 버튼 누르면 readonly 해제 -->
                            <c:if test="${verify != null}">
                                <script>
                                    var items = document.getElementsByClassName("form-control");
                                    for(var i=0; i< items.length; i++){
                                        items[i].readOnly = false;
                                    }
                                </script>
                            </c:if>
                            <button class="btn btn-primary btn-lg btn-block " type="submit">회원 정보 수정</button>
                            <button class="btn btn-secondary btn-lg btn-block " type="reset">수정 사항 초기화</button>
                            <button class="btn btn-danger btn-lg btn-block " type="button" onclick="deleteUser()">회원 정보 삭제</button>
                        </c:when>
                    </c:choose>
                </div>
            </form>
        </div>
    </div>
    <!-- 위시리스트 --> 
    <div class="container">
        <div style="margin-top: 32px;">
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button class="btn btn-secondary btn-lg btn-block " type="button" onclick="location.href='/'">내 작품보기</button>
        </div>
            <h2 style="margin-bottom: 32px;">위시리스트</h2><hr>
        </div>
            <div class="col-md-3 mb-3 d-md-flex">
                <c:forEach var="project" items="${projectLists}">
                    <div class="card">
                        <div><h2>${project.title}</h2></div>
                        <img src="/images/projectThumbnails/${project.image}">
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
<!-- 삭제 시 확인 메시지 -->
<script>
    function deleteUser(){
        if(confirm("삭제하시겠습니까?")){
            return location.href='/deleteUser';
        }
    } 

    //팝업 창 띄우기
    function pwcheck() {
        // 현재 창의 너비와 높이를 가져옴
        var windowWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
        var windowHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;

        // 팝업 창의 위치를 계산
        var left = (windowWidth - 250) / 2 + window.screenLeft || window.screenX;
        var top = (windowHeight - 100) / 2 + window.screenTop || window.screenY;
        window.open("/verifyUser","__blank", 'width=' + 250 + ', height=' + 100 + ', left=' + left + ', top=' + top);
    }
</script>
<!-- 로그인 전에는 사용할 수 없도록 메인으로 리다이렉트 -->
<c:if test="${loginUser == null}">
    <c:redirect url="/"></c:redirect>
</c:if>
</html>