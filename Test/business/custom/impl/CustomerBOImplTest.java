package business.custom.impl;


import util.CustomerTM;

class CustomerBOImplTest {
    public static void main(String[] args) {
        CustomerBOImpl customerBO = new CustomerBOImpl();
        try {
            CustomerTM ac001 = customerBO.getAccount("AC00122");
            System.out.println(ac001);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}