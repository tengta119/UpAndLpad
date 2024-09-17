package lbwxxc.fin;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet
{
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("文件下载");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String parameter = request.getParameter("fileName");
        if (parameter == null || parameter.trim().isEmpty())
        {
            response.getWriter().write("请输入要下载的文件名");
            response.getWriter().close();
            return;
        }
        String path = request.getServletContext().getRealPath("/down/");
        File file = new File(path + parameter);
        if (file.exists() && file.isFile())
        {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + parameter);
            FileInputStream in = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) != -1)
            {
                out.write(bytes, 0, len);
            }
            out.close();
            in.close();

        }
        else
        {
            response.getWriter().write("文件不存在请重试");
            response.getWriter().close();
        }
    }
}
