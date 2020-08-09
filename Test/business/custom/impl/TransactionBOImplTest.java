package business.custom.impl;


import dao.custom.impl.TransactionDAOImpl;
import entity.Transaction;

class TransactionBOImplTest {
    public static void main(String[] args) throws Exception {
        TransactionDAOImpl transactionDAO = new TransactionDAOImpl();
        Transaction transaction = transactionDAO.find("A002");
    }

}