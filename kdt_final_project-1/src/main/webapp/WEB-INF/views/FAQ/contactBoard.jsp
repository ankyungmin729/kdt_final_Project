<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FAQ 게시판</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>FAQ 게시판</h1>
    <table>
        <thead>
            <tr>
                <th>글 번호</th>
                <th>제목</th>
                <th>내용</th>
                <th>분류</th>
                <th>이메일</th>
                <th>전화번호</th>
                <th>첨부 파일</th>
            </tr>
        </thead>
        <tbody>
            <%-- 게시물 데이터를 가져와서 표시하는 부분 --%>
            <%-- 여기서는 데이터베이스에서 게시물 데이터를 가져오는 메소드를 호출하여 리스트로 받아온다고 가정합니다. --%>
            <%-- 실제로는 데이터베이스 연결과 쿼리 작성이 필요합니다. --%>
            <%-- 아래 예시에서는 임시로 1부터 10까지의 게시물을 표시하도록 하였습니다. --%>
            <% for (int i = 1; i <= 10; i++) { %>
                <tr>
                    <td><%= i %></td>
                    <td><c:out value="${articles[i-1].title}" /></td>
                    <td><c:out value="${articles[i-1].content}" /></td>
                    <td><c:out value="${articles[i-1].categories}" /></td>
                    <td><c:out value="${articles[i-1].email}" /></td>
                    <td><c:out value="${articles[i-1].phone}" /></td>
                    <td><c:out value="${articles[i-1].imageFileName}" /></td>
                </tr>
            <% } %>
        </tbody>
    </table>

    <%-- 페이징 처리 부분 --%>
    <%-- 실제로는 데이터베이스에서 총 게시물 수와 페이지 정보를 조회하여 사용해야 합니다. --%>
    <%-- 여기서는 임시로 총 게시물 수와 현재 페이지 정보를 설정하였습니다. --%>
    <% int totalArticles = 50; // 전체 게시물 수 %>
    <% int currentPage = 1; // 현재 페이지 번호 %>
    <% int articlesPerPage = 10; // 페이지당 게시물 수 %>
    <% int totalPages = (int) Math.ceil((double) totalArticles / articlesPerPage); %>

    <%-- 페이징 버튼 부분 --%>
    <div class="paging">
        <%-- 이전 페이지 버튼 --%>
        <% if (currentPage > 1) { %>
            <a href="?page=<%= currentPage - 1 %>">&lt;</a>
        <% } else { %>
            <span class="disabled">&lt;</span>
        <% } %>

        <%-- 페이지 번호 버튼 --%>
        <% for (int pageNum = 1; pageNum <= totalPages; pageNum++) { %>
            <% if (pageNum == currentPage) { %>
                <span class="current"><%= pageNum %></span>
            <% } else { %>
                <a href="?page=<%= pageNum %>"><%= pageNum %></a>
            <% } %>
        <% } %>

        <%-- 다음 페이지 버튼 --%>
        <% if (currentPage < totalPages) { %>
            <a href="?page=<%= currentPage + 1 %>">&gt;</a>
        <% } else { %>
            <span class="disabled">&gt;</span>
        <% } %>
    </div>
</body>
</html>
