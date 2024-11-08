<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>


<div style="margin: 20px;">
   <c:if test="${not empty mylists}">
    <c:forEach var="post" items="${mylists}">
        <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
            <h3>${post.title}</h3>
            <p>${post.summary}</p> 
            <button onclick="location.href='/post/detail?id=${post.id}'">상세보기</button>
        </div>
    </c:forEach>
    </c:if>
	<c:if test="${empty mylists}">
	    <h1>등록된 게시물이 없습니다.</h1>
	</c:if>
</div>

<%@ include file="../layout/footer.jsp" %>