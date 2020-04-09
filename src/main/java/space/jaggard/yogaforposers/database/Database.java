package space.jaggard.yogaforposers.database;

import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.Console;
import space.jaggard.yogaforposers.io.IO;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private final String connectionString;
    private static Connection connection;
    IO ioType;

    public static String TEST_CONNECTION_STRING = "jdbc:sqlite:testYogaForPosers.db";
    public static String PRODUCTION_CONNECTION_STRING = "jdbc:sqlite:yogaForPosers.db";


    public Database(){
        this(PRODUCTION_CONNECTION_STRING);
    }

    public Database(String connectionString){
        this.connectionString = connectionString;
    }

    public void connect() {
        try {
            if (connection == null){
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(connectionString);
            }
        } catch (Exception e) {
            ioType.print("Error: " + e);
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS yogaPoses(id " +
                    "text, " +
                    "englishName text, sanskritName text, poseType text, " +
                    "healthBenefits text, imgURL text, primary key(id));");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addEntry(Entry entry) {
        try {
            Statement statement = connection.createStatement();

            String id = entry.getID();
            String englishName = entry.getEnglishName();
            String sanskritName = entry.getSanskritName();
            String poseType = entry.getPoseType();
            String healthBenefits = entry.getHealthBenefits();
            String imgURL = entry.getImgURL();

            int addEntry = statement.executeUpdate(
                    "INSERT INTO yogaPoses (id, englishName, sanskritName, poseType, healthBenefits, imgURL) "
                            + "VALUES('" + id + "', '" + englishName + "', '" +
                            sanskritName + "', '" + poseType + "', '" + healthBenefits + "', '" + imgURL + "');");
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Entry> getEntry(String id) {
        ArrayList<Entry> entryResults = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT englishName, " +
                    "sanskritName, poseType, healthBenefits, imgURL FROM yogaPoses WHERE id" +
                    " ='" + id + "';");

            boolean resultSet = result.next();

            if (resultSet) {
                String englishName = result.getString("englishName");
                String sanskritName = result.getString("sanskritName");
                String poseType = result.getString("poseType");
                String healthBenefits = result.getString(
                        "healthBenefits");
                String imgURL  = result.getString("imgURL");

                statement.close();
                Entry entry = new Entry(englishName, sanskritName, poseType,
                        healthBenefits, imgURL);
                entryResults.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entryResults;
    };


    public ArrayList<Entry> getEntries()  {
        ArrayList<Entry> resultList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT englishName, " +
                    "sanskritName, poseType, healthBenefits, imgURL FROM yogaPoses");
            while (results.next()) {
                String englishName = results.getString("englishName");
                String sanskritName = results.getString("sanskritName");
                String poseType = results.getString("poseType");
                String healthBenefits = results.getString(
                        "healthBenefits");
                String imgURL  = results.getString("imgURL");

                Entry entry = new Entry(englishName, sanskritName, poseType,
                        healthBenefits, imgURL);

                resultList.add(0, entry);
            }
            statement.close();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public void clearTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM yogaPoses;");
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try{
            connection.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int countEntries() throws SQLException, ClassNotFoundException {
        int numberOfEntries = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM" +
                    " yogaPoses");
            numberOfEntries = result.getInt(1);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfEntries;
    }

}
