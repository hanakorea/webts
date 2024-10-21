<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<form method="POST" action="/login">	
 <div class="mb-3">
  <label for="email" class="form-label">email:</label>
  <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" >
 </div>
  <div class="mb-3">
    <label for="password" class="form-label">Password:</label>
    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" >
  </div>
  <button id="btn-login" type="submit" class="btn btn-secondary">로그인</button>
  
	<c:if test="${not empty passwordER}">
	    <div style="color: red;">${passwordER}</div>
	</c:if>
	<c:if test="${not empty emailER}">
	    <div style="color: red;">${emailER}</div>
	</c:if>
</form>
	

<%@ include file="../layout/footer.jsp" %>