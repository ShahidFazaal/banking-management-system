package dao.custom.impl;

import dao.custom.CustomerDAO;
import entity.Customer;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> findAll() throws Exception {
        return null;
    }

    @Override
    public Customer find(String pk) throws Exception {
        return null;
    }

    @Override
    public boolean save(Customer entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Customer entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String key) throws Exception {
        return false;
    }
}
