<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Page</title>

</head>


<body bgcolor="orange">
<img alt="picture1" src="img/stackoverflow.png">
<center><h1>Welcome to Stack Overflow Q and A</h1></center>
<form action= "connection.do"  method = "post">
<h5>${errors}</h5>
<div style="text-align:center;"><img alt="picture1" src="img/qa.jpg"></div>
<div style="text-align:center;"><button type="submit" class="action" >ConnectToMongo</button></div>
<!--  <h5>${errors}</h5> -->
<!-- <a href = "search.jsp"> Search On Stack Over Flow </a> -->
</form>
</body>
</html>

