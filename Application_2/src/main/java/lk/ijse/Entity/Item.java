package lk.ijse.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: vishmee
 * Date: 1/4/25
 * Time: 11:25 PM
 * Description:
 */
@Entity
public class Item {
    @Id
    private String itemId;

    private String itemName;
    private double itemPrice;
    private int itemQty;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders = new ArrayList<>();


    public Item(String itemId, String itemName, double itemPrice, int itemQty, List<Order> orders) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
        this.orders = orders;
    }

    public Item() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}