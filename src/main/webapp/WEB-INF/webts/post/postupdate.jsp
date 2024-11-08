<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="container mt-3">
   <form method="POST" action="/post/update/${postInfo.id }">
     <div class="mb-3 mt-3">
       <label for="title" class="form-label" >Title:</label>
       <input type="text" class="form-control" id="title" placeholder="Enter title" name="title" value="${postInfo.title}">
     </div>
     <div class="mb-3">
       <label for="content" class="form-label">Content:</label>
       <textarea class="form-control" id="content" rows="5" name="content">${postInfo.content}</textarea>
     </div>
     <button id="btn-update" type="submit" class="btn btn-primary">게시물 수정</button>
   </form>
</div>
<script src="/js/post.js"></script>
<%@ include file="../layout/footer.jsp" %>