package dao.custom.impl;


import entity.Customer;

import java.math.BigDecimal;
import java.sql.Date;

class CustomerDAOImplTest {
    public static void main(String[] args) throws Exception {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        Boolean b = customerDAO.update(new Customer("951344354v","1111111111","222","2222","2222","2222", Date.valueOf("1994-05-15"),"222", BigDecimal.valueOf(222),"B001",BigDecimal.valueOf(222)));
        System.out.println(b);
    }

}