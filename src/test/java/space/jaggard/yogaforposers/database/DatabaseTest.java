package space.jaggard.yogaforposers.database;

import org.junit.jupiter.api.*;
import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.testConsole.TestConsole;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {


    private static Database database;
    private static Connection connection;

    @BeforeAll
    private static void connectToTestDB() {
        database = new Database(Database.TEST_CONNECTION_STRING);
        database.connect();
        database.createTable();
    }

    @AfterEach
    private void clearEntries(){
        database.clearTable();
    }

    @AfterAll
    private static void endTestDB() {
        database.closeConnection();
    }

    void addDummyEntry() {
        Entry entry = new Entry("Pigeon Pose", "Eka Pada Rajakapotasana", "Hip " +
                "opener", "Opens hip joint", "");

        database.addEntry(entry);
    }

    @Test
    void addEntry() throws SQLException, ClassNotFoundException {
        addDummyEntry();
        assertEquals(1, database.countEntries());
    }

    @Test
    void getEntry() {
        Entry entry = new Entry("Pigeon Pose", "Eka Pada Rajakapotasana", "Hip " +
                "opener", "Opens hip joint", "");
        String id = entry.getID();

        database.addEntry(entry);

        ArrayList<Entry> results = database.getEntry(id);
        String entryName = results.get(0).getEnglishName();

        assertEquals("Pigeon Pose", entryName);
    }

    @Test
    void getEntries() {
        addDummyEntry();
        addDummyEntry();

        ArrayList<Entry> entries = database.getEntries();
        assertEquals(2, entries.size());
    }

    @Test
    void clearTable() throws SQLException, ClassNotFoundException {
        addDummyEntry();
        database.clearTable();
        int zeroEntries = 0;

        assertEquals(zeroEntries, database.countEntries());
    }

    @Test
    void countEntries () throws SQLException, ClassNotFoundException {
        addDummyEntry();
        addDummyEntry();

        assertEquals(2, database.countEntries());
    }

}