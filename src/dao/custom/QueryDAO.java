package dao.custom;

import dao.SuperDAO;
import entity.CustomEntity;

public interface QueryDAO extends SuperDAO {
    public CustomEntity getCustomerDetails(String id) throws Exception;
}
