package dao.custom;

import dao.CrudDAO;
import entity.User;

public interface UserDAO extends CrudDAO<User,String> {
    public String getLastUserId() throws Exception;
}
