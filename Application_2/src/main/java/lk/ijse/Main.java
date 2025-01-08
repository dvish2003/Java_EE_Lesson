package lk.ijse;

import lk.ijse.Config.FactoryConfiguration;
import lk.ijse.Entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Author: vishmee
 * Date: 1/7/25
 * Time: 1:58 AM
 * Description: 
 */public class Main {
    public Main(Customer customerDTO) {

       /* Session sessions = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = sessions.beginTransaction();
        sessions.save(customerDTO);
        transaction.commit();
        sessions.close();*/

    }

    public static void main(String[] args) {
    }
}
