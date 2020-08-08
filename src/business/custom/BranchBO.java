package business.custom;

import business.SuperBO;
import util.BranchTM;

import java.util.List;

public interface BranchBO extends SuperBO {
    public List<BranchTM> getAllBranches() throws Exception;
    public boolean saveBranches(String id, String location, String phone, String email) throws Exception;
    public  String getNewBranchId() throws Exception;
    public boolean deleteBranch(String branchId) throws Exception;
    public boolean UpdateBranch(String locationText, String phone, String email,String branchId) throws Exception;
}
