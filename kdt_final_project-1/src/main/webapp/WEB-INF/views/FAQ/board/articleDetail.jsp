<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세보기</title>
</head>
<body>
<h1 style="text-align: center;">게시글 상세보기</h1>
<table border="1" align="center">
    <tr>
        <th>제목</th>
        <th>내용</th>
        <th>카테고리</th>
        <th>이메일</th>
        <th>전화번호</th>
        <th>이미지 파일</th>
    </tr>
    <tr>
        <td>${article.title}</td>
        <td>${article.content}</td>
        <td>${article.categories}</td>
        <td>${article.email}</td>
        <td>${article.phone}</td>
        <td>${article.imgFileName}</td>
    </tr>
</table>
</body>
</html>
