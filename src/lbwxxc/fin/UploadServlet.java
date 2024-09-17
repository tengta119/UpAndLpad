package lbwxxc.fin;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/uploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("文件上传");
        //请求的编码
        request.setCharacterEncoding("utf-8");
        //获取普通表单项
        String uname = request.getParameter("uname");
        System.out.println("uname:"+uname);
        //获取part对象
        Part part = request.getPart("myFile");
        String fileName = part.getSubmittedFileName();
        System.out.println("fileName:"+fileName);
        String realPath = request.getServletContext().getRealPath("/");
        System.out.println("realPath:"+realPath);
        part.write(realPath+"/"+fileName);

    }
}
