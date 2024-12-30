package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: vishmee
 * Date: 12/31/24
 * Time: 2:38 AM
 * Description:
 */
@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Shop", "root", "Ijse@123");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
PrintWriter out = resp.getWriter();
        try {
          Connection connection = getConnection();
          ResultSet rs = connection.createStatement().executeQuery("select * from item");

            JsonArrayBuilder allItem = Json.createArrayBuilder();

          while (rs.next()) {
              String item_id = rs.getString("item_id");
              String item_name = rs.getString("item_name");
              String item_price = rs.getString("item_price");
              String item_qty = rs.getString("item_qty");

              JsonObjectBuilder item = Json.createObjectBuilder();
              item.add("item_id", item_id);
              item.add("item_name", item_name);
              item.add("item_price", item_price);
              item.add("item_qty", item_qty);
              allItem.add(item);
          }
            resp.setContentType("application/json"); // describe data as json
            resp.getWriter().write(allItem.build().toString());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
