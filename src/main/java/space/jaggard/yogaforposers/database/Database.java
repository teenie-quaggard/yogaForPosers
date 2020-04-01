package space.jaggard.yogaforposers.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {
    void initialise() throws SQLException;
    void connectToDB() throws ClassNotFoundException, SQLException;
    ResultSet displayEntries() throws ClassNotFoundException, SQLException;
}
