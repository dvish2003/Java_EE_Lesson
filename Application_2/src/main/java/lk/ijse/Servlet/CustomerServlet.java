package lk.ijse.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.CustomerBO;
import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.DTO.CustomerDTO;
import lk.ijse.Entity.Customer;
import lk.ijse.Main;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Author: vishmee
 * Date: 12/30/24
 * Time: 7:43 PM
 * Description:
 */
@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Customer);

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Shop", "root", "Ijse@123");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Shop","root","Ijse@123");
            ResultSet resultSet =connection.prepareStatement("select * from Customer").executeQuery();

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
        try{
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
                CustomerDTO customerDTO = new CustomerDTO(id, name,address);
                customerBO.update(customerDTO);


                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"message\" : \"Customer update successful\"}");

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String address = req.getParameter("address");

            System.out.println(id);
            System.out.println(name);
            System.out.println(address);
            Customer customerDTO = new Customer();
            customerDTO.setId(id);
            customerDTO.setName(name);
            customerDTO.setAddress(address);


            if (id ==null || id.isEmpty() && name == null || name.isEmpty() && address == null || address.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\" : \"ID is required..!\"}");
            }else{
                /*boolean isSave = customerBO.save(customerDTO);
                System.out.println(id);
                System.out.println(name);
                System.out.println(address);
                if(isSave){
                    System.out.println("======================================================");
                    System.out.println(id);
                    System.out.println(name);
                    System.out.println(address);
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                    resp.getWriter().write("{\"message\" : \"Customer Save successful\"}");
                }else{
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                    resp.getWriter().write("{\"error\" : \"Customer already exists\"}");*/
                Session session = FactoryConfiguration.getInstance().getSession();
                Transaction transaction = session.beginTransaction();
                session.save(customerDTO);
                transaction.commit();
                session.close();


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*super.doDelete(req, resp);*/
        try {
            String id = req.getParameter("id");

            if (id ==null || id.isEmpty()){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\" : \"ID is required..!\"}");
            }else{
                customerBO.delete(id);

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
-- Create the Shop database
CREATE DATABASE Shop;

-- Use the Shop database
USE Shop;

-- Create the Customer table
CREATE TABLE customer (
    id VARCHAR(5) PRIMARY KEY,  -- Corrected column name to match references
    name VARCHAR(30),
    address VARCHAR(50)
);

-- Create the Item table
CREATE TABLE item (
    item_id VARCHAR(5) PRIMARY KEY,
    item_name VARCHAR(30),
    item_price INT,
    item_qty INT
);

-- Create the Payment table
CREATE TABLE payment (
    payment_id VARCHAR(10) PRIMARY KEY,
    payment_amount INT NOT NULL,
    payment_date DATE NOT NULL
);

-- Create the Order table
CREATE TABLE orders (  -- Changed table name to 'orders' since 'order' is a reserved keyword in SQL
    order_id VARCHAR(10) PRIMARY KEY,
    id VARCHAR(5) NOT NULL,  -- Updated to match the length of customer_id in the customer table
    payment_id VARCHAR(10) NOT NULL,
    order_place_date DATE NOT NULL,  -- Corrected column name for clarity and consistency
    FOREIGN KEY (id) REFERENCES customer(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (payment_id) REFERENCES payment(payment_id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Create the OrderDetails table
CREATE TABLE order_details (  -- Changed table name to snake_case for consistency
    item_id VARCHAR(5) NOT NULL,  -- Updated to match the length of item_id in the item table
    order_id VARCHAR(10) NOT NULL,
    qty INT NOT NULL,
    PRIMARY KEY (item_id, order_id),  -- Added composite primary key
    FOREIGN KEY (item_id) REFERENCES item(item_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON UPDATE CASCADE ON DELETE CASCADE
);


*/