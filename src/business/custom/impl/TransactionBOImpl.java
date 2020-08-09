package business.custom.impl;

import business.custom.TransactionBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.CustomerDAO;
import dao.custom.TransactionDAO;
import db.DBConnection;
import entity.Customer;
import entity.Transaction;
import util.CustomerTM;
import util.TransactionTM;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionBOImpl implements TransactionBO {
    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
    TransactionDAO transactionDAO = DAOFactory.getInstance().getDAO(DAOType.TRANSACTION);


    @Override
    public boolean doDeposit(TransactionTM transaction) throws Exception {
        Connection connection=null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean result = customerDAO.updateAccountBalance(transaction.getAccountNumber(), transaction.getTransactionAmount());
            if (result == false) {
                connection.rollback();
                return false;
            }
            boolean result2 = transactionDAO.save(new Transaction(transaction.getTransactionId(), transaction.getAccountNumber(), transaction.getTransactionType(), transaction.getDate(), transaction.getTransactionAmount(), transaction.getUserId(), transaction.getBranch()));
            if (!result2) {
                connection.rollback();
                return false;
            }
            connection.commit();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<TransactionTM> getAllTransaction() throws Exception {
        List<Transaction> allTransaction = transactionDAO.findAll();
        ArrayList<TransactionTM> transactions = new ArrayList<>();
        for (Object entity: allTransaction) {
            Transaction transaction = (Transaction) entity;
            transactions.add(new TransactionTM(transaction.getTransactionId(),transaction.getAccountNumber(),transaction.getTransactionType(),transaction.getDate(),transaction.getTransactionAmount(),transaction.getUserId(),transaction.getBranch()));
        }
        return transactions;
    }

    @Override
    public List<TransactionTM> getTransactions(String accountNumber) throws Exception {
        List<Transaction> allTransaction = transactionDAO.getTransaction(accountNumber);
        ArrayList<TransactionTM> transactions = new ArrayList<>();
        for (Object entity: allTransaction) {
            Transaction transaction = (Transaction) entity;
            transactions.add(new TransactionTM(transaction.getTransactionId(),transaction.getAccountNumber(),transaction.getTransactionType(),transaction.getDate(),transaction.getTransactionAmount(),transaction.getUserId(),transaction.getBranch()));
        }
        return transactions;
    }


}
