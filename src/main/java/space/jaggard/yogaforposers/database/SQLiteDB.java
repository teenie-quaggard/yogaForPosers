package space.jaggard.yogaforposers.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLiteDB {
    Connection connection = null;
    Statement statement = null;

    public SQLiteDB(){
         try {
             Class.forName("org.sqlite.JDBC");
             connection = DriverManager.getConnection("jdbc:sqlite:YogaForPosersDB.sqlite");
             System.out.println("DB connected!");
             connection.close();
         } catch (Exception e) {
             System.out.println("Error: " + e.getMessage());
         }
    }
}
