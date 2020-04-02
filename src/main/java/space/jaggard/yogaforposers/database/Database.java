package space.jaggard.yogaforposers.database;

import space.jaggard.yogaforposers.entry.Entry;

import java.sql.*;

public class Database {

    private static Connection connection;
    private static boolean hasData = false;
    private final String connectionString;

    public static String TEST_CONNECTION_STRING = "jdbc:sqlite:testYogaForPosers.db";
    public static String PRODUCTION_CONNECTION_STRING = "jdbc:sqlite:yogaForPosers.db";

    public Database(String connectionString){
        this.connectionString = connectionString;
    }

    public ResultSet getEntries() throws SQLException,
            ClassNotFoundException {
        if (connection == null) {
            connectToDB();
        }

        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT englishName, sanskritName, " +
                "poseType, healthBenefits, imgURL FROM yogaPoses");
    }

    public void displayEntries() {
        Database db = new Database(connectionString);
        ResultSet result;

        try {
            result = db.getEntries();
            while (result.next()) {
                System.out.println(
                        "English name: " + result.getString("englishName") + "\n"
                                + "Sanskrit name: " + result.getString("sanskritName") + "\n"
                                + "Pose Type: " + result.getString("poseType") + "\n"
                                + "Health benefits: " + result.getString("healthBenefits") + "\n"
                                + "Image URL: " + result.getString("imgURL") + "\n");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Entry getEntry(int index) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connectToDB();
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT englishName, " +
                    "sanskritName, " +
                    "poseType, healthBenefits, imgURL FROM yogaPoses WHERE id ='" + index + "'");

            boolean resultSet = result.next();

            if (resultSet) {
                String englishName = result.getString("englishName");
                String sanskritName = result.getString("sanskritName");
                String poseType = result.getString("poseType");
                String healthBenefits = result.getString(
                        "healthBenefits");

                return new Entry(englishName, sanskritName, poseType, healthBenefits);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return null;
    }



    public void connectToDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(connectionString);
//        initialise();
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
