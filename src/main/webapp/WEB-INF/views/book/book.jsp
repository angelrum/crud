<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vladimir
  Date: 05.01.2018
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Добавить новую книгу</title>
    <link href="<c:url value="/theme/css/style.css" />" rel="stylesheet">
</head>
<body>
    <div>
        <h2>Парметры добавленной книги</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Название</th>
                <th>Комментарий</th>
                <th>Автор</th>
                <th>ISBN</th>
                <th>Год издания</th>
            </tr>
            <tr>
                <td><c:out value="${book.id}" /></td>
                <td><c:out value="${book.title}" /></td>
                <td><c:out value="${book.description}" /></td>
                <td><c:out value="${book.author}" /></td>
                <td><c:out value="${book.isbn}" /></td>
                <td><c:out value="${book.printYear}" /></td>
            </tr>
        </table>
        <input type="button" class="button" value="На главную" onclick="location.href='/home'" />
    </div>
</body>
</html>
