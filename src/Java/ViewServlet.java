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
			response.sendRedirect("notice.jsp?select=default&value=default"); // notice.jsp로 default값 처리
		} else if (request.getParameter("update") != null) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			String writer = (String) session.getAttribute("writer");
			if (id.equals(writer) || id.equals("root")) { // 작성 회원 혹은 관리자의 경우 업데이트 가능.
				response.sendRedirect("update.jsp");
			} else {
				response.sendRedirect("notice.jsp?select=default&value=default"); // 다른 회원의 경우 notice.jsp로 default값 처리
				// Not Equal
			}
		}
		// update시 글 작성자와 현재 세션아이디와 비교하여 같으면 update.jsp로 이동한다. 또한 세션아이디가 root일 경우 관리자이므로
		// update.jsp로 이동가능하게 한다.
		else if (request.getParameter("delete") != null) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			String writer = (String) session.getAttribute("writer");
			int no = (int) session.getAttribute("no");
			// id와 writer의 일치유무에 따라 삭제판단.
			if (id.equals(writer) || id.equals("root")) { // 작성 회원 혹은 관리자의 경우 삭제 가능.
				db.removeList(no);
				response.sendRedirect("notice.jsp?select=default&value=default"); // notice.jsp로 default값 처리
			} else {
				response.sendRedirect("notice.jsp?select=default&value=default"); // notice.jsp로 default값 처리

				// Not Equal
			}
		}
		// delete시 글 작성자와 현재 세션아이디와 비교하여 같으면 db.removeLIst 함수를 수행한다. 또한 세션아이디가 root일 경우
		// 관리자이므로 remove함수를 수행시킨다.
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
