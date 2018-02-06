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
<h1>Hi ${user.alias}</h1>
<a href="/logout/">don't forget to logout</a>
<div>




</div>
<h1>what's your idea</h1>
<form action="/idea/new/" method="post">
<label>enter your idea</label>
<input type="textbox" name="ideatext">
<input type="hidden" value='${user.id}' name="user_id">
<input type="submit" value="Post!">
</form>


<h3>all ideas</h3>
    <table>
   		<tr>
   		<th>author</th>
   		<th>idea<th>

   		
   		<th>like</th>
   		<th> total likes</th>
   		<th>delete</th>
		
   		</tr> 
    
			<c:forEach var="i" varStatus="loop" items="${ideas}">	
				<tr>
					<td><a href="/user/${i.author.id }/view/">${i.author.alias}<a> Says:</a></td>
					<td><c:out value="${i.text}"/></td>

					
					
					<td><a href="/idea/${i.id }/like/">like?</a></td>
					<c:choose>
					<c:when test="${i.likers.size()==1}">
					<td><a href="/idea/<c:out value="${i.id}"/>/view/">${i.likers.size() } person likes this!</a><td>
					</c:when>
					<c:otherwise>
					<td><a href="/idea/<c:out value="${i.id}"/>/view/">${i.likers.size() } people like this!</a><td>
					</c:otherwise>					
					</c:choose>

					
					<td>
					<c:choose>
				    <c:when test="${user.id==i.author.id}">
				       <a href="/idea/${i.id }/delete/" >delete</a>
				    </c:when>
				    <c:otherwise>
				        delete not availible
				    </c:otherwise>
					</c:choose>
					
					
					</td>
					
				</tr>
			</c:forEach>
    
    </table>

</body>
</html>