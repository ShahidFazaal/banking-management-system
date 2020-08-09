package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){
    }
    public static DAOFactory getInstance(){
        return (daoFactory == null) ? daoFactory = new DAOFactory(): daoFactory;
    }
    public <T extends SuperDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case BRANCH:
                return (T) new BranchDAOImpl();
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case TRANSACTION:
                return (T) new TransactionDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }

    }
}
