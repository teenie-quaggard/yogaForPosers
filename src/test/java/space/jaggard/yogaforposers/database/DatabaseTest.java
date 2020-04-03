package space.jaggard.yogaforposers.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.entry.Entry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {

    private Database database;

    @BeforeEach
    private void setUp() throws SQLException, ClassNotFoundException {
        database = new Database(Database.TEST_CONNECTION_STRING);
        database.connectToDB();
    }

    @AfterEach
    private void tearDownDatabase() throws SQLException, ClassNotFoundException {
        database.clearDB();
    }

//    @Test
//    void addEntry() throws SQLException, ClassNotFoundException {
//        Entry entry = new Entry("Pigeon Pose", "Eka Pada Rajakapotasana", "Hip " +
//                "opener", "Opens hip joint", "");
//        database.addEntry(entry);
//        Entry dbEntry = database.getEntry(0);
//        String dbEntryName = dbEntry.getEnglishName();
//
//        assertEquals("Pigeon Pose" , dbEntryName);
//    }

    @Test
    void getEntry() throws SQLException, ClassNotFoundException {
        database.initialiseDummyData();
        Entry entry = database.getEntry(0);
        String entryName = entry.getEnglishName();

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