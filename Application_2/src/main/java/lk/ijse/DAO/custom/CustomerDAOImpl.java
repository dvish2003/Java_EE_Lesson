package lk.ijse.DAO.custom;

import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.Entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: vishmee
 * Date: 1/6/25
 * Time: 10:59 PM
 * Description:
 */
public class CustomerDAOImpl implements CustomerDAO{
    @Override
    public boolean save(Customer entity) throws Exception {
    Session session = FactoryConfiguration.getInstance().getSession();
    Transaction transaction = session.beginTransaction();
    session.save(entity);
    transaction.commit();
    session.close();
        return true;
    }

    @Override
    public boolean update(Customer entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String ID) throws Exception {
        return false;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
 List<Customer> customers = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        customers = session.createQuery("FROM Customer").list();
        transaction.commit();
        session.close();
        return customers;


    }
}
