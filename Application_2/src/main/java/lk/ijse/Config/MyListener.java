/*
package lk.ijse.Config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lk.ijse.Entity.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

@WebListener
public class MyListener implements ServletContextListener {
    private SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized");
        try {
            Configuration configuration = new Configuration().configure("/lib/hibernate.cfg.xml");
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Item.class);
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(Payment.class);
            configuration.addAnnotatedClass(OrderDetail.class);
            sessionFactory =  configuration.buildSessionFactory();
            System.out.println(sessionFactory);

            FactoryConfiguration.setSessionFactory(sessionFactory);

            //sce.getServletContext().setAttribute("SessionFactory", sessionFactory);
            System.out.println("hibernate session factory initialized successfully.");

        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Hibernate SessionFactory   ",e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("shutdown hibernate session factory.");
        if (sessionFactory != null) {
            sessionFactory.close();
        }

    }
}
*/
