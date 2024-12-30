import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: vishmee
 * Date: 12/12/24
 * Time: 3:10 PM
 * Description:
 */
@WebServlet(urlPatterns = "/auth/*")
/*curl --location 'http://localhost:8080/Hello_Server_2_Web_exploded/auth/*'   mehi auth ekt pasu kumk damuwath print wenne          out.println("Wild Card Mapping Servlet"); pamani*/
public class WildCardMapping extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doGet(req, resp);*/
        PrintWriter out = resp.getWriter();
        out.println("Wild Card Mapping Servlet");
    }
}
