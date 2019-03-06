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

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>()); // Client���� ��Ƴ��� ��ü

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		synchronized (clients) {
			// Iterate over the connected sessions
			// and broadcast the received message
			for (Session client : clients) {
				if (!client.equals(session)) {
					client.getBasicRemote().sendText(message);
				}
				// �޼��� �� ��ü�� ������ ������ ��� Ŭ���̾�Ʈ�鿡�� �޼����� ������.
			}
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		clients.add(session);
		// Ŭ���̾�Ʈ ���ӽ� Ŭ���̾�Ʈ���� ��Ƴ��� ��ü�� �߰���Ų��.
	}

	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		// Ŭ���̾�Ʈ ���� ����� Ŭ���̾�Ʈ���� ��Ƴ��� ��ü���� ������Ų��.
	}
}
//���� ��α״� chatting.jsp ����.