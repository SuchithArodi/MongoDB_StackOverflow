<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
h2 {
    color: red;
    font-family: verdana;
    font-size: 150%;
}
p  {
    color: black;
    font-family: courier;
    font-size: 100%;
    font-weight: bold;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Answers</title>
</head>

 <div style="text-align:right;"><button type="button" 
        onclick="window.open('', '_self', ''); window.close();">Quit Application</button></div>
<body bgcolor="orange">

        
        <form action = "comment.do" method = "POST">
        <h2>${answers.title}</h2><br/>
        <p>${answers.fullQuestionBody}</p><br/>
		<div style="text-align:center;"><img  src="${pageContext.request.contextPath}/${answers.imageLink}" ></div>
		<br>
		  <div id ="answersList" style = "margin-left: 15px;margin-right: 33px;">
		  <c:forEach var="document" items="${answers.answerBody}">
		  
		  <div id= "answers" style="color:#000000; background: beige;" >
		  ${document}
		</div><br/>
		</c:forEach>
		</div>
		<div style="text-align:center;"><input type="text" id="comment" name="comment" style="width: 450px;height:25px" /><input type = "submit" value = "Submit"></input><input type = "submit" name ="cancel" value = "Cancel" onClick = "javascript:clear()"><a href = "questions.jsp?questionList=${questionList}"><input type="button" value="back"/></a></div>
		
	<!--  	<a href = "questions.jsp?questionList=${questionList}"><button>Back</button></a> -->
		</form>
		<script type="text/javascript">
		
		function clear()
		   {  
		   document.getElementById('comment').value = "";
		   }
		</script>
		
		
	
</body>
</html>

