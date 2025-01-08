package lk.ijse.DTO;

/**
 * Author: vishmee
 * Date: 1/5/25
 * Time: 8:52 AM
 * Description:
 */


public class OrderDetailDTO {
    private String id;
    private int qty;
    private ItemDTO item;
    private OrderDTO order;

    public OrderDetailDTO(String id, int qty, ItemDTO item, OrderDTO order) {
        this.id = id;
        this.qty = qty;
        this.item = item;
        this.order = order;
    }

    public OrderDetailDTO() {
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

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }
}