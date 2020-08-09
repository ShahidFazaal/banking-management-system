package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()){
            customers.add(new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getDate(7),rst.getString(8),rst.getBigDecimal(9),rst.getString(10),rst.getBigDecimal(11)));
        }
        return customers;
    }

    @Override
    public Customer find(String pk) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer WHERE accountNumber=? OR nic =?",pk,pk);
        if (rst.next()){
            return new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getDate(7),rst.getString(8),rst.getBigDecimal(9),rst.getString(10),rst.getBigDecimal(11));

        }
        return null;
    }

    @Override
    public boolean save(Customer entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?,?,?)",entity.getNic(),entity.getAccountNumber(),entity.getAccountType(),entity.getCustomerName(),entity.getEmailAddress(),entity.getContactNumber(),entity.getDob(),entity.getGender(),entity.getAccountBalance(),entity.getBranch(),entity.getInitialDeposit());
    }

    @Override
    public boolean update(Customer entity) throws Exception {
        return CrudUtil.execute("UPDATE Customer SET nic=?,accountType=?,customerName=?,emailAddress=?,contactNumber=?,dob=?,gender=?,accountBalance=?,branch=?,initialDeposit=? WHERE accountNumber=? OR nic=?",entity.getNic(), entity.getAccountType(),entity.getCustomerName(),entity.getEmailAddress(),entity.getContactNumber(), entity.getDob(),entity.getGender(),entity.getAccountBalance(),entity.getBranch(),entity.getInitialDeposit(),entity.getAccountNumber(),entity.getNic());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM Customer WHERE accountNumber=? OR nic =?",key,key);
    }

    @Override
    public String getLastAccountNumber() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT accountNumber FROM Customer ORDER BY accountNumber DESC LIMIT 1");
        if (rst.next()){
            return rst.getString(1);
        }else {
            return null;
        }
    }

    @Override
    public boolean updateAccountBalance(String accountNumber, BigDecimal depositAmount) throws Exception {
        return CrudUtil.execute("UPDATE Customer SET accountBalance=accountBalance+? WHERE accountNumber=?",depositAmount,accountNumber);
    }
}
