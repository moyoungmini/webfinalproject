package Java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DB db = new DB();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (request.getParameter("idOverlap") != null) {
			String id = request.getParameter("id");
			if (db.idOverlap(id)) {
				out.println("<script>alert('ID Overlap');</script>");
				out.println("<script>history.back();</script>");
			} else {
				out.println("<script>alert('Good ID');</script>");
				out.println("<script>history.back();</script>");
			}
		}
		// ID Event 처리
		else if (request.getParameter("nameOverlap") != null) {
			String name = request.getParameter("name");
			if (db.nameOverlap(name)) {
				out.println("<script>alert('Name Overlap!');</script>");
				out.println("<script>history.back();</script>");
			} else {
				out.println("<script>alert('Good Name');</script>");
				out.println("<script>history.back();</script>");
			}
		}
		// Name Event 처리
		else if (request.getParameter("submit") != null) {
			String id = request.getParameter("id");
			String pw1 = request.getParameter("pw1");
			String pw2 = request.getParameter("pw1");
			String name = request.getParameter("name");
			String address = request.getParameter("email");

			if (db.signUP(id, pw1, pw2, name, address)) {
				out.println("<script>alert('Sign UP Success');</script>");
				response.sendRedirect("login.jsp");
			}
			// 로그인 성공시 User테이블에 등록 후 이전 페이지로 이동한다.
			else {
				out.println("<script>alert('Sign UP Not Success');</script>");
				response.sendRedirect("login.jsp");
			}
			// 로그인 예외 처리
		}
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
