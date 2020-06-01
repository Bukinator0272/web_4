package model.dao;

import java.sql.SQLException;

public interface DAO<Entity, Key> {

    void create(Entity entity) throws SQLException;

    Entity read(Key key) throws SQLException ;

    void update(Entity entity) throws SQLException ;

    void delete(Entity entity) throws SQLException ;

}
