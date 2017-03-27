<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Questions</title>
</head>
<body bgcolor="orange">
<img alt="picture1" src="img/stackoverflow.png">
 <div style="text-align:right;"><button type="button" 
        onclick="window.open('', '_self', ''); window.close();">Quit Application</button></div>
<form action = "search.do" name ="search" method = "POST" onsubmit="return searchValidation();">

<div style="text-align:center;">
<input type="text" id="searchTerm" name="searchTerm" style="width: 400px;height:15px" />
<input type="submit" value="Search" name = "searchButton" style="height:50px; width:50px">
<input type="submit" value="Clear" name = "clearButton" style="height:50px; width:50px">
</div>
<span>${noquestions}</span>
</form>
<br><br/>
<!-- Questions Display -->
<form action = "search.do" method = "POST">
<div id="questionsList" style="MARGIN-LEFT: 98px;MARGIN-RIGHT: 139px;">
<c:forEach var="document" items="${questionList}">
<div>
<input type = "hidden" id="questionid" value = ${document.id}>
<b>
<a href = "answers.do?questionid=${document.id}">${document.title}</a>
</b><br/>
${document.questionBody}<br/><br/>	
</div>
</c:forEach>
</div>
</form>

</body>
</html>