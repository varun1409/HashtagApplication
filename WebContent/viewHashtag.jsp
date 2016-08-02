<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="selab1.Hashtag"%>
    <%@page import="selab1.HashtagFeed"%>  
    <%@page import="java.util.ArrayList"%>  
    <%@page import="java.text.SimpleDateFormat"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function clickFunction(clicked_id)
{
	//alert(document.getElementById("parentid").value);
	var str=clicked_id;
	var pid=str.substring(6);
	var msg="reply"+pid;
	//alert(pid);
	//alert(document.getElementById(msg).value);
	document.getElementById("replyContent").value=document.getElementById(msg).value;
	//alert(document.getElementById("replyContent").value);
	document.getElementById("parentid").value=pid;
	//alert(document.getElementById("parentid").value);
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view hashtag</title>
</head>
<body>
<%
Hashtag htt=(Hashtag)request.getAttribute("hash");
String msg2=(String)request.getAttribute("msg2");
out.write("<h1>"+htt.getHashtag()+"</h1>");
%>
<br>
<form action="ViewHashtagServlet" method="post">
<input type="text" name="postContent">
<input type="Submit" value="Post">
<%out.write(msg2); %><hr>
<input type="hidden" name="hash" value=<%=htt.getHashtag() %>>
<input type="hidden" id="parentid" name="parentid" value="0">
<input type="hidden" name="replyContent" id="replyContent" value="">

<%
@SuppressWarnings("unchecked")
ArrayList<HashtagFeed> htf=(ArrayList<HashtagFeed>)request.getAttribute("Hashtagvalues");
if(htf.size()<1)
{
	String msg=(String)request.getAttribute("msg");
	if(msg!=null)
	{
	out.write("<br>"+msg);
	}
}
else
{
for(int i=0;i<htf.size();i++)
{
	
	if(htf.get(i).getParentId()==0)
	{
		
		out.write(htf.get(i).getcontent()+"-------"+htf.get(i).getpostedDate()+"<br>");
		
	for(int j=0;j<htf.size();j++)
	{
		if(htf.get(j).getParentId()==htf.get(i).getId())
		{
			out.write("	--> "+htf.get(j).getcontent()+"<br>");
		}
	}
	%>	
	<input type="text" name="reply<%=htf.get(i).getId()%>" id="reply<%=htf.get(i).getId()%>">
	<input type="submit" name="submit<%=htf.get(i).getId() %>" id="submit<%=htf.get(i).getId() %>" value="Reply" onClick="clickFunction(this.id)"> 
	<hr>
<%	
	
	}
}
}
%>
</form>
</body>
</html>