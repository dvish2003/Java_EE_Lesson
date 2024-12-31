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
import java.sql.*;

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
       try {
           Connection connection = getConnection();
           String item_id = req.getParameter("item_id");
           String item_name = req.getParameter("item_name");
           String item_price = req.getParameter("item_price");
           String item_qty = req.getParameter("item_qty");

           System.out.println(item_id);
           System.out.println(item_name);
           System.out.println(item_price);
           System.out.println(item_qty);

           if (item_id == null || item_id.isEmpty() && item_name == null || item_name.isEmpty() && item_price == null || item_price.isEmpty() && item_qty == null || item_qty.isEmpty()) {
               resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
               resp.getWriter().write("{\"error\" : \"ID is required..!\"}");
           }else{
               String query = "insert into item values(?,?,?,?)";
               PreparedStatement preparedStatement = connection.prepareStatement(query);
               preparedStatement.setString(1, item_id);
               preparedStatement.setString(2, item_name);
               preparedStatement.setString(3, item_price);
               preparedStatement.setString(4, item_qty);

               preparedStatement.executeUpdate();
               resp.setStatus(HttpServletResponse.SC_CREATED);
               resp.getWriter().write("{\"message\" : \"item Save successful\"}");
           }


       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = getConnection();
            String item_id = req.getParameter("item_id");
            String item_name = req.getParameter("item_name");
            String item_price = req.getParameter("item_price");
            String item_qty = req.getParameter("item_qty");

            System.out.println(item_id);
            System.out.println(item_name);
            System.out.println(item_price);
            System.out.println(item_qty);

            if (item_id == null || item_id.isEmpty() && item_name == null || item_name.isEmpty() && item_price == null || item_price.isEmpty() && item_qty == null || item_qty.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\" : \"ID is required..!\"}");
            }else{
                String query = "UPDATE item SET item_name = ?, item_price = ?, item_qty = ? WHERE item_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, item_name);
                preparedStatement.setString(2, item_price);
                preparedStatement.setString(3, item_qty);
                preparedStatement.setString(4, item_id);

                preparedStatement.executeUpdate();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"message\" : \"item update successful\"}");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

 try {
     Connection connection = getConnection();
     String item_id = req.getParameter("item_id");
     if ( item_id==null || item_id.isEmpty()){
         resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
         resp.getWriter().write("{\"error\" : \"ID is required..!\"}");
     }else{
         String query = "DELETE FROM item WHERE item_id = ?";

         PreparedStatement preparedStatement = connection.prepareStatement(query);
         preparedStatement.setString(1, item_id);
         preparedStatement.executeUpdate();
         resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
         resp.getWriter().write("{\"message\" : \"item Save successful\"}");

     }

     } catch (Exception e) {
     throw new RuntimeException(e);
 }
    }


}
