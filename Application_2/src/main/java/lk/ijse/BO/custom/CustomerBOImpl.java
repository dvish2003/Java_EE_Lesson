package lk.ijse.BO.custom;

import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.CustomerDAO;
import lk.ijse.DTO.CustomerDTO;
import lk.ijse.Entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: vishmee
 * Date: 1/6/25
 * Time: 10:59 PM
 * Description:
 */
public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Customer);
    @Override
    public boolean save(CustomerDTO dto) throws Exception {
        Customer customer = new Customer(dto.getId(), dto.getName(), dto.getAddress(), new ArrayList<>());
        return customerDAO.save(customer);
    }

    @Override
    public boolean update(CustomerDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String ID) throws Exception {
        return false;
    }

    @Override
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Customer> customers = customerDAO.getAll();
        List<CustomerDTO> dtoList = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(
             customer.getId(),
             customer.getName(),
             customer.getAddress()

     );
            dtoList.add(customerDTO);
        }
        return dtoList;
    }
}
