package service;

import model.dao.CustomerDAOImpl;
import model.entity.Customer;

import javax.ejb.Stateless;
import java.sql.SQLException;

@Stateless
public class CustomerEJB {

    private CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    public Customer validateUserLogin(String login, String password) throws SQLException {
        if (customerDAO.isExist(login, password))
            return customerDAO.read(customerDAO.getByLoginPassword(login, password).getId());
        return new Customer();
    }

}
