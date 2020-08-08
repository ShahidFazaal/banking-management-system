package business.custom;

import business.SuperBO;
import util.CustomerTM;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface CustomerBO extends SuperBO {
    public String generateNewAccountNumber() throws Exception;
    public boolean saveCustomer(String nic, String accountNumber, String accountType, String customerName, String email, String contactNumber, Date dob, String gender, BigDecimal accountBalance, String branchId, BigDecimal initialDeposit) throws Exception;
    public List<CustomerTM> getAllCustomerAccountDetails() throws Exception;
}
