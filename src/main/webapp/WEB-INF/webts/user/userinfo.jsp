<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<form id="update-form">
    <h1>회원정보 수정</h1>    
    <div class="mb-3 mt-3">
        <label for="username" class="form-label">닉네임:</label>
        <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" value="${principal.username}" autocomplete="username">
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">이메일:</label>
        <input type="email" class="form-control" id="email" name="email" value="${principal.email}" disabled autocomplete="email">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password:</label>
        <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" autocomplete="new-password">
    </div>
    <div class="mb-3">
        <label for="gender" class="form-label">성별:</label>
        <input type="text" class="form-control" id="gender" name="gender" value="${principal.gender}" readonly autocomplete="gender">
    </div>
    <div class="mb-3">
        <label for="creatDate" class="form-label">등록일:</label>
        <input type="text" class="form-control" id="creatDate" name="creatDate" value="${principal.creatTime}" disabled autocomplete="off">
    </div>
    <button id="btn-update" type="submit" class="btn btn-secondary">회원정보 수정</button>
    <button id="btn-delete" type="button" class="btn btn-danger">회원탈퇴</button>
</form>

<form id="cal-form">
    <h1>기초대사량 계산</h1>
    <input type="hidden" id="gender_cal" name="gender" value="${principal.gender}">
    <div class="mb-3">
        <label for="weight" class="form-label">몸무게:</label>
        <input type="text" class="form-control" placeholder="Enter weight" id="weight" name="weight" autocomplete="weight">
    </div>
    <div class="mb-3">
        <label for="height" class="form-label">키:</label>
        <input type="text" class="form-control" placeholder="Enter height" id="height" name="height" autocomplete="height">
    </div>
    <div class="mb-3">
        <label for="age" class="form-label">나이:</label>
        <input type="text" class="form-control" placeholder="Enter age" id="age" name="age" autocomplete="age">
    </div>
    <div class="mb-3">
        <label class="form-label">활동량:</label>
        <input type="radio" name="active" value="less"> 적음
        <input type="radio" name="active" value="middle"> 보통
        <input type="radio" name="active" value="high"> 많음
    </div>
    <button id="btn-cal" type="submit" class="btn btn-secondary">계산</button>
</form>

<script src="js/user.js"></script>    
<%@ include file="../layout/footer.jsp"%>
