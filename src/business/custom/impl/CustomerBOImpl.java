package business.custom.impl;

import business.custom.CustomerBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.CustomerDAO;
import entity.Customer;
import util.CustomerTM;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
    @Override
    public String generateNewAccountNumber() throws Exception {

        String lastAccountNumber = customerDAO.getLastAccountNumber();
        if (lastAccountNumber == null){
            return "AC001";
        }else{
            int maxId = Integer.parseInt(lastAccountNumber.replace("AC", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "AC00" + maxId;
            } else if (maxId < 100) {
                id = "AC0" + maxId;
            } else {
                id = "AC" + maxId;
            }
            return id;
        }
    }

    @Override
    public boolean saveCustomer(String nic, String accountNumber, String accountType, String customerName, String email, String contactNumber, Date dob, String gender, BigDecimal accountBalance, String branchId, BigDecimal initialDeposit) throws Exception {
        return customerDAO.save(new Customer(nic,accountNumber,accountType,customerName,email,contactNumber,dob,gender,accountBalance,branchId,initialDeposit));
    }

    @Override
    public List<CustomerTM> getAllCustomerAccountDetails() throws Exception {
        List<Customer> allCustomerAccounts = customerDAO.findAll();
        ArrayList<CustomerTM> customerAccounts = new ArrayList<>();
        for (Object entity: allCustomerAccounts) {
            Customer customer = (Customer) entity;
            customerAccounts.add(new CustomerTM(customer.getNic(),customer.getAccountNumber(),customer.getAccountType(),customer.getCustomerName(),customer.getEmailAddress(),customer.getContactNumber(),customer.getDob(),customer.getGender(),customer.getAccountBalance(),customer.getBranch(),customer.getInitialDeposit()));
        }
        return customerAccounts;
    }
}
