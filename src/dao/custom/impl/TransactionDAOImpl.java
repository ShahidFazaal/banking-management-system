package dao.custom.impl;

import dao.custom.TransactionDAO;
import entity.Transaction;

import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public List<Transaction> findAll() throws Exception {
        return null;
    }

    @Override
    public Transaction find(String pk) throws Exception {
        return null;
    }

    @Override
    public boolean save(Transaction entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Transaction entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String key) throws Exception {
        return false;
    }
}
