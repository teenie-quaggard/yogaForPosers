package space.jaggard.yogaforposers.database;

import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.testConsole.TestConsole;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {

    @Test
    void addEntry() throws SQLException, ClassNotFoundException {
        TestConsole console = new TestConsole(null);
        Database db = new Database(Database.TEST_CONNECTION_STRING, console);
        Entry entry = new Entry("Pigeon Pose", "Eka Pada Rajakapotasana", "Hip " +
                "opener", "Opens hip joint", "");
        db.connectToDB();
        db.addEntry(entry);

        assertEquals("Pigeon Pose" , db.getEntry(0).getEnglishName());
    }

    @Test
    void getEntry() throws SQLException, ClassNotFoundException {
        TestConsole console = new TestConsole(null);
        Database db = new Database(Database.TEST_CONNECTION_STRING, console);
        db.connectToDB();
        db.initialiseDummyData();
        Entry entry = db.getEntry(0);

        assertEquals("Pigeon Pose", entry.getEnglishName());
    }

    @Test
    void getEntries() throws SQLException, ClassNotFoundException {
        TestConsole console = new TestConsole(null);
        Database db = new Database(Database.TEST_CONNECTION_STRING, console);
        db.connectToDB();
        db.initialiseDummyData();
        ArrayList<Entry> entries = db.getEntries();

        assertEquals(3, entries.size());
    }
}