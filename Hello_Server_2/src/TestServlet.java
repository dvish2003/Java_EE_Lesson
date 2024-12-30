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
 * Time: 2:19 PM
 * Description:
 */
/*
@WebServlet(urlPatterns = "/test2")      =          <?xml version="1.0" encoding="UTF-8"?>
( meka annotacion ekak use karala hadnne )           <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
                                                         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                                                         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
                                                         version="4.0">
                                                    <servlet>
                                                        <servlet-name>Tests</servlet-name>
                                                        <servlet-class>TestServlet</servlet-class>
                                                    </servlet>
                                                    <servlet-mapping>
                                                        <servlet-name>Tests</servlet-name>
                                                        <url-pattern>/test2</url-pattern>
                                                    </servlet-mapping>
                                                </web-app>
                                               ( meka Manual hadnne )
*/
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doGet(req, resp);*/
        PrintWriter out = resp.getWriter();
        out.println("Deployment Descriptor Work");
    }
}
