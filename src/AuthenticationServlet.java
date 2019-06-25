

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DBUtil;
import beans.User;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBUtil db = new DBUtil();
        String ID = request.getParameter("ID");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        String query = "select * from " + userType + "Info where ID = ? and password = ?";
        List result = db.getList(query, new String[]{ID, password});
        if (result.size() == 1)
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", new User(ID, userType));
            response.sendRedirect(choosePage(userType));
        }
        else
        {
            PrintWriter out = response.getWriter();
            out.println("用户名不存在或密码错误");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	 private String choosePage(String userType)
	    {
	        String page = new String();
	        if (userType.equals("student"))
	        {
	            page = "studentPages/index.html";
	        }
	        else if (userType.equals("teacher"))
	        {
	            page = "teacherPages/index.html";
	        }
	        else if (userType.equals("administrator"))
	        {
	            page = "administratorPages/index.html";
	        }
	        return page;
	    }

}
