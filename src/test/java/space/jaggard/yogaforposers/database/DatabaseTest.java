package space.jaggard.yogaforposers.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.entry.Entry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {

    private Database database;

    @BeforeEach
    private void setUp() throws SQLException, ClassNotFoundException {
        database = new Database(Database.TEST_CONNECTION_STRING);
        System.out.println("Database initialised");
    }

    @AfterEach
    private void tearDownDatabase() throws SQLException, ClassNotFoundException {
        System.out.println("Wiping test database.");
        database.clearDB();
    }

    @Test
    void addEntry() throws SQLException, ClassNotFoundException {
        Entry entry = new Entry("Pigeon Pose", "Eka Pada Rajakapotasana", "Hip " +
                "opener", "Opens hip joint", "");

        database.addEntry(entry);

        assertEquals(1, database.countDatabaseEntries());
    }

    @Test
    void getEntry() throws SQLException, ClassNotFoundException {
        Entry entry = new Entry("Pigeon Pose", "Eka Pada Rajakapotasana", "Hip " +
                "opener", "Opens hip joint", "");
        String id = entry.getID();

        database.addEntry(entry);

        ArrayList<Entry> results = database.getEntry(id);
        String entryName = results.get(0).getEnglishName();

        assertEquals("Pigeon Pose", entryName);
    }
//
//    @Test
//    void getEntries() throws SQLException, ClassNotFoundException {
//
//        database.initialiseDummyData();
//        ArrayList<Entry> entries = database.getEntries();
//
//        assertEquals(2, entries.size());
//    }

//    @Test
//    void clearDB() throws SQLException, ClassNotFoundException {
//        Database db = new Database(Database.TEST_CONNECTION_STRING);
//        db.connectToDB();
//        db.initialiseDummyData();
//        ArrayList<Entry> entries = db.getEntries();
//
//        db.clearDB();
//
//        assertEquals(0, entries.size());
//    }
}