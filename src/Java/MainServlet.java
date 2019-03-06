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
		// 회원정보 변환
		else if (request.getParameter("notice") != null) {
			//<td><a href="view.jsp?no=<%=list.get(i).getNo() %>">Content</a></td>
			response.sendRedirect("notice.jsp?select=default&value=default");
			//게시판으로 넘어 갈 때 GET방식을 이용하여 검색기능이 아닌 default를 의미하는 정보를 보낸다.
		}
		// 게시판
		else if (request.getParameter("chatting") != null) {
			response.sendRedirect("chatting.jsp");
		}
		// 채팅 프로그램
		else if (request.getParameter("manage") != null) {
			response.sendRedirect("userManage.jsp");
		} else {
			HttpSession session = request.getSession();
			session.removeAttribute("id"); // 로그아웃시 세션 제거
			response.sendRedirect("login.jsp");
		}
		// 로그아웃 처리
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
