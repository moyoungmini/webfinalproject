<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="Java.*" %>
    <%@ page import="java.util.ArrayList" %>
    
    <%
    if (session.getAttribute("id") == null) {
        response.sendRedirect("login.jsp");
    	//ID Session이 없을 경우 로그인 페이지로 이동
    }
	%>
    
    <%
    int no = Integer.parseInt(request.getParameter("no"));
    DB db = new DB();
    String [] tmp = db.getView(no);
    String title =tmp[0];
    String writer =tmp[1];
    String date = tmp[2];
    String content = tmp[3];
    session.setAttribute("writer", writer);
    session.setAttribute("no",no);
    session.setAttribute("content", content);
    session.setAttribute("title", title);
    %>
    <!-- notice.jsp에서 보내준 no값을 이용하여 DB로부터 값을 읽어와서 화면에 표시하고 Session에 저장을 한다.  -->
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title><%=session.getAttribute("id")%></title>
<style>
body {
	background-color: darkslategray;
}

form {
	position: absolute;
	top: 10%;
	left: 30%;
	width: 1200px;
	height: 200px;
	margin: 0px 0px 0px -150px;
}
	table {
		width: 900px;
	}

	#content {
		height:400px;
	}
	#list, #update, #delete {
	border-radius: 5px;
	border: 0;
	outline: 0;
	width: 150px;
	height: 25px;
  background-color: cornsilk;
  color:darkslategrey;
  font-size:  15px;
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
	<form action="ViewServlet" method="GET">
	<table style="text-align : center;">
		<thead>
			<tr>
				<th colspan="3"text-align:center;">Notice-Board View</th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<th style="width: 20%;">Title</th>
				<td colspan="2"><%=title%></td>
			</tr>
			<tr>
				<th>Writer</th>
				<td colspan="2"><%=writer%></td>
			</tr>
			<tr>
				<th>Date</th>
				<td colspan="2"><%=date%></td>
			</tr>
			<tr>
				<th>Content</th>
				<td  id="content" colspan="2" style="min-height: 600px; text-align: left;"><%=content%></td>
			</tr>
		</tbody>
	</table>
	<br>
	<input type="submit" value="List" id="list" name="list">
	<input type="submit" value="Update" id="update" name="update">
	<input type="submit" value="Delete" id="delete" name="delete">
	</form>	
</body>
</html>