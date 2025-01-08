package lk.ijse.DTO;

/**
 * Author: vishmee
 * Date: 12/30/24
 * Time: 4:46 PM
 * Description:
 */


public class CustomerDTO {
    private String id;
    private String name;
    private String address;

    public CustomerDTO(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public CustomerDTO() {
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

}
