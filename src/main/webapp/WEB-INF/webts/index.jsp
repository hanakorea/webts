<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>
	<h1>${principal.username}</h1>
	<h2>${principal}</h2>
	<c:if test="${not empty logoutMsg}">
	 <div class="alert alert-success">${logoutMsg}</div>
	</c:if>
	<!-- 아침, 점심, 저녁 먹은 칼로리 -->
<%@ include file="layout/footer.jsp"%>