<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
	
<form>
	<div class="mb-3 mt-3">
       <label for="username" class="form-label">닉네임:</label>
       <input type="text" class="form-control" id="username" placeholder="Enter username" name="username"  autocomplete="username">
     </div>
     <div class="mb-3">
       <label for="password" class="form-label">Password:</label>
       <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" autocomplete="current-password">
     </div>
     <div class="mb-3">
       <label for="email" class="form-label">이메일:</label>
       <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" autocomplete="email">
     </div>
     <!-- 선택해서 정보 보내기 -->
 	 <div class="mb-3">
       <label class="form-label">성별:</label>
       <input type="radio" name="gender" value="man" > 남자
       <input type="radio" name="gender" value="woman" > 여자
     </div>
     <button id="btn-save" type="submit" class="btn btn-secondary">회원가입</button>
</form>
	
<script src="js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>