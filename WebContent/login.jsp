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
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<style>
	body {
	background-color: darkslategray;
}

#id{
	color: bisque;
	background-color: darkslategray;
	border-color: khaki;
	width: 350px;
	height: 25px;
	border-radius: 5px;
}
#pw {
	color: bisque;
	background-color: darkslategray;
	border-color: khaki;
	width: 350px;
	height: 25px;
	border-radius: 5px;
}

#input {
	background-color: goldenrod;
}

#form {
	position: absolute;
	top: 50%;
	left: 50%;
	width: 400px;
	height: 100px;
	margin: 0px 0px 0px -150px;
}

#input {
background-color: goldenrod;
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
<title>Login</title>
</head>
<body>

	 <canvas id="myCanvas" width="2000" height="500"></canvas>
     <script>
        var canvas = document.getElementById('myCanvas');
        var context = canvas.getContext('2d');

        var numX = 700;
    	var numY = 150;
    	var dist = Math.PI/180; 
    	context.textAlign = "center";
        context.textBaseline = "middle";
        context.font = "bold 15px sans-serif";
        context.fillStyle = "black";
        
        setInterval(fun, 1000);
		function f(){
			context.fillText("1",numX+(110*Math.cos(300 * dist)),numY+(110*Math.sin(300 * dist)));
	        context.fillText("2",numX+(110*Math.cos(330 * dist)),numY+(110*Math.sin(330 * dist)));
	        context.fillText("3",numX+(110*Math.cos(0 * dist)),numY+(110*Math.sin(0 * dist)));
	        context.fillText("4",numX+(110*Math.cos(30 * dist)),numY+(110*Math.sin(30 * dist)));
	        context.fillText("5",numX+(110*Math.cos(60 * dist)),numY+(110*Math.sin(60 * dist)));
	        context.fillText("6",numX+(110*Math.cos(90 * dist)),numY+(110*Math.sin(90 * dist)));
	        context.fillText("7",numX+(110*Math.cos(120 * dist)),numY+(110*Math.sin(120 * dist)));
	        context.fillText("8",numX+(110*Math.cos(150 * dist)),numY+(110*Math.sin(150 * dist)));
	        context.fillText("9",numX+(110*Math.cos(180 * dist)),numY+(110*Math.sin(180 * dist)));
	        context.fillText("10",numX+(110*Math.cos(210 * dist)),numY+(110*Math.sin(210 * dist)));
	        context.fillText("11",numX+(110*Math.cos(240 * dist)),numY+(110*Math.sin(240 * dist)));
			context.fillText("12",numX+(110*Math.cos(270 * dist)),numY+(110*Math.sin(270 * dist)));
		}
		<!--시계에 숫자 표시-->
		
        function fun() {
            var date = new Date();
            var hr = parseInt(date.getHours()) % 12;
            var sec = parseInt(date.getSeconds());
            var min = parseInt(date.getMinutes());

            context.clearRect(0, 0, 2000, 500);
            context.beginPath();
            context.arc(700, 150, 120, 0, 2.0 * Math.PI, false);
            context.strokeStyle = "black";
            context.stroke();

            context.beginPath();
            context.strokeStyle="black";
            context.moveTo(700, 150);
            context.lineTo(700 + 50 * Math.cos((hr * 30 - 90) * (Math.PI / 180)), 150 + 50 * Math.sin((hr * 30 - 90) * (Math.PI / 180)));
            context.stroke();

            context.beginPath();
            context.strokeStyle="goldenrod";
            context.moveTo(700, 150);
            context.lineTo(700 + 70 * Math.cos((min * 6 - 90) * (Math.PI / 180)), 150 + 70 * Math.sin((min * 6 - 90) * (Math.PI / 180)));
            context.stroke();

            context.beginPath();
            context.strokeStyle="lightpink";
            context.moveTo(700, 150);
            context.lineTo(700 + 90 * Math.cos((sec * 6 - 90) * (Math.PI / 180)), 150 + 90 * Math.sin((sec * 6 - 90) * (Math.PI / 180)));
            context.stroke();
            f();	
        }
        <!-- 시계바늘을 1초마다 초기화-->
    </script>
	<!-- 실습시간에 했던 과제 참조 -->

    <form action ="LoginServlet" method ="GET" id="form" class="hc vc">
        <input type="text" name="id" id="id" placeholder="ID">
        <br>
        <br>
        <input type="password" name="pw" id="pw" placeholder="Password">
        <br>
        <br>
        <input type="submit" value="Login" id="input">
        <br>
        <br>
        <span id="span"><a href="signup.jsp">SignUp</a></span>
    </form>
</body>
</html>