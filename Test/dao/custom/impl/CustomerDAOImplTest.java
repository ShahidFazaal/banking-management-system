package dao.custom.impl;


import entity.Customer;

import java.math.BigDecimal;
import java.sql.Date;

class CustomerDAOImplTest {
    public static void main(String[] args) throws Exception {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        Customer customer = customerDAO.find("AC0011");
        System.out.println(customer);
    }

}