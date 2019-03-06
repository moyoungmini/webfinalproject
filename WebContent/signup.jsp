<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	 <%
    if (session.getAttribute("id") != null) {
        response.sendRedirect("main.jsp");
    }
    %>
    <!-- Exception <Logout 하지 않고 URL을 통해서 Login 페이지로 올 경우 로그아웃을 하라는 main.jsp 페이지로 이동 -->
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=session.getAttribute("id")%></title>
<style>
	body {
	background-color: darkslategray;
}

#id, #pw1, #pw2, #name, #email {
	color: bisque;
	background-color: darkslategray;
	border-color: khaki;
	width: 350px;
	height: 25px;
	border-radius: 5px;
}

#idOverlap, #nameOverlap {
	color: bisque;
	background-color: darkslategray;
	border-color: khaki;
	width: 150px;
	height: 25px;
	border-radius: 5px;
}

#input {
	background-color: goldenrod;
	color: lightpink;
}

#form {
	position: absolute;
	top: 30%;
	left: 50%;
	width: 1000px;
	height: 200px;
	margin: 0px 0px 0px -150px;
}

#input {
	border-radius: 5px;
	border: 0;
	outline: 0;
	width: 350px;
	height: 25px;
	color: lightpink;
	size: 20px;
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
</style>
<!-- Style Set -->
</head>
<body>
	<form action ="SignUpServlet" id="form" class="hc vc">
        <br>
        <input type="text" name="id" id="id" placeholder="ID">
        <input type ="submit" id = "idOverlap" name="idOverlap" value="Overlap Check">
        <br>
        <br>
        <input type="password" name="pw1" id="pw1" placeholder="Password">
        <br>
        <br>
        <input type="password" name="pw2" id="pw2" placeholder="Password Check">
        <br>
        <br>
        <input type="text" name="name" id="name" placeholder="NickName">
        <input type ="submit" id = "nameOverlap" name="nameOverlap" value="Overlap Check">
        <br>
        <br>
        <input type="text" name="email" id="email" placeholder="Email Address">
        <br>
        <br>
        <input type="submit" name="submit" value="Input Finish" id="input">
        <br>
        <br>
        <span id="span"><a href="login.jsp">Cancel</a></span>
    </form>
</body>
</html>