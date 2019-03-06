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

#searchText{
	color: bisque;
	background-color: darkslategray;
	border-color: khaki;
	width: 250px;
	height: 30px;
	border-radius: 5px;
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
	#write,#pre,#searchOption,#searchSubmit	 {
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
<!-- Style Set -->
</head>

<body>
	<form action="NoticeServlet" method="GET">
	<table style="text-align : center;">
		<thead>
			<tr>
				<th style="text-align:center;">No</th>
				<th style="text-align:center;">Title</th>
				<th style="text-align:center;">Writer</th>
				<th style="text-align:center;">Date</th>
				<th style="text-align:center;">Content</th>
			</tr>
		</thead>
		
		<tbody>
			<%
				DB db = new DB();
				String select = request.getParameter("select");
				ArrayList <List> list;
				if(select.equals("default")){
					list = db.getList(); // 검색이 아닌 default의 경우 모든 리스트 출력
				}
				else{
					list=db.selectList(request.getParameter("select"), request.getParameter("value"));
					// 검색 했을 경우 해당 select된 list들만 출력을 한다.
				}
				for(int i=0;i<list.size();i++){
			%>
			<tr>
			<td><%=list.get(i).getNo() %></td>
			<td><%=list.get(i).getTitle() %></td>
			<td><%=list.get(i).getWriter() %></td>
			<td><%=list.get(i).getDate() %></td>
			<td><a href="view.jsp?no=<%=list.get(i).getNo() %>">Content</a></td>
			</tr>
			<%}%>
			<!-- Content 클릭의 경우 GET방식을 이용하여 no 값을 전달한다. -->
			<!-- 화면에 DB로부터 읽어 온 값들을 차례대로 표시한다. 그리고 Content 링크를 만들어 링크를 클릭했을 경우 no값을 GET방식으로 전달한다. -->
		</tbody>
	</table>
	<br>
	<input type="submit" value="Write" id="write" name="write">
	<input type="submit" value="Pre" id="pre" name="pre"> &nbsp;&nbsp;&nbsp;&nbsp;
	<br>
	<br>
	<select name="searchOption" id="searchOption">
				<option value="no">No</option>
				<option value="title">Title</option>
				<option value="writer">Writer</option>
			</select>
			<input type="text" id="searchText" name="searchText">
			<input type="submit" value="Search" id="searchSubmit" name="searchSubmit">
	</form>	
</body>
</html>