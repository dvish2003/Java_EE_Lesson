package lk.ijse.DTO;

import java.sql.Date;

/**
 * Author: vishmee
 * Date: 1/5/25
 * Time: 8:48 AM
 * Description:
 */
public class PaymentDTO {
    private String paymentId;
    private double amount;
    private Date date;
    private OrderDTO order;

    public PaymentDTO(String paymentId, double amount, Date date, OrderDTO order) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.order = order;
    }

    public PaymentDTO() {
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

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }
}

