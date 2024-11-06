<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

 <div class="button-container" style="text-align: right; margin: 10px;">
 	<button onclick="location.href='/post/create'">게시물 작성</button>
 	<button onclick="location.href='/myposts'">내 게시물 확인</button>
 </div>
 
<div style="margin: 20px;">
   <c:if test="${not empty postlist}">
    <c:forEach var="post" items="${postlist}">
        <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
            <h3>${post.title}</h3>
            <p>${post.summary}</p> 
            <button onclick="location.href='/post/detail?id=${post.id}'">상세보기</button>
        </div>
    </c:forEach>
    </c:if>
	<c:if test="${empty postlist}">
	    <h1>등록된 게시물이 없습니다.</h1>
	</c:if>
</div>


<%@ include file="../layout/footer.jsp"%>