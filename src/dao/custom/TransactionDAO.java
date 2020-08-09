package dao.custom;

import dao.CrudDAO;
import entity.Transaction;

import java.util.List;

public interface TransactionDAO extends CrudDAO<Transaction,String> {
    public List<Transaction> getTransaction(String pk) throws Exception;
}
