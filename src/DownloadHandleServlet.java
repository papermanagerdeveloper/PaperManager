import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet(name = "DownloadHandleServlet")
public class DownloadHandleServlet extends HttpServlet
{
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String schedule = request.getParameter("schedule");
        String studentID = request.getParameter("ID");
        String filePath = "/data/" + schedule + "/" + studentID + ".pdf";
        System.out.println("文件路径:"+filePath);
        File file = new File(filePath);
        //如果文件不存在
        if (!file.exists())
        {
            //用于设置服务器端response的编码格式
            response.setCharacterEncoding("UTF-8");
            //用于设置客户端浏览器以什么编码解析文字
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            System.out.println(response.getCharacterEncoding());
            PrintWriter out = response.getWriter();
            out.println("错误！当前文件不存在");
            return;
        }
        response.setHeader("content-disposition", "attachment;filename=" + studentID + ".pdf");
        FileInputStream in = new FileInputStream(filePath);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while ((len = in.read(buffer)) > 0)
        {
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
    }
}
