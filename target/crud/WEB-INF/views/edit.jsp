<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vladimir
  Date: 28.12.2017
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title>Edit</title>
    <link href="<c:url value="/theme/css/style.css" />" rel="stylesheet">
</head>
    <body>
    <%
        request.setCharacterEncoding("UTF-8");
    %>
        <div>
            <form:form method="post" action="save" modelAttribute="editList">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Название</th>
                        <th>Комментарий</th>
                        <th>Автор</th>
                        <th>ISBN</th>
                        <th>Год издания</th>
                        <th>Прочитано</th>
                    </tr>
                    <c:forEach items="${editList.bookList}" varStatus="i">
                        <tr>
                            <td><form:input path="bookList[${i.index}].id" readonly="true"/></td>
                            <td><form:input path="bookList[${i.index}].title" /></td>
                            <td><form:input path="bookList[${i.index}].description" /></td>
                            <td><form:input path="bookList[${i.index}].author" readonly="true" /></td>
                            <td><form:input path="bookList[${i.index}].isbn" /></td>
                            <td><form:input path="bookList[${i.index}].printYear" /></td>
                            <td><form:input path="bookList[${i.index}].readAlready" readonly="true" /></td>
                        </tr>
                    </c:forEach>
                </table>
                <input type="submit" value="Сохранить" name="save">
                <input type="submit" value="Отменить" name="cancel">
            </form:form>
        </div>
    </body>
</html>
