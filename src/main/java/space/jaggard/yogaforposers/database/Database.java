package space.jaggard.yogaforposers.database;

import space.jaggard.yogaforposers.entry.Entry;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private final String connectionString;
    private static Connection connection;

    public static String TEST_CONNECTION_STRING = "jdbc:sqlite:testYogaForPosers.db";
    public static String PRODUCTION_CONNECTION_STRING = "jdbc:sqlite:yogaForPosers.db";

    public Database(String connectionString){
        this.connectionString = connectionString;
    }

    public void DBSetup() throws ClassNotFoundException, SQLException {
        try {
            if (connection == null){
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(connectionString);
                createTable();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public void createTable(){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE yogaPoses(id text, " +
                    "englishName text, sanskritName text, poseType text, " +
                    "healthBenefits text, imgURL text, primary key(id));");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void addEntry(Entry entry) throws SQLException, ClassNotFoundException {
        DBSetup();
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
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public ArrayList<Entry> getEntry(String id) throws SQLException,
            ClassNotFoundException {
        DBSetup();
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
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        return entryResults;
    };


    public void clearDB() throws SQLException, ClassNotFoundException {
        DBSetup();
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE yogaPoses;");
            statement.close();
            connection.close();
            System.out.println("Connection closed.");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public int countDatabaseEntries() throws SQLException, ClassNotFoundException {
        DBSetup();
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


//
//    public ArrayList<Entry> getEntries() throws SQLException, ClassNotFoundException {
//        connectToDB();
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet results = statement.executeQuery("SELECT englishName, " +
//                    "sanskritName, poseType, healthBenefits, imgURL FROM yogaPoses");
//            ArrayList<Entry> resultList = new ArrayList<>();
//
//            while (results.next()) {
//                String englishName = results.getString("englishName");
//                String sanskritName = results.getString("sanskritName");
//                String poseType = results.getString("poseType");
//                String healthBenefits = results.getString(
//                        "healthBenefits");
//                String imgURL  = results.getString("imgURL");
//
//                Entry entry = new Entry(englishName, sanskritName, poseType,
//                        healthBenefits, imgURL);
//
//                resultList.add(0, entry);
//            }
//            statement.close();
//            return resultList;
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//            e.printStackTrace();
//        }
//        return null;
//    }


//    public void initialiseDummyData() throws SQLException {
//
//
//        Statement statement = connection.createStatement();
//        ResultSet result = statement.executeQuery("SELECT name FROM " +
//                "sqlite_master WHERE type='table' AND name='yogaPoses'");
//
//
//        if(!result.next()){
//            System.out.println("Building the yogaPoses table with " +
//                    "prepopulated values.");
//            Statement statement2 = connection.createStatement();
//            statement2.execute("CREATE TABLE yogaPoses(id integer, " +
//                    "englishName text, sanskritName text, poseType text, " +
//                    "healthBenefits text, imgURL text, primary key(id));");
//
//            PreparedStatement entry = connection.prepareStatement(
//                    "INSERT INTO yogaPoses values(0, 'Pigeon Pose', 'Eka Pada Rajakapotasana', 'Hip opener', 'Opens hip joint', 'https://cdn.sivanaspirit.com/wp-content/uploads/2017/11/04001203/un-94-896x580.jpg');"
//                );
//            entry.execute();
//
//            PreparedStatement entry2 = connection.prepareStatement("INSERT" +
//                    " INTO yogaPoses values(1, 'Corpse Pose', 'Savasana'," +
//                    " 'Resting pose', 'Relaxation', 'https://www.sarvyoga.com/wp-content/uploads/2016/05/Savasana-Corpse-yoga-Pose-Relaxation-Pose.jpg');");
//            entry2.execute();
//
//            }
//        }
