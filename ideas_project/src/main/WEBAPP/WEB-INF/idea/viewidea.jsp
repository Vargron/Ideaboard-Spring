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
<h4>Hi ${user.alias}</h4>
<a href="/idea/">back to dashboard</a>
<a href="/logout/">don't forget to logout</a>
<h1> <a href="/user/${idea.author.id }/view/">${ idea.author.first_name}</a> says:</h1>
<p>${idea.text }</p>

<p>

<h3>users who like the idea</h3>
    <table>
   		<tr>
   		<th>alias</th>
   		<th>full name<th>


		
   		</tr> 
    
			<c:forEach var="u" varStatus="loop" items="${idea.likers}">	
				<tr>
					<td><a href="/user/${u.id}/view/">${u.alias}<a></a></td>
					<td> ${u.first_name } ${u.last_name }</td>
						
					
				</tr>
			</c:forEach>
    
    </table>


</body>
</html>