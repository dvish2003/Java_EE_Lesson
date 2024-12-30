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
 * Time: 2:54 PM
 * Description:
 */

@WebServlet(urlPatterns = "/")
public class DefaultMapping extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doGet(req, resp);*/
        PrintWriter out = resp.getWriter();
        out.println("Default Mapping IS Working");
    }
}
