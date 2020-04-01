package space.jaggard.yogaforposers.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {
    ResultSet displayEntries() throws ClassNotFoundException, SQLException;
}
