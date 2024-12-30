package Servlet;

import Entity.Customer;
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
 * Date: 12/30/24
 * Time: 7:43 PM
 * Description:
 */
@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Shop", "root", "Ijse@123");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Shop","root","Ijse@123");
            ResultSet resultSet =connection.prepareStatement("select * from customer").executeQuery();

            //create json array
            JsonArrayBuilder allCustomers = Json.createArrayBuilder();


            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                //System.out.println(id+ " " +name+  " +
                JsonObjectBuilder customer = Json.createObjectBuilder();
                customer.add("id", id);
                customer.add("name", name);
                customer.add("address", address);
                allCustomers.add(customer);
            }


            resp.setContentType("application/json"); // describe data as json
            resp.getWriter().write(allCustomers.build().toString());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doPut(req, resp);*/
        try{
            Connection connection = getConnection();
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String address = req.getParameter("address");

            System.out.println("ID :"+id);
            System.out.println("NAme :"+name);
            System.out.println("Address :"+address);

            if (id ==null || id.isEmpty() && name == null || name.isEmpty() && address == null || address.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\" : \"ID is required..!\"}");
            }else{
                String query = "UPDATE customer SET name = ?, address = ? WHERE id = ?";


                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, id);

                preparedStatement.executeUpdate();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"message\" : \"Customer update successful\"}");

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = getConnection();

            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String address = req.getParameter("address");

            if (id ==null || id.isEmpty() && name == null || name.isEmpty() && address == null || address.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\" : \"ID is required..!\"}");
            }else{
                String query = "INSERT INTO customer (id, name, address) VALUES (?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, address);

                preparedStatement.executeUpdate();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"message\" : \"Customer Save successful\"}");

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doDelete(req, resp);*/
        try {
            Connection connection = getConnection();
            String id = req.getParameter("id");

            if (id ==null || id.isEmpty()){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\" : \"ID is required..!\"}");
            }else{
                String query = "DELETE FROM customer WHERE id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                resp.getWriter().write("{\"message\" : \"Customer Save successful\"}");

            }


        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}


/*
Create database Shop;

Use Shop;

Create table customer(
id varchar(5) primary key,
name varchar(30),
address varchar(50)
);

Create table item(
item_id varchar(5) primary key,
item_name varchar(30),
item_price int(10),
item_qty int(10)
);


*/