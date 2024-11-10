<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="../layout/header.jsp" %>    
<div class="container border py-3 mt-3">
	<c:if test="${empty postInfo}">
		<h1>등록된 게시물이 없습니다.</h1>
	</c:if>	
	
   <div>
      <h3>${postInfo.title}</h3>
   </div>
   <div>
      <p>${postInfo.content}</p>
   </div>
   <div>
      <p>포스트 번호 : <span id="id">${postInfo.id}</span></p>
      <p>작성자 : ${postInfo.user.username}</p>
   </div>
   <hr>
   <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
   <c:if test="${principal != null and postInfo.user.id == principal.id}">
   <a href="/post/update/${postInfo.id}" class="btn btn-warning">수정하기</a>
   <button id="btn-delete" class="btn btn-danger" onclick="location.href='/post/delete/${postInfo.id}'">삭제하기</button>
   </c:if>
</div>

   <br><br>
   <div class="container mt-3">
      <table class="table">
         <thead>
            <tr>
               <th><h4>댓글 목록</h4></th>
        <c:if test="${!empty postInfo.reply}">
	      <div class="container mt-3">
	         <table class="table">
	            <thead>
	               <tr>
	                  <th width="75%">내용</th>
	                  <th width="10%">작성자</th>
	                  <th width="15%">삭제</th>
	               </tr>
	            </thead>
	            <tbody>
	               <c:forEach items="${postInfo.reply}" var="reply">
	                  <tr>
	                     <td>${reply.content}</td>
	                     <td>${reply.user.username}</td>
	                     
	                     <td>

    <button class="btn btn-danger reply-delete" onclick="location.href='/reply/${postInfo.id}/${reply.id}'" data-id="${reply.id}">삭제</button>


	                     </td>
	                  </tr>
              		 </c:forEach>
		            </tbody>
		         </table>
		      </div>
		   </c:if>       
            </tr>
         </thead>
         <tbody>
            <tr align="right">
               <td>
		      <form method="POST" action="/reply/${postInfo.id}"> 
                  <textarea id="reply-content" rows="1" class="form-control" name="content"></textarea>
                  <button id="btn-save-reply" class="btn btn-secondary mt-3">댓글 등록</button>
				</form> 
               </td>
            </tr>
         </tbody>
      </table>
   </div>
<%@ include file="../layout/footer.jsp"%>