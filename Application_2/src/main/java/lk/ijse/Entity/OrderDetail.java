package lk.ijse.Entity;

/**
 * Author: vishmee
 * Date: 1/5/25
 * Time: 8:52 AM
 * Description:
 */

import jakarta.persistence.*;

@Entity
public class OrderDetail {
    @Id
    private String id;

    private int qty;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderDetail(String id, int qty, Item item, Order order) {
        this.id = id;
        this.qty = qty;
        this.item = item;
        this.order = order;
    }

    public OrderDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}