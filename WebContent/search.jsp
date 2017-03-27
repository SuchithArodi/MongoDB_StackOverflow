<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Page</title>
</head>
<script type="text/javascript">
/* function searchValidation()
{
	var searchString = document.search.searchTerm.value.length;  
	if (searchString == 0)  
	{  
	alert("Enter Search Term"); 
	//document.getElementById("searchButton").append('<br/><span>Enter Search Term</span>');
	//searchString.focus();  
	//alert(searchString);
	
	}  
	
}
 */
function connection()
{
	//var error = ${errors};
	//if(error == "null")
	//	{
	var dbmessage = "Connection to Database Successful";
	var servermessage = "Connection to Mongo Server is done";
    alert(servermessage+"\n"+dbmessage);
		//}
	/* else
		{
		   alert("ERROR connecting to DB!");
		  
		} */
}



</script>
<body bgcolor="orange" onload="connection()">
<img alt="picture1" src="img/stackoverflow.png">
<form action = "search.do" name ="search" method = "POST" onsubmit="return searchValidation();">

<div style="text-align:center;"><img alt="picture1" src="img/newq&a.jpg  "></div>
<table height=100% width=100%>
		<tr>
			<td>
			<div style="text-align:center;">
			<input type="text" id="searchTerm" name="searchTerm" style="width: 400px;height:25px" /><br/>
			
			<span>${noquestions}</span>
			</div>
			</td>
		</tr><tr>
			<td height="10px" colspan="5" bgcolor="orange"><div style="text-align:center;"><font size="80">
			<input type="submit" value="Search" id = "searchButton" style="height:50px; width:50px">
			<input type="reset" value="Reset"></font></div></td>
		</tr>
		
	
	</table>
	</form>
</body>
</html>

