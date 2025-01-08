package lk.ijse.Entity;

/**
 * Author: vishmee
 * Date: 1/5/25
 * Time: 8:46 AM
 * Description:
 */

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`Order`") // Escape the table name
public class Order {
    @Id
    private String orderId;

    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToMany
    private List<Item> items = new ArrayList<>();

    public Order(String orderId, Date orderDate, Customer customer, Payment payment, List<Item> items) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.payment = payment;
        this.items = items;
    }

    public Order() {

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
