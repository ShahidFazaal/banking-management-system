package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.TransactionDAO;
import entity.Transaction;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public List<Transaction> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `Transaction`");
        ArrayList<Transaction> transactions = new ArrayList<>();
        while (rst.next()){
            transactions.add(new Transaction(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDate(4),rst.getBigDecimal(5),rst.getString(6),rst.getString(7)));
        }
        return transactions;
    }

    @Override
    public Transaction find(String pk) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `Transaction` WHERE transactionId=?", pk);
        if (rst.next()){
            return new Transaction(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDate(4),rst.getBigDecimal(5),rst.getString(6),rst.getString(7));
        }
        return null;
    }

    @Override
    public boolean save(Transaction entity) throws Exception {
        return CrudUtil.execute("INSERT INTO `Transaction` VALUES(?,?,?,?,?,?,?)",entity.getTransactionId(),entity.getAccountNumber(),entity.getTransactionType(),entity.getDate(),entity.getTransactionAmount(),entity.getUserId(),entity.getBranch());
    }

    @Override
    public boolean update(Transaction entity) throws Exception {
        return CrudUtil.execute("UPDATE `Transaction` SET accountNumber=?,transactionType=?,date=?,transactionAmount=?,userId=?,branch=? WHERE transactionId=?",entity.getAccountNumber(),entity.getTransactionType(),entity.getDate(),entity.getTransactionAmount(),entity.getUserId(),entity.getBranch(),entity.getTransactionId());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM `Transaction` WHERE transactionId =?",key);
    }
    @Override
    public List<Transaction> getTransaction(String pk) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `Transaction` WHERE accountNumber=?", pk);
        ArrayList<Transaction> transactions = new ArrayList<>();
        while (rst.next()){
            transactions.add(new Transaction(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDate(4),rst.getBigDecimal(5),rst.getString(6),rst.getString(7)));
        }
        return transactions;
    }
}
