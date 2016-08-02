<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body>
<h1>SEARCH</h1><br>
<form action="SearchServlet" method="post">
<input type="text" name="searchHashtag">
<input type="submit" value="Search">
<%
String errormsg=(String)request.getAttribute("errormessage");
if(errormsg!=null)
{
out.write("<br>"+errormsg);
}
%>
</form>
</body>
</html>