package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.DTO.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Author: vishmee
 * Date: 1/6/25
 * Time: 10:58 PM
 * Description:
 */
public interface CustomerBO extends SuperBO {
    boolean save(CustomerDTO dto) throws Exception;

    boolean update(CustomerDTO dto) throws Exception;

    boolean delete(String ID) throws Exception;

    List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
}
