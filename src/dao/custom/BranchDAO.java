package dao.custom;

import dao.CrudDAO;
import entity.Branch;

public interface BranchDAO extends CrudDAO<Branch,String> {
    public String getLastBranchId() throws Exception;
}
