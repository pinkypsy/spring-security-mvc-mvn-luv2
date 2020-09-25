<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>
        Home Page
    </title>
</head>
<body>
<h1>Hello to Home Page</h1>
<hr>
<h3>This is a fancy Home Page - Yoohoo</h3>
<hr>
<p>
    User: <security:authentication property="principal.username"/>
    <br>
    Role(s): <security:authentication property="principal.authorities"/>
</p>
<hr>

<security:authorize access="hasRole('MANAGER')">
<p>
    <a href="${pageContext.request.contextPath}/leaders">Leaders Page</a>
</p>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
<p>
    <a href="${pageContext.request.contextPath}/systems">Admins Page</a>
</p>

</security:authorize>

<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout">
</form:form>

</body>

</html>