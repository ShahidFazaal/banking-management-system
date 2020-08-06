package dao.custom.impl;

import dao.custom.UserDAO;
import entity.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> findAll() throws Exception {
        return null;
    }

    @Override
    public User find(String pk) throws Exception {
        return null;
    }

    @Override
    public boolean save(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String key) throws Exception {
        return false;
    }
}
