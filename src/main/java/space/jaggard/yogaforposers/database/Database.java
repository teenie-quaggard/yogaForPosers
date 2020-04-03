package space.jaggard.yogaforposers.database;

import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.Console;
import space.jaggard.yogaforposers.io.IO;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static Connection connection;
    private static boolean hasData = false;
    private final String connectionString;
    IO ioType;

    public static String TEST_CONNECTION_STRING = "jdbc:sqlite:testYogaForPosers.db";
    public static String PRODUCTION_CONNECTION_STRING = "jdbc:sqlite:yogaForPosers.db";

    public Database(){
        this(PRODUCTION_CONNECTION_STRING, new Console());
    }

    public Database(String connectionString, IO ioType){
        this.connectionString = connectionString;
        this.ioType = ioType;
    }

    public void addEntry(Entry entry) throws SQLException,
            ClassNotFoundException {
        if (connection == null) {
            connectToDB();
        }
        try {
            Statement statement = connection.createStatement();

            String englishName = entry.getEnglishName();
            String sanskritName = entry.getSanskritName();
            String poseType = entry.getPoseType();
            String healthBenefits = entry.getHealthBenefits();
            String imgURL = entry.getImgURL();

            int addEntry = statement.executeUpdate(
                    "INSERT INTO yogaPoses "
                            + "(englishName, sanskritName, poseType, healthBenefits, imgURL) "
                            + "VALUES('" + englishName + "', '" + sanskritName + "', '"
                            + poseType + "', '" + healthBenefits + "', '" + imgURL + "');");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    public ArrayList<Entry> getEntries() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connectToDB();
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT englishName, " +
                    "sanskritName, poseType, healthBenefits, imgURL FROM " +
                    "yogaPoses;");
            ArrayList<Entry> resultList = new ArrayList<>();

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
            return resultList;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public Entry getEntry(int id) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connectToDB();
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT englishName, " +
                    "sanskritName, poseType, healthBenefits, imgURL FROM yogaPoses WHERE id ='" + id + "'");

            boolean resultSet = result.next();

            if (resultSet) {
                String englishName = result.getString("englishName");
                String sanskritName = result.getString("sanskritName");
                String poseType = result.getString("poseType");
                String healthBenefits = result.getString(
                        "healthBenefits");
                String imgURL  = result.getString("imgURL");

                return new Entry(englishName, sanskritName, poseType,
                        healthBenefits, imgURL);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public void connectToDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(connectionString);
    }

    public void initialiseDummyData() throws SQLException {
        if (!hasData) {
            hasData = true;

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
}
