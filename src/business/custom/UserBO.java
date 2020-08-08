package business.custom;

import business.SuperBO;
import util.BranchTM;
import util.UserTM;

import java.sql.Date;
import java.util.List;

public interface UserBO extends SuperBO {
    public String getNewUserId() throws Exception;
    public List<UserTM> getAllUsers() throws Exception;
    public boolean saveUsers(String id, String userName, String nic, Date dob, String password, String email, String branch, String userRole) throws Exception;
    public boolean deleteUsers(String userId) throws Exception;
    public boolean UpdateUsers(String userName, String nic, Date dob, String password, String email, String branch, String userRole, String id) throws Exception;
}
