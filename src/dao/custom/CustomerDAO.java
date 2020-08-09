package dao.custom;

import dao.CrudDAO;
import entity.Customer;

import java.math.BigDecimal;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public String getLastAccountNumber() throws Exception;
    public boolean updateAccountBalance(String accountNumber, BigDecimal depositAmount) throws Exception;
}
