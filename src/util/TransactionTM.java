package util;

import entity.SuperEntity;

import java.math.BigDecimal;
import java.sql.Date;

public class TransactionTM implements SuperEntity {
    private int transactionId;
    private String accountNumber;
    private String transactionType;
    private Date date;
    private BigDecimal transactionAmount;
    private String userId;
    private String branch;

    public TransactionTM() {
    }

    public TransactionTM(int transactionId, String accountNumber, String transactionType, Date date, BigDecimal transactionAmount, String userId, String branch) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.date = date;
        this.transactionAmount = transactionAmount;
        this.userId = userId;
        this.branch = branch;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", accountNumber=" + accountNumber +
                ", transactionType='" + transactionType + '\'' +
                ", date=" + date +
                ", transactionAmount=" + transactionAmount +
                ", userId='" + userId + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
