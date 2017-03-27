<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Questions</title>
</head>
<body>
        <form action = "search.do" method = "POST">
        
		<c:forEach var="document" items="${questionsList}">
		<ul> 
		    <li> <input type = "hidden" id="questionid" value = ${document.id}><li>
			<li><a href = "javascript.document.submitForm.submit()">${document.Title}</a></li>
 
			<li>${document.questionBody}</li>
		</ul>	
		</c:forEach>
		</form>
</body>
</html>