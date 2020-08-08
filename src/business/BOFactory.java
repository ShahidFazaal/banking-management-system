package business;

import business.custom.impl.BranchBOImpl;
import business.custom.impl.CustomerBOImpl;
import business.custom.impl.TransactionBOImpl;
import business.custom.impl.UserBOImpl;
import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.impl.BranchDAOImpl;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.TransactionDAOImpl;
import dao.custom.impl.UserDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }
    public static BOFactory getInstance(){
        return (boFactory == null) ? boFactory = new BOFactory(): boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boType){
        switch (boType){
            case BRANCH:
                return (T) new BranchBOImpl();
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case TRANSACTION:
                return (T) new TransactionBOImpl();
            case USER:
                return (T) new UserBOImpl();
            default:
                return null;
        }

    }
}
