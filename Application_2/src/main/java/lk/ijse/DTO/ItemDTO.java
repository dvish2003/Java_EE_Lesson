package lk.ijse.DTO;


/**
 * Author: vishmee
 * Date: 1/4/25
 * Time: 11:25 PM
 * Description:
 */
public class ItemDTO {

    private String itemId;
    private String itemName;
    private double itemPrice;
    private int itemQty;


    public ItemDTO(String itemId, String itemName, double itemPrice, int itemQty) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
    }

    public ItemDTO() {
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

}