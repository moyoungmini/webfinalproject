<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<style>
body {
	background-color: darkslategray;
}

form {
	position: absolute;
	top: 30%;
	left: 50%;
	width: 600px;
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
#change, #notice, #chatting, #logout,#manage {
  border-radius: 5px;
	border: 0;
	outline: 0;
	width: 350px;
	height: 50px;
  background-color: cornsilk;
  color:darkslategrey;
  font-size:  20px;
}
</style>
<!-- Style Set -->
</head>
<body>
    <form action ="MainServlet" method ="GET" id="form" class="hc vc"><br><br>
      <input type="submit" name="notice" id="notice" value="Notice Board"><br><br> 
      <input type="submit" name="chatting" id="chatting" value="Chatting"><br><br>
      <input type="submit" name="change" id="change" value="Change Imformation"><br><br>
      <input type="submit" name="manage" id="manage" value="Manage"><br><br>
      <input type="submit" name="logout" id="logout" value="Logout"><br><br>
      <!-- 게시판, 채팅, 회원정보수정, 로그아웃 버튼 -->
    </form>
</body>
</html>