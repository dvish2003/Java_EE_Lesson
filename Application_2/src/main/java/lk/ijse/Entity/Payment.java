package lk.ijse.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.sql.Date;

/**
 * Author: vishmee
 * Date: 1/5/25
 * Time: 8:48 AM
 * Description:
 */
@Entity
public class Payment {
    @Id
    private String paymentId;

    private double amount;
    private Date date;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Payment(String paymentId, double amount, Date date,Order order) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.order = order;
    }

    public Payment() {
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

