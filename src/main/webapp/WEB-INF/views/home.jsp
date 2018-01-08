<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/theme/css/style.css" />" rel="stylesheet">
    <title>Crud</title>
</head>
    <body>
    <%
        request.setAttribute("search", new String());
    %>
    <div>
        <form:form action="search" method="post" >
            <input type="text" name="search">
            <input type="submit" name="search" value="Поиск">
            <c:if test="${searchMessage!=null}">
                <span style="color: cadetblue"><c:out value="${searchMessage}"/></span>
            </c:if>
        </form:form>
    </div>
        <form action="/edit">
            <input type="button" class="button" onclick="location.href='/book?new'" value="Добавить книгу" />
            <input type="submit" class="button" value="Редактировать" />
            <input type="submit" class="button" name="delete" value="Удалить" />
            <c:if test="${message!=null}">
                <span style="color:cadetblue"><c:out value="${message}" /></span>
            </c:if>
            <c:choose>
                <c:when test="${bookList==null}">
                    <span style="color: red"><c:out value="По вашему запросу ничего не найдено"/></span>
                </c:when>
                <c:otherwise>
                    <table>
                        <tr>
                            <th>Выбрать</th>
                            <th>ID</th>
                            <th>Название</th>
                            <th>Комментарий</th>
                            <th>Автор</th>
                            <th>ISBN</th>
                            <th>Год издания</th>
                            <th>Прочитано</th>
                        </tr>
                        <c:forEach var="book" items="${bookList}">
                            <tr>
                                <td style="text-align: center"><input type="checkbox" name="id" value="${book.id}"></td>
                                <td><c:out value="${book.id}" /></td>
                                <td><c:out value="${book.title}" /></td>
                                <td><c:out value="${book.description}" /></td>
                                <td><c:out value="${book.author}" /></td>
                                <td><c:out value="${book.isbn}" /></td>
                                <td style="text-align: center"><c:out value="${book.printYear}" /></td>
                                <td style="text-align: center" class="td_style">
                                    <c:choose>
                                        <c:when test="${book.readAlready==0}">
                                            <button class="button" name="book" value="${book.id}">Не прочитано</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="button" disabled>Прочитано</button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </form>
        <c:if test="${search==false}">
            <c:choose>
                <c:when test="${prevPage}">
                    <a href="/?page=${thisPage - 1}">Пред. стр.</a>
                </c:when>
                <c:otherwise>
                    <c:out value="Пред. стр." />
                </c:otherwise>
            </c:choose>

            <c:out value=" ${thisPage} " />

            <c:choose>
                <c:when test="${nextPage}">
                    <a href="/?page=${thisPage + 1}">Сл.стр.</a>
                </c:when>
                <c:otherwise>
                    <c:out value="Сл.стр." />
                </c:otherwise>
            </c:choose>
        </c:if>
    </body>
</html>