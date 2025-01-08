package lk.ijse.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.sql.*;

/**
 * Author: vishmee
 * Date: 12/31/24
 * Time: 2:42 PM
 * Description:
 */

@WebServlet(urlPatterns = "/orders")
public class PlaceOrderServlet extends HttpServlet {
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Shop", "root", "Ijse@123");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            Connection connection =getConnection();
            ResultSet resultSet =connection.prepareStatement("select * from orders").executeQuery();

            //create json array
            JsonArrayBuilder allOrders = Json.createArrayBuilder();


            while (resultSet.next()){
                String Order_id = resultSet.getString("order_Id");
                String customer_id = resultSet.getString("id");
                String payment_id = resultSet.getString("payment_id");
                Date order_date = Date.valueOf(resultSet.getString("order_place_date"));


                JsonObjectBuilder order = Json.createObjectBuilder();

                order.add("order_Id", Order_id);
               order.add("id", customer_id);
               order.add("payment_id", payment_id);
               order.add("order_place_date", String.valueOf(order_date));
                allOrders.add(order);
            }


            resp.setContentType("application/json"); // describe data as json
            resp.getWriter().write(allOrders.build().toString());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
