package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import entity.CustomEntity;

import java.sql.ResultSet;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public CustomEntity getCustomerDetails(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT C.*, B.location From Customer C INNER JOIN Branch B on C.branch = B.branchId WHERE accountNumber=? OR nic =?", id, id);
        if (resultSet.next()){
            return new CustomEntity(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getDate(7),resultSet.getString(8),resultSet.getBigDecimal(9),resultSet.getBigDecimal(11),resultSet.getString(12));
        }
        return null;
    }
}
