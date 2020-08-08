package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.UserDAO;
import entity.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `User`");
        ArrayList<User> users = new ArrayList<>();
        while (rst.next()){
            users.add(new User(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDate(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8)));
        }
        return users;
    }

    @Override
    public User find(String pk) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM `User` WHERE userId=?",pk);
        if (rst.next()){
            return new User(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDate(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getString(8));
        }
        return null;
    }

    @Override
    public boolean save(User entity) throws Exception {
        return CrudUtil.execute("INSERT INTO `User` VALUES (?,?,?,?,?,?,?,?)",entity.getUserId(),entity.getUserName(),entity.getNic(),entity.getDob(),entity.getPassword(),entity.getEmailAddress(),entity.getBranch(),entity.getUserRole());
    }

    @Override
    public boolean update(User entity) throws Exception {
        return CrudUtil.execute("UPDATE `User` SET username=?,nic=?,dob=?,password=?,emailAddress=?,branch=?,userRole=? WHERE userId=?",entity.getUserName(),entity.getNic(),entity.getDob(),entity.getPassword(),entity.getEmailAddress(),entity.getBranch(),entity.getUserRole(),entity.getUserId());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM `User` WHERE userId=?",key);
    }

    @Override
    public String getLastUserId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT userId FROM `User` ORDER BY userId DESC LIMIT 1");
        if (resultSet.next()){
            return resultSet.getString(1);
        }else {
            return null;
        }
    }
}
