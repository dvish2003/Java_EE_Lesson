package lk.ijse.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: vishmee
 * Date: 12/30/24
 * Time: 4:46 PM
 * Description:
 */


@Entity
public class Customer {
    @Id
    private String id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Customer(String id, String name, String address, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.orders = orders;
    }

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
