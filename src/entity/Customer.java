package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Customer implements SuperEntity {
    private String nic;
    private String accountNumber;
    private String accountType;
    private String customerName;
    private String emailAddress;
    private String contactNumber;
    private Date dob;
    private String gender;
    private BigDecimal accountBalance;
    private String branch;
    private BigDecimal initialDeposit;

    public Customer() {
    }

    public Customer(String nic, String accountNumber, String accountType, String customerName, String emailAddress, String contactNumber, Date dob, String gender, BigDecimal accountBalance, String branch, BigDecimal initialDeposit) {
        this.nic = nic;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.customerName = customerName;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
        this.dob = dob;
        this.gender = gender;
        this.accountBalance = accountBalance;
        this.branch = branch;
        this.initialDeposit = initialDeposit;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public BigDecimal getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(BigDecimal initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "nic='" + nic + '\'' +
                ", accountNumber=" + accountNumber +
                ", accountType='" + accountType + '\'' +
                ", customerName='" + customerName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", contactNumber=" + contactNumber +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", accountBalance=" + accountBalance +
                ", branch='" + branch + '\'' +
                ", initialDeposit=" + initialDeposit +
                '}';
    }
}
