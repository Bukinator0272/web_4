package model.dao;

import model.entity.ChequeBook;
import model.entity.Customer;
import model.executor.Executor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements DAO<Customer, Integer> {

    private Executor executor;
    private ChequeBookDAOImpl chequeBookDAO;

    public CustomerDAOImpl() {
        this.executor = new Executor();
        this.chequeBookDAO = new ChequeBookDAOImpl();
    }

    @Override
    public void create(Customer customer) throws SQLException {
        executor.execUpdate("insert into customer values (?::integer, ?, ?, ?)",
                String.valueOf(customer.getId()), customer.getName(), customer.getLogin(), customer.getPassword());
        //addConstraint(customer);
    }

    @Override
    public Customer read(Integer id) throws SQLException {
        return executor.execQuery(result -> {
            if (!result.next())
                return null;
            return new Customer(
                    result.getInt("customer_id"),
                    result.getString("name"),
                    result.getString("login"),
                    result.getString("password"),
                    chequeBookDAO.readAll(id)
            );
        }, "select * from customer where customer_id = ?::integer", String.valueOf(id));
    }

    @Override
    public void update(Customer customer) throws SQLException {
        executor.execUpdate("update customer set name = ?, login = ?, password = ? where customer_id = ?::integer",
                customer.getName(), customer.getLogin(), customer.getPassword(), String.valueOf(customer.getId()));
    }

    @Override
    public void delete(Customer customer) throws SQLException {
        executor.execUpdate("delete from customer where customer_id = ?::integer", String.valueOf(customer.getId()));
    }

    public List<Customer> readAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        return executor.execQuery("select * from customer", result -> {
            while (result.next()) {
                customers.add(new Customer(
                        result.getInt("customer_id"),
                        result.getString("name"),
                        result.getString("login"),
                        result.getString("password"),
                        chequeBookDAO.readAll(result.getInt("customer_id"))
                ));
            }
            return customers;
        });
    }

    public boolean isExist(String login, String password) throws SQLException {
        return executor.execQuery(result -> {
            return result.next();
        }, "select * from customer where login = ? and password = ?", login, password);
    }

    private void addConstraint(Customer customer) throws SQLException {
        for (ChequeBook chequeBook : customer.getChequeBooks()) {
            executor.execUpdate("insert into cheque_book values (?::integer, ?::integer)",
                    String.valueOf(chequeBook.getAccountNumber()), String.valueOf(customer.getId()));
        }
    }

    public Customer getByLoginPassword(String login, String password) throws SQLException {
        return executor.execQuery(result -> {
            if (result.next()) {
                return new Customer(result.getInt("customer_id"),
                        result.getString("name"),
                        result.getString("login"),
                        result.getString("password"));
            }
            return null;
        }, "select * from customer where login = ? and password = ?", login, password);
    }

}
