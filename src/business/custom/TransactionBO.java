package business.custom;

import business.SuperBO;
import util.TransactionTM;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface TransactionBO extends SuperBO {
    public boolean doDeposit(TransactionTM transaction) throws Exception;
    public List<TransactionTM> getAllTransaction() throws Exception;
    public List<TransactionTM> getTransactions(String accountNumber) throws Exception;
}

