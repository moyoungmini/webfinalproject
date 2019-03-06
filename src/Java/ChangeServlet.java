package Java;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeServlet
 */
@WebServlet("/ChangeServlet")
public class ChangeServlet extends HttpServlet {
	private DB db = new DB();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeServlet() {
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
		if (request.getParameter("nameOverlap") != null) {
			if (db.nameOverlap(request.getParameter("name"))) {
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Name Overlap');");
				out.println("location='change.jsp';");
				out.println("</script>");
				return;
			}
		} else {
			String id = request.getParameter("id");
			String pw1 = request.getParameter("pw1");
			String pw2 = request.getParameter("pw1");
			String name = request.getParameter("name");
			String address = request.getParameter("email");
			PrintWriter out = response.getWriter();
			if (db.update(id, pw1, pw2, name, address)) {
				out.println("<script>alert('Update Success');</script>");
				response.sendRedirect("main.jsp");
			} else {
				out.println("<script>alert('Update Not Success');</script>");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	//회원정보 수정 Controller
}
