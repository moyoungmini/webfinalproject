<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    if (session.getAttribute("id") == null) {
        response.sendRedirect("login.jsp");
    	//ID Session이 없을 경우 로그인 페이지로 이동
    }
	%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%=session.getAttribute("id")%></title>
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

	table {
	width: 800px;
		border-right:none;
		border-left:none;
		border-top:none;
		border-bottom:none;
	}
	#write {
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

	table {
		width: 1000px;
	}
	#write {
	border-radius: 5px;
	border: 0;
	outline: 0;
	width: 150px;
	height: 35px;
	color: lightpink;
	font-size: 20px;
	}
	#name{
		width: 1000px;
		text-align: center;
		border: 1px solid #eeeeee;
	}
	#content {
		width: 1000px;
	}
	form {
	position: absolute;
	top: 10%;
	left: 25%;
	width: 1100px;
	height: 700px;
	margin: 0px 0px 0px -150px;
	}
</style>
</head>

<body>
	<form action="WriteServlet" method="GET">
	<table style="text-align : center;">
		<thead>
			<tr>
				<th colspan="2" style="background-color:#eeeeee; text-align:center;">Write Form</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" placeholder="Writing Name" name="name" id="name" maxlength="50"></td>
			</tr>
			<tr>
				<td><textarea type="text" placeholder="Writing Content" name="content" id="content"maxlength="2048" style="height:350px"></textarea></td>
			</tr>
		</tbody>
		<br>
	</table>
	<br>
	<input type="submit" value="Write" id="write" name="write">
	</form>	
</body>
</html>