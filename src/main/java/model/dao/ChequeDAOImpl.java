package model.dao;

import model.entity.Cheque;
import model.executor.Executor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChequeDAOImpl implements DAO<Cheque, String> {

    private Executor executor;

    public ChequeDAOImpl() {
        this.executor = new Executor();
    }

    @Override
    public void create(Cheque cheque) throws SQLException {
        executor.execUpdate("insert into cheque values (?, ?::integer, ?::integer, ?::integer)",
                cheque.getServiceType(), String.valueOf(cheque.getPrice()), String.valueOf(cheque.getCount()), String.valueOf(cheque.getCost()));
    }

    @Override
    public Cheque read(String serviceType) throws SQLException {
        return executor.execQuery(result -> {
            if (!result.next())
                return null;
            return new Cheque(
                    result.getString("service_type"),
                    result.getInt("price"),
                    result.getInt("count"),
                    result.getInt("cost")
            );
        }, "select * from cheque where service_type = ?", serviceType);
    }

    @Override
    public void update(Cheque cheque) throws SQLException {
        executor.execUpdate("update cheque set price = ?::integer, count = ?::integer, cost = ?::integer where service_type = ?",
                String.valueOf(cheque.getPrice()),
                String.valueOf(cheque.getCount()),
                String.valueOf(cheque.getCost()),
                cheque.getServiceType()
        );
    }

    @Override
    public void delete(Cheque cheque) throws SQLException {
        executor.execUpdate("delete from cheque where service_type = ?", cheque.getServiceType());
    }

    public List<Cheque> readAll(Integer accountNumber) throws SQLException {
        List<Cheque> cheques = new ArrayList<>();
        return executor.execQuery(result -> {
            while (result.next()) {
                cheques.add(new Cheque(
                        result.getString("service_type"),
                        result.getInt("price"),
                        result.getInt("count"),
                        result.getInt("cost")
                ));
            }
            return cheques;
        }, "select * from cheque_book_cheque left join cheque using (service_type) where account_number = ?::integer", accountNumber.toString());
    }

}
