package lk.ijse.DAO;

import lk.ijse.DAO.custom.CustomerDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DaoType {
        Customer, Item, Payment, Order, Order_Detail
    }

    public SuperDAO getDAO(DaoType daoType) {
        switch (daoType) {
            case Customer:
                return new CustomerDAOImpl();
            /*case Student:
                return new StudentDAOImpl();
            case Payment:
                return new PaymentDAOImpl();
            case Course:
                return new CourseDAOImpl();
            case Student_Course:
                return new Student_CourseDAOImpl();
                case Login:
                    return new LoginDAOImpl();*/
            default:
                return null;
        }
    }

}
