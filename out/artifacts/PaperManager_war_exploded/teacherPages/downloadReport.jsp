<%@ page import="beans.DBUtil" %>
<%@ page import="beans.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>下载学生报告</title>
    <link rel="stylesheet" type="text/css" href="../bootstrap-4.3.1-dist/css/bootstrap.css">
</head>
<body>
<table class="table">
    <tr>
        <th>学生ID</th>
        <th>学生名</th>
        <th>任务书</th>
        <th>中期报告</th>
        <th>结题报告</th>
    </tr>
    <tr>
        <%
            DBUtil db = new DBUtil();
            User user = (User) session.getAttribute("user");
            String query = "select ID,name,schedule from studentInfo where teacherID = " + user.getID();
            List result = db.getList(query, new String[]{});
            for (int i = 0; i < result.size(); i++)
            {
                Map m = (HashMap) result.get(i);
                out.println("<tr>");
                out.println("<td>" + m.get("ID") + "</td>");
                out.println("<td>" + m.get("name") + "</td>");
                String schedule = (String) m.get("schedule");
                int candownload = 0;
                if (schedule.equals("taskPlan"))
                {
                    candownload = 0;
                }
                else if (schedule.equals("midtermReport"))
                {
                    candownload = 1;
                }
                else if (schedule.equals("finalReport"))
                {
                    candownload = 2;
                }
                else if(schedule.equals("finish"))
                {
                    candownload = 3;
                }
                for (int j = 1; j <= 3; j++)
                {
                    if(j <= candownload)
                    {
                        if (j == 1)
                        {
                            out.println("<td><a href=\"../DownloadHandleServlet?schedule=taskPlan&ID=" + m.get("ID") + "\">下载</a></td>");
                        }
                        if (j == 2)
                        {
                            out.println("<td><a href=\"../DownloadHandleServlet?schedule=midtermReport&ID=" + m.get("ID") + "\">下载</a></td>");
                        }
                        if (j == 3)
                        {
                            out.println("<td><a href=\"../DownloadHandleServlet?schedule=finalReport&ID=" + m.get("ID") + "\">下载</a></td>");
                        }
                    }
                    else
                    {
                        out.println("<td></td>");
                    }
                }
                out.println("</tr>");
            }
        %>
    </tr>
</table>
</body>
</html>
