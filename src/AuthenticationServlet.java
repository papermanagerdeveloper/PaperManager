import beans.DBUtil;
import beans.User;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AuthenticationServlet extends HttpServlet
{
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
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
