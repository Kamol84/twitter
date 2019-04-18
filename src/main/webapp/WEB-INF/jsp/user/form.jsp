<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>form</title>
</head>
<body>

    <a href="${pageContext.request.contextPath}/user/list/">Back to list</a>

        <form:form modelAttribute="user"
                   method="post"
                   action="${pageContext.request.contextPath}/user/form"
                    >
            <form:hidden path="id"/>
            <form:input path="firstName" placeholder="Firstname"/>
            <form:errors path="firstName"/>

            <form:input path="lastName" placeholder="Lastname"/>
            <form:errors path="lastName"/>

            <form:password path="password" placeholder="Password"/>
            <form:errors path="password"/>

            <form:input path="email" placeholder="email"/>
            <form:errors path="email"/>

            <input type="submit" value="save">

    </form:form>

</body>
</html>
