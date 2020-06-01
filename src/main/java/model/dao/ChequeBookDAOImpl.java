package model.dao;

import model.entity.Cheque;
import model.entity.ChequeBook;
import model.executor.Executor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChequeBookDAOImpl implements DAO<ChequeBook, Integer> {

    private Executor executor;
    private ChequeDAOImpl chequeDAO;

    public ChequeBookDAOImpl() {
        this.executor = new Executor();
        this.chequeDAO = new ChequeDAOImpl();
    }

    @Override
    public void create(ChequeBook chequeBook) throws SQLException {
        //
    }

    public void add(ChequeBook chequeBook, Integer id) throws SQLException {
        executor.execUpdate("insert into cheque_book values (?::integer, ?::integer)",
                String.valueOf(chequeBook.getAccountNumber()), id.toString());
        addConstraint(chequeBook);
    }

    @Override
    public ChequeBook read(Integer accountNumber) throws SQLException {
        return new ChequeBook(accountNumber, chequeDAO.readAll(accountNumber));
    }

    @Override
    public void update(ChequeBook chequeBook) throws SQLException {
       //
    }

    @Override
    public void delete(ChequeBook chequeBook) throws SQLException {
        executor.execUpdate("delete from cheque_book where account_number = ?::integer",
                String.valueOf(chequeBook.getAccountNumber()));
    }

    public List<ChequeBook> readAll(Integer id) throws SQLException {
        List<ChequeBook> chequeBooks = new ArrayList<>();
        return executor.execQuery(result -> {
            while (result.next()) {
                chequeBooks.add(new ChequeBook(
                        result.getInt("account_number"),
                        chequeDAO.readAll(result.getInt("account_number"))
                ));
            }
            return chequeBooks;
        }, "select * from cheque_book where customer_id = ?::integer", id.toString());
    }

    private void addConstraint(ChequeBook chequeBook) throws SQLException {
        for (Cheque cheque : chequeBook.getCheques())
            executor.execUpdate("insert into cheque_book_cheque values (?::integer, ?)",
                    String.valueOf(chequeBook.getAccountNumber()), cheque.getServiceType());
    }

}
