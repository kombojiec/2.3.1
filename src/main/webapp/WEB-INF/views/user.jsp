<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>" />
    <title>Document</title>
</head>
<body>

    <h1 class="header">Users List</h1>

    <div class="table__wrapper">
        <table class="table">
            <tr class="table__raw">
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>operations</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <c:url var="update" value="/user/update">
                    <c:param name="userId" value="${user.id}" />
                </c:url>
                <c:url var="delete" value="/user/delete">
                    <c:param name="userId" value="${user.id}" />
                </c:url>
                <tr class="table__raw">
                    <td><c:out value="${user.id}"/></td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td class="table__links">
                        <a href="${update}" class="table__link table__link_update">Update</a>
                        <a href="${delete}" class="table__link table__link_delete">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <button type="button" class="table__button">
            <a href="/user/create" class="table__button-link">Add user</a>
        </button>
    </div>

</body>
</html>
