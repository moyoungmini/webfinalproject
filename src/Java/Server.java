package Java;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/broadcasting")
public class Server {

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>()); // Client들을 모아놓은 객체

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		synchronized (clients) {
			// Iterate over the connected sessions
			// and broadcast the received message
			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(message);
				}
				// 메세지 온 객체를 제외한 나머지 모든 클라이언트들에게 메세지를 보낸다.
			}
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		clients.add(session);
		// 클라이언트 접속시 클라이언트들을 모아놓은 객체에 추가시킨다.
	}

	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		// 클라이언트 연결 종료시 클라이언트들을 모아놓은 객체에서 삭제시킨다.
	}
}
//참조 블로그는 chatting.jsp 참조.