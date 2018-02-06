<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
<h1>Login</h1>



<form action="/login/" method="post">
<label>email</label>
<input type="text" name="email" >
<label>password</label>
<input type="password" name="password" >
<input type="submit" value="login!">
</form>



<h1>Register</h1>
<form:form modelAttribute="user" action="/register/" method="post">
<form:label path="first_name"> First Name:
<form:errors path="first_name"/>
<form:input path="first_name" type="text" name="first_name" />
</form:label>
<form:label path="last_name"> Last Name:
<form:errors path="last_name"/>
<form:input path="last_name" type="text" name="last_name" />
</form:label>
<label>alias:</label>
<input type="text" name="alias" >
<form:label path="email"> email:
<form:errors path="email"/>
<form:input path="email" type="text" name="email" />
</form:label>
<label>Password:</label>
<input type="password" name="pword" >
<label>Password confirm:</label>
<input type="password" name="pwordc" >
<input type="submit" value="register">

</form:form>

<c:forEach var="err" varStatus="loop" items="${errors}">	
	<p><c:out value="${err}"/></p>

</c:forEach>





</div>

</body>
</html>