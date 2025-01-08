package lk.ijse.DTO;

/**
 * Author: vishmee
 * Date: 1/5/25
 * Time: 8:46 AM
 * Description:
 */
import java.sql.Date;


public class OrderDTO {
    private String orderId;
    private Date orderDate;
    private CustomerDTO customerDTO;
    private PaymentDTO payment;

    public OrderDTO(String orderId, Date orderDate, CustomerDTO customerDTO, PaymentDTO payment) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerDTO = customerDTO;
        this.payment = payment;
    }

    public OrderDTO() {

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

    public CustomerDTO getCustomer() {
        return customerDTO;
    }

    public void setCustomer(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }


}
