/*
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

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Establish Database Connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Application1","root","Ijse@123");
            ResultSet resultSet =connection.prepareStatement("select * from item").executeQuery();

            //create json array
            JsonArrayBuilder allItems = Json.createArrayBuilder();


            while (resultSet.next()){
                String id = resultSet.getString("item_id");
                String name = resultSet.getString("item_name");
                String price = resultSet.getString("item_price");
                String qtyOnHand = resultSet.getString("item_qtyOnHand");

                JsonObjectBuilder item = Json.createObjectBuilder();
                item.add("id", id);
                item.add("name", name);
                item.add("price", price);
                item.add("qtyOnHand", qtyOnHand);
                allItems.add(item);
            }

            resp.setContentType("application/json"); // describe data as json
            resp.getWriter().write(allItems.build().toString());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Application1","root","Ijse@123");

            String id = req.getParameter("item_id");
            String name = req.getParameter("item_name");
            String price = req.getParameter("item_price");
            String qtyOnHand = req.getParameter("item_qtyOnHand");

            if (id == null || id.isEmpty() && name == null || name.isEmpty() && price == null || price.isEmpty() && qtyOnHand == null || qtyOnHand.isEmpty()){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\" : \"ID is required..!\"}");
            }else {
                String query = "INSERT INTO item (item_id, item_name, item_price, item_qtyOnHand) VALUES (?, ?, ?, ?)";

                PreparedStatement pstm = connection.prepareStatement(query);
                pstm.setString(1,id);
                pstm.setString(2,name);
                pstm.setString(3,price);
                pstm.setString(4,qtyOnHand);

                pstm.executeUpdate();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"message\" : \"Customer Save successful\"}");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Application1","root","Ijse@123");

            String id = req.getParameter("item_id");
            String name = req.getParameter("item_name");
            String price = req.getParameter("item_price");
            String qtyOnHand = req.getParameter("item_qtyOnHand");

            System.out.println("ID :" + id);
            System.out.println("Name :" + name);
            System.out.println("Price :" + price);
            System.out.println("Qty On Hand :" + qtyOnHand);

            if (id ==null || id.isEmpty() && name == null || name.isEmpty() && price == null || price.isEmpty() && qtyOnHand == null || qtyOnHand.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\" : \"ID is required..!\"}");
            }else{
                String query = "UPDATE item SET item_name = ?, item_price = ?, item_qtyOnHand = ? WHERE item_id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, price);
                preparedStatement.setString(2, qtyOnHand);
                preparedStatement.setString(3, id);

                preparedStatement.executeUpdate();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"message\" : \"Customer update successful\"}");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Application1","root","Ijse@123");

            String id = req.getParameter("item_id");

            if (id ==null || id.isEmpty()){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\" : \"ID is required..!\"}");
            }else{
                String query = "DELETE FROM item WHERE item_id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                resp.getWriter().write("{\"message\" : \"Item Save successful\"}");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}*/
