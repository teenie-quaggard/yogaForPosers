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

    public Connection connectToDB() throws ClassNotFoundException, SQLException {
        try {
            if (connection == null){
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(connectionString);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        return connection;
    }

//
//    public void addEntry(Entry entry) throws SQLException, ClassNotFoundException {
//        connectToDB();
//        try {
//            Statement statement = connection.createStatement();
//
//            String englishName = entry.getEnglishName();
//            String sanskritName = entry.getSanskritName();
//            String poseType = entry.getPoseType();
//            String healthBenefits = entry.getHealthBenefits();
//            String imgURL = entry.getImgURL();
//
//            int addEntry = statement.executeUpdate(
//                    "INSERT INTO yogaPoses "
//                            + "(englishName, sanskritName, poseType, healthBenefits, imgURL) "
//                            + "VALUES('" + englishName + "', '" + sanskritName + "', '"
//                            + poseType + "', '" + healthBenefits + "', '" + imgURL + "');");
//
//            statement.close();
//
////            PreparedStatement statement = connection.prepareStatement("INSERT" +
////                    " INTO yogaPoses VALUES(?,?,?,?,?,?)");
////            statement.setString(0, entry.getEnglishName());
////            statement.setString(1, entry.getSanskritName());
////            statement.setString(2, entry.getPoseType());
////            statement.setString(3, entry.getHealthBenefits());
////            statement.setString(4, entry.getHealthBenefits());
////            statement.setString(5, entry.getImgURL());
////            statement.execute();
////            statement.close();
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//            e.printStackTrace();
//        }
//
//    }
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

    public Entry getEntry(int id) throws SQLException, ClassNotFoundException {
        connectToDB();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT englishName, " +
                    "sanskritName, " +
                    "poseType, healthBenefits, imgURL FROM yogaPoses WHERE id" +
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
                return new Entry(englishName, sanskritName, poseType,
                        healthBenefits, imgURL);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        return null;
    }


    public void clearDB() throws SQLException, ClassNotFoundException {
        connectToDB();
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM yogaPoses;");
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public void initialiseDummyData() throws SQLException {


        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT name FROM " +
                "sqlite_master WHERE type='table' AND name='yogaPoses'");


        if(!result.next()){
            System.out.println("Building the yogaPoses table with " +
                    "prepopulated values.");
            Statement statement2 = connection.createStatement();
            statement2.execute("CREATE TABLE yogaPoses(id integer, " +
                    "englishName text, sanskritName text, poseType text, " +
                    "healthBenefits text, imgURL text, primary key(id));");

            PreparedStatement entry = connection.prepareStatement(
                    "INSERT INTO yogaPoses values(0, 'Pigeon Pose', 'Eka Pada Rajakapotasana', 'Hip opener', 'Opens hip joint', 'https://cdn.sivanaspirit.com/wp-content/uploads/2017/11/04001203/un-94-896x580.jpg');"
                );
            entry.execute();

            PreparedStatement entry2 = connection.prepareStatement("INSERT" +
                    " INTO yogaPoses values(1, 'Corpse Pose', 'Savasana'," +
                    " 'Resting pose', 'Relaxation', 'https://www.sarvyoga.com/wp-content/uploads/2016/05/Savasana-Corpse-yoga-Pose-Relaxation-Pose.jpg');");
            entry2.execute();

            }
        }
    }

