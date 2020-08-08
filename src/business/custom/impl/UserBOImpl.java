package business.custom.impl;

import business.custom.UserBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.UserDAO;
import entity.User;
import util.UserTM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);

    @Override
    public String getNewUserId() throws Exception {
        ;
        String lastUserId = userDAO.getLastUserId();
        if (lastUserId == null){
            return "U001";
        }else{
            int maxId = Integer.parseInt(lastUserId.replace("U", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "U00" + maxId;
            } else if (maxId < 100) {
                id = "U0" + maxId;
            } else {
                id = "U" + maxId;
            }
            return id;
        }
    }

    @Override
    public List<UserTM> getAllUsers() throws Exception {
        List<User> allUsers = userDAO.findAll();
        ArrayList<UserTM> users = new ArrayList<>();
        for (Object entity : allUsers) {
            User user = (User) entity;
            users.add(new UserTM(user.getUserId(),user.getUserName(),user.getNic(),user.getDob(),user.getPassword(),user.getEmailAddress(),user.getBranch(),user.getUserRole()));
        }
        return users;
    }

    @Override
    public boolean saveUsers(String id, String userName, String nic, Date dob, String password, String email, String branch, String userRole) throws Exception {
        return userDAO.save(new User(id,userName,nic,dob,password,email,branch,userRole));
    }

    @Override
    public boolean deleteUsers(String userId) throws Exception {
        return userDAO.delete(userId);
    }

    @Override
    public boolean UpdateUsers(String userName, String nic, Date dob, String password, String email, String branch, String userRole, String id) throws Exception {
        return userDAO.update(new User(id,userName,nic,dob,password,email,branch,userRole));
    }
}
