<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

 <div class="button-container" style="text-align: right; margin: 10px;">
 	<button onclick="location.href='/post/create'">게시물 작성</button>
 	<button onclick="location.href='/myposts'">내 게시물 확인</button>
 </div>
 <!-- 포스트들 보이게 -->
<div style="margin: 20px;">
    <h2>게시물 목록</h2>
<%--     <% 
        // 예시 데이터, 실제로는 데이터베이스에서 가져와야 함
        List<String> posts = Arrays.asList("게시물 1", "게시물 2", "게시물 3");
        for (String post : posts) {
    %> --%>
        <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
            <h3>post의 제목</h3>
            <p>게시물의 간략한 내용이 여기에 들어갑니다.</p>
            <button onclick="location.href='/post/detail?id='">상세보기</button>
        </div>
</div>

<%@ include file="../layout/footer.jsp"%>