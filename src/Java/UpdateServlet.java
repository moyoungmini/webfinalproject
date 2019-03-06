package Java;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DB db = new DB();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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

		try {
			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();
			String id = (String) session.getAttribute("id"); // 세션으로부터 아이디를 받는다.
			String title = request.getParameter("name"); // 게시판 글 수정 시 title값(제목)을 받는다.
			String date = db.getDate(); // 현재 시간을 얻는다.
			int no = (int) session.getAttribute("no"); // 세션으로부터 no값을 얻는다.
			String content = request.getParameter("content"); // //게시판 글 수정시 content값(글내용)을 얻는다.
			if (db.writeContent(no, title, id, date, content)) {
				response.sendRedirect("notice.jsp?select=default&value=default"); // 일반적인 notice.jsp로 이동한다.
			} else {
				out.println("<script>alert('Not Success Writing');</script>");
				out.println("<script>history.back();</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
