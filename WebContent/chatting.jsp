<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
	if (session.getAttribute("id") == null) {
        response.sendRedirect("login.jsp");
    }
    %>
    <!-- 그냥 URL을 통해 접속의 경우(로그인 없이) Login.jsp페이지로 이동 -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=session.getAttribute("id")%></title>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<style>
	body {
	background-color: darkslategray;
}

#content {
    color: bisque;
	background-color: darkslategray;
	border-color: khaki;
	width: 700px;
	height: 500px;
	border-radius: 5px;
}
#inputText {
    color: bisque;
	background-color: darkslategray;
	border-color: khaki;
	width: 700px;
	height: 35px;
	border-radius: 5px;
}


#submit {
	color: bisque;
	background-color:goldenrod;
	border-color: khaki;
	width: 705px;
	height: 25px;
	border-radius: 5px;
}

#form {
	position: absolute;
	top: 0%;
	left: 40%;
	width: 1000px;
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
#pTag{
    height:0%
}
</style>
<!-- Style Set -->
</head>
<body>
    	<%
    		String id;
    		id= (String)session.getAttribute("id");
    	%>
    	<!-- 회원 정보 표시를 위해 ID값을 세션값으로부터 읽어 온다. -->
				<span id="form" name="form" class="hc vc">
                   <br>	
                   <textarea id="content" name="content" readonly="readonly" ></textarea> <br><br>
                   <input type="text" name="inputText" id="inputText" onkeyup="enterkey();">
                   <p id="pTag"></p>
                   <input type="submit" name="submit" id="submit" value="Submit" onclick="send()">
                   <span id="span"><a href="main.jsp" onclick="onclose()">Pre</a></span>
               </span>
        <script type="text/javascript">
        var textarea = document.getElementById("content");
        var webSocket = new WebSocket('ws://localhost:8080/InpFinalProject/broadcasting'); // WebSocket 접속
        var inputMessage = document.getElementById('inputText');
        
    function enterkey() {
            if (window.event.keyCode == 13) {
                 send();
            }
            //Enter 입력의 경우 처리
    }
	webSocket.onclose = function(event){
		onclose(event)
	};
	//연결 종료시 처리
    webSocket.onerror = function(event) {
      onError(event)
    };
    webSocket.onopen = function(event) {
      onOpen(event)
    };
    //소켓 접속시 처리
    webSocket.onmessage = function(event) {
      onMessage(event)
    };
    function onMessage(event) {
        textarea.value += event.data + "\n";
    }
    //메세지가 서버로부터 왔을 경우 textArea에 값 추가
    function onOpen(event) {
        textarea.value += "<%=id%>"+" : come in \n";
        webSocket.send("<%=id%>"+ " : come in");
    }
    //연결 성공시 처리
    function onError(event) {
      alert(event.data);
    }
    function onclose(event){
    	alert("DF");
    	  textarea.value += "<%=id%>"+" : go out \n";
          webSocket.send("<%=id%>"+ " : go out");
    }
    //소켓 종료시 처리
    function send() {
        textarea.value += ("<%=id%>" + " : " +inputMessage.value + "\n");
        webSocket.send("<%=id%>" + " : " +inputMessage.value);
        inputMessage.value = "";
    }
    //서버로 메세지 보낼 경우 처리.
  </script>
</body>
</html>

<!-- 
Chatting 기능을 위해 socket.io(Web Socket을 하나의 API로 추상화 한 것으로 자바스크립트에서 사용 가능)을 사용했다.
https://d2.naver.com/helloworld/1336
https://www.joinc.co.kr/w/man/12/websocket
블로그 참조를 했으며
웹소켓의 경우 API 자체가 심플하고 양방향 통신이 가능하며 이 외에 많은 장점이 있다.(전형적인 브라우저 랜더 방식은 Request, Response로 클라이언트에서 요청이 일어날때만 response를 했었다. 이를 해결하기 위해서
polling 방식을 이용했으나 이 자체는 효율적이지 못했다.)
내가 코드를 처리 할것은 WebSocket 접속 이후 웹 소켓의 onOpen, send, onMessage, onClose 함수를 처리만 하면 된다.
 -->