<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<form>
	<h1>회원정보 수정</h1>	
	<div class="mb-3 mt-3">
       <label for="username" class="form-label">닉네임:</label>
       <input type="text" class="form-control" id="username" placeholder="Enter username" name="username"  value="${principal.username}">
     </div>
     <div class="mb-3">
       <label for="email" class="form-label">이메일:</label>
       <input type="email" class="form-control" id="email" name="email" value="${principal.email}" disabled>
     </div>
     <div class="mb-3">
       <label for="password" class="form-label">Password:</label>
       <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" >
     </div>
    <div class="mb-3">
       <label for="gender" class="form-label">성별:</label>
       <input type="text" class="form-control" id="gender" name="gender" value="${principal.gender}" readonly>
     </div>
   	<div class="mb-3">
      <label for="creatDate" class="form-label">creatDate:</label>
      <input type="text" class="form-control" id="creatDate" name="creatDate" value="${principal.creatTime}" disabled>
    </div>
    <button id="btn-update" type="submit" class="btn btn-secondary">회원정보수정</button>
    <button id="btn-delete" type="button" class="btn btn-danger">회원탈퇴</button>
</form>
<form>
<h1>기초대사량 계산</h1>
   <input type="hidden" id="gender_cal" name="gender" value="${principal.gender}">
   <div class="mb-3">
     <label for="weight" class="form-label">몸무게:</label>
     <input type="text" class="form-control" placeholder="enter weight" id="weight" name="weight">
   </div>
   <div class="mb-3">
     <label for="height" class="form-label">키:</label>
     <input type="text" class="form-control" placeholder="enter height" id="height" name="height">
   </div>
 <div class="mb-3">
     <label for="age" class="form-label">나이:</label>
     <input type="text" class="form-control" placeholder="enter age" id="age" name="age">
   </div>
   <div class="mb-3">
    <label class="form-label">활동량:</label>
    <input type="radio" name="move" value="less" > 적음
    <input type="radio" name="move" value="middle" > 보통
    <input type="radio" name="move" value="high" > 많음
    </div>
   <button id="btn-cal" type="submit" class="btn btn-secondary">계산</button>
</form>

<script src="js/user.js"></script>	
<%@ include file="../layout/footer.jsp"%>