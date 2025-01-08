package lk.ijse.Config;

import lk.ijse.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure("/lib/hibernate.cfg.xml");

        // Add annotated classes
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(OrderDetail.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ?
                factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
