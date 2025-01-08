import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/dbpc")
public class DBCPServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/Application1");
        ds.setUsername("root");
        ds.setPassword("Ijse@123");
        ds.setMaxTotal(5);
        ds.setInitialSize(5);*/
    /*//common interface to all servlets
        ServletContext servletContext = req.getServletContext();
        servletContext.setAttribute("dataSource",ds);
*/
        ServletContext servletContext = req.getServletContext();
        BasicDataSource ds = (BasicDataSource) servletContext.getAttribute("dataSource");
        PrintWriter out = resp.getWriter();

        try {
            Connection connection = ds.getConnection();
            ResultSet resultSet =connection.prepareStatement("select * from customer").executeQuery();

            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                System.out.println(id + " " + name + " " + address);
                 out.println(id+"  "+name+"  "+address);
            }
            connection.close();
            resp.setContentType("application/json"); // describe data as json

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("DBCPServlet doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        BasicDataSource ds = (BasicDataSource) servletContext.getAttribute("dataSource");

        try {
            Connection connection = ds.getConnection();
            ResultSet resultSet =connection.prepareStatement("select * from customer").executeQuery();

            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                System.out.println(id + " " + name + " " + address);
            }
            connection.close();
            resp.setContentType("application/json"); // describe data as json

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("DBCPServlet doPost");
    }
}
