<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vladimir
  Date: 05.01.2018
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/theme/css/style.css" />" rel="stylesheet">
    <title>Crud</title>
</head>
<body>
<div>
    <h2>Create book</h2>
    <form:form id="new-book" modelAttribute="entity" method="post" >
        <table>
            <tr>
                <th>Название</th>
                <th>Комментарий</th>
                <th>Автор</th>
                <th>ISBN</th>
                <th>Год издания</th>
            </tr>
            <tr>
                <td>
                    <form:input path="title" id="title" maxlength="100"/>
                    <form:errors path="title" cssClass="error" />
                </td>
                <td>
                    <form:input path="description" id="description" maxlength="255"/>
                    <form:errors path="description" cssClass="error" />
                </td>
                <td>
                    <form:input path="author" id="author" maxlength="100" />
                    <form:errors path="author" cssClass="error" />
                </td>
                <td>
                    <form:input path="isbn" id="isbn" maxlength="20" />
                    <form:errors path="isbn" cssClass="error" />
                </td>
                <td>
                    <form:input path="printYear" id="printYear" maxlength="4" />
                    <form:errors path="printYear" cssClass="error" />
                </td>
            </tr>
        </table>
        <input type="submit" name="save" value="Сохранить">
        <input type="button" name="cancel" value="Отменить" onclick="location.href='/book?cancel'">
    </form:form>
</div>
</body>
</html>
