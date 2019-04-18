<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
</head>
<body>
<h2>Users</h2>
<a href="${pageContext.request.contextPath}/user/form/">Add user</a>

<table border="0.5px">
    <thead>
    <tr>
        <th>Fullname</th>
        <th>mail</th>
        <th>Operations</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.fullName}</td>
                <td>${user.email}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/user/form/${user.id}">edit</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
