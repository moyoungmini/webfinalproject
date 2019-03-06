package Java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DB db = new DB();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewServlet() {
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
		if (request.getParameter("list") != null) {
			response.sendRedirect("notice.jsp?select=default&value=default"); // notice.jsp�� default�� ó��
		} else if (request.getParameter("update") != null) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			String writer = (String) session.getAttribute("writer");
			if (id.equals(writer) || id.equals("root")) { // �ۼ� ȸ�� Ȥ�� �������� ��� ������Ʈ ����.
				response.sendRedirect("update.jsp");
			} else {
				response.sendRedirect("notice.jsp?select=default&value=default"); // �ٸ� ȸ���� ��� notice.jsp�� default�� ó��
				// Not Equal
			}
		}
		// update�� �� �ۼ��ڿ� ���� ���Ǿ��̵�� ���Ͽ� ������ update.jsp�� �̵��Ѵ�. ���� ���Ǿ��̵� root�� ��� �������̹Ƿ�
		// update.jsp�� �̵������ϰ� �Ѵ�.
		else if (request.getParameter("delete") != null) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			String writer = (String) session.getAttribute("writer");
			int no = (int) session.getAttribute("no");
			// id�� writer�� ��ġ������ ���� �����Ǵ�.
			if (id.equals(writer) || id.equals("root")) { // �ۼ� ȸ�� Ȥ�� �������� ��� ���� ����.
				db.removeList(no);
				response.sendRedirect("notice.jsp?select=default&value=default"); // notice.jsp�� default�� ó��
			} else {
				response.sendRedirect("notice.jsp?select=default&value=default"); // notice.jsp�� default�� ó��

				// Not Equal
			}
		}
		// delete�� �� �ۼ��ڿ� ���� ���Ǿ��̵�� ���Ͽ� ������ db.removeLIst �Լ��� �����Ѵ�. ���� ���Ǿ��̵� root�� ���
		// �������̹Ƿ� remove�Լ��� �����Ų��.
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
