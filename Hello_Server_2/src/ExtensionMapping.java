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
 * Time: 3:23 PM
 * Description:
 */


/*mehidi file txt or JS or ..... etc onema  file ekak nisa trigger wei*/
@WebServlet(urlPatterns = "*.txt")
/*@WebServlet(urlPatterns = "*.js")*/
public class ExtensionMapping extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doGet(req, resp);*/
    PrintWriter out = resp.getWriter();
    out.println("Extension Mapping Servlet");
    }
}
