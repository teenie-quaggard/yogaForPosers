package space.jaggard.yogaforposers.database;

import java.sql.*;

public class ProductionDB implements Database {

    private static Connection connection;
    private static boolean hasData = false;

    @Override
    public ResultSet displayEntries() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connectToDB();
        }

        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT englishName, sanskritName, " +
                "poseType, healthBenefits, imgURL FROM yogaPoses");
    };

    @Override
    public void connectToDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:yogaForPosers.db");
        initialise();
    }

    @Override
    public void initialise() throws SQLException {
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
