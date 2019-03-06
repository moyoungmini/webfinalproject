<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="Java.*" %>
    <%@ page import="java.util.ArrayList" %>
    <%
    if(session.getAttribute("id")==null){
    	response.sendRedirect("login.jsp");
    }
    else if (!session.getAttribute("id").equals("root")) {
        response.sendRedirect("login.jsp");
    }
	%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title><%=session.getAttribute("id")%></title>

<%
	session.removeAttribute("name");
	session.removeAttribute("content");
	session.removeAttribute("writer");
	session.removeAttribute("no");
	session.removeAttribute("title");
	//session 초기화
%>
<style>

body {
	background-color: darkslategray;
}

form {
	position: absolute;
	top: 20%;
	left: 35%;
	width: 1100px;
	height: 200px;
	margin: 0px 0px 0px -150px;
}

a:link {
	color: lightpink;
	text-decoration: none;
}

a:visited {
	color: lightpink;
	text-decoration: none;
}

a:hover {
	color: lightpink;
	text-decoration: none;
}
	table {
	width: 800px;
		border-right:none;
		border-left:none;
		border-top:none;
		border-bottom:none;
	}
	#write, #pre {
	border-radius: 10px;
	border: 0;
	outline: 0;
	width: 120px;
	height: 35px;
  background-color: cornsilk;
  color:darkslategrey;
  font-weight:bold;
  font-size:  20px;
	}
	
	th {
	border-radius: 5px;
		background-color: cornsilk;
 		color:darkslategrey;

	}
	td {
		 border-radius: 5px;
		 background-color: cornsilk;
  		color:darkslategrey;
	}
</style>
</head>

<body>
	<form action="NoticeServlet" method="GET">
	<table style="text-align : center;">
		<thead>
			<tr>
				<th style="text-align:center;">User</th>
				<th style="text-align:center;">Name</th>
				<th style="text-align:center;">PW</th>
				<th style="text-align:center;">Address</th>
				<th style="text-align:center;">Delete</th>
			</tr>
		</thead>
		
		<tbody>
			<%
				DB db = new DB();
				ArrayList <ListUser> list = db.getUser();
				for(int i=0;i<list.size();i++){
			%>
			<tr>
			<td><%=list.get(i).getId() %></td>
			<td><%=list.get(i).getName() %></td>
			<td><%=list.get(i).getPw() %></td>
			<td><%=list.get(i).getAddress() %></td>
			<td><a href="UserDel?id=<%=list.get(i).getId() %>">Delete</a></td>
			</tr>
			<%}%>
			<!-- delete 클릭시 해당 id값을 GET방식으로 전달한다. -->
			<!-- 화면에 DB로부터 읽어 온 값들을 차례대로 표시한다. 그리고 Content 링크를 만들어 링크를 클릭했을 경우 no값을 GET방식으로 전달한다. -->
		</tbody>
	</table>
	<br>
	<a href="main.jsp">Pre</a>
	</form>	
</body>
</html>