package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class CustomEntity implements SuperEntity {
    private String nic;
    private String accountNumber;
    private String accountType;
    private String customerName;
    private String emailAddress;
    private String contactNumber;
    private Date dob;
    private String gender;
    private BigDecimal accountBalance;
    private BigDecimal initialDeposit;
    private String location;

    public CustomEntity() {
    }

    public CustomEntity(String nic, String accountNumber, String accountType, String customerName, String emailAddress, String contactNumber, Date dob, String gender, BigDecimal accountBalance, BigDecimal initialDeposit, String location) {
        this.nic = nic;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.customerName = customerName;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
        this.dob = dob;
        this.gender = gender;
        this.accountBalance = accountBalance;
        this.initialDeposit = initialDeposit;
        this.location = location;
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

    public BigDecimal getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(BigDecimal initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "nic='" + nic + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", customerName='" + customerName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", accountBalance=" + accountBalance +
                ", initialDeposit=" + initialDeposit +
                ", location='" + location + '\'' +
                '}';
    }
}
