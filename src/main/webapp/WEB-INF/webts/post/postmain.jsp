<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<!-- 버튼들 (게시물 작성, 내 게시물 확인) -->
<div class="button-container" style="text-align: right; margin: 10px;">
    <button onclick="location.href='/post/create'">게시물 작성</button>
    <button onclick="location.href='/myposts'">내 게시물 확인</button>
</div>

<!-- 게시물이 없을 때 -->
<c:if test="${empty postlist}">
    <h1>등록된 게시물이 없습니다.</h1>
</c:if>

<!-- 게시물 목록 -->
<div style="margin: 20px;">
    <c:if test="${not empty postlist}">
        <c:forEach var="post" items="${postlist}">
            <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
                <h3>${post.title}</h3>
                <p>${post.summary}</p> 
                <button onclick="location.href='/post/detail/${post.id}'">상세보기</button>
            </div>
        </c:forEach>
    </c:if>
    
    
    <!-- 페이지네이션 -->
    <c:if test="${not empty page}">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <!-- 이전 페이지 버튼 -->
                <c:if test="${page.number > 0}">
                    <li class="page-item">
                        <a class="page-link" href="/post/main?page=${page.number - 1}" tabindex="-1">Previous</a>
                    </li>
                </c:if>

                <!-- 페이지 번호 -->
                <c:forEach begin="0" end="${page.totalPages - 1}" var="i">
                    <li class="page-item ${page.number == i ? 'active' : ''}">
                        <a class="page-link" href="/post/main?page=${i}">${i + 1}</a>
                    </li>
                </c:forEach>

                <!-- 다음 페이지 버튼 -->
                <c:if test="${page.number < page.totalPages - 1}">
                    <li class="page-item">
                        <a class="page-link" href="/post/main?page=${page.number + 1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
</div>

<%@ include file="../layout/footer.jsp" %>
