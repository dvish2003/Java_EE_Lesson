import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: vishmee
 * Date: 12/5/24
 * Time: 3:37 PM
 * Description:
 *///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


@WebServlet (urlPatterns = "/h")
public class Main extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Path = req.getServletPath();
        String url = req.getRequestURI();
        String Context = req.getContextPath();
        String Method = req.getMethod();

        PrintWriter out = resp.getWriter();
        out.println("Servlet :"+Path);
        out.println("url     :"+url);
        out.println("Context :"+Context);
        out.println("Method  :"+Method);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doPost(req, resp);*/
        PrintWriter out = resp.getWriter();
        out.println("DO POST");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doPut(req, resp);*/
        PrintWriter out = resp.getWriter();
        out.println("DO PUT");
    }
}
