<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="stackpackage.QABean"
         import = "org.jsoup.Jsoup"
         import = "org.bson.Document"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Questions</title>
</head>
<body>
		<c:forEach var="document" items="${questionsList}">
		<ul>
			<li><a href = "">${document.Title}</a></li>
			<% String s1=((Document)(pageContext.getAttribute("document"))).getString("Body");
	          String target= Jsoup.parse(s1).text(); %> 
			<li>${document.questionBody}</li>
		</ul>	
		</c:forEach>
</body>
</html>