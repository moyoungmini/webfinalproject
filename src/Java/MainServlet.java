package Java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("change") != null) {
			response.sendRedirect("change.jsp");
		}
		// ȸ������ ��ȯ
		else if (request.getParameter("notice") != null) {
			//<td><a href="view.jsp?no=<%=list.get(i).getNo() %>">Content</a></td>
			response.sendRedirect("notice.jsp?select=default&value=default");
			//�Խ������� �Ѿ� �� �� GET����� �̿��Ͽ� �˻������ �ƴ� default�� �ǹ��ϴ� ������ ������.
		}
		// �Խ���
		else if (request.getParameter("chatting") != null) {
			response.sendRedirect("chatting.jsp");
		}
		// ä�� ���α׷�
		else if (request.getParameter("manage") != null) {
			response.sendRedirect("userManage.jsp");
		} else {
			HttpSession session = request.getSession();
			session.removeAttribute("id"); // �α׾ƿ��� ���� ����
			response.sendRedirect("login.jsp");
		}
		// �α׾ƿ� ó��
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
