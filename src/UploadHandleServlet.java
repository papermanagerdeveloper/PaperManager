import beans.DBUtil;
import beans.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UploadHandleServlet")
public class UploadHandleServlet extends HttpServlet
{
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try
        {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(10000000);
            upload.setHeaderEncoding("UTF-8");

            //判断请求消息中的内容是否是“multipart/form-data”类型,不是则为false.
            if (!ServletFileUpload.isMultipartContent(request))
            {
                return;
            }

            //将通过表单中每一个HTML标签提交的数据封装成一个FileItem对象，然后以List列表的形式返回。
            List<FileItem> list = upload.parseRequest(request);
            Map<String, String> property = new HashMap<>();
            for (FileItem item : list)
            {
                //判断FileItem类对象封装的数据是一个普通文本表单字段，还是一个文件表单字段.
                //如果是普通表单字段则返回true，否则返回false。
                if (item.isFormField())
                {
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    property.put(name, value);
                }
                else
                {
                    String filename = item.getName();
                    System.out.println("获取文件：" + filename);
                    if (filename == null || filename.trim().equals(""))
                    {
                        continue;
                    }
                    filename = filename.substring(filename.lastIndexOf("/") + 1);
                    filename = filename.toLowerCase();
                    if (!filename.matches(".*\\.pdf"))
                    {
                        PrintWriter out = response.getWriter();
                        out.println("请上传pdf格式论文");
                        return;
                    }
                    filename = user.getID() + ".pdf";
                    InputStream in = item.getInputStream();
                    String savePath = chooseSavePath(user.getID()) + filename;

                    FileOutputStream out = new FileOutputStream(savePath);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) > 0)
                    {
                        out.write(buffer, 0, len);
                    }
                    in.close();
                    out.close();
                    item.delete();
                }
            }
        } catch (Exception e)
        {
            response.sendRedirect("ChoosePageServlet");
            e.printStackTrace();
        }
    }

    private String chooseSavePath(String userID)
    {
        DBUtil db = new DBUtil();
        String query = "select schedule from studentInfo where ID=" + userID;
        Map result = db.getMap(query, new String[]{});
        return "/data/" + result.get("schedule") + "/";
    }

}