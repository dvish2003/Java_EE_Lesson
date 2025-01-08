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
 * Time: 4:42 PM
 * Description:
 */
@WebServlet(urlPatterns = "/payment")
public class PaymentServlet extends HttpServlet {
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Shop", "root", "Ijse@123");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doGet(req, resp);*/
try{
    Connection connection = getConnection();
    ResultSet resultSet =connection.prepareStatement("select * from payment").executeQuery();

    //create json array
    JsonArrayBuilder allPayment = Json.createArrayBuilder();


    while (resultSet.next()){
        String payment_id = resultSet.getString("payment_id");
        int amount = resultSet.getInt("payment_amount");
        Date date = resultSet.getDate("payment_date");


        JsonObjectBuilder payment = Json.createObjectBuilder();
payment.add("payment_id", payment_id);
payment.add("payment_amount", amount);
payment.add("payment_date", String.valueOf(date));


        allPayment.add(payment);
    }


    resp.setContentType("application/json"); // describe data as json
    resp.getWriter().write(allPayment.build().toString());


} catch (Exception e) {
    throw new RuntimeException(e);
}
    }
}
