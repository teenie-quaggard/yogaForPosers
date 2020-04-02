package space.jaggard.yogaforposers.database;

import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.entry.Entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {

    @Test
    void addEntry(){
        Database db = new Database(Database.TEST_CONNECTION_STRING);

//        db.createEntry(entry);
//
//        assertEquals( , );
    }

    @Test
    void getEntry() throws SQLException, ClassNotFoundException {
        Database db = new Database(Database.TEST_CONNECTION_STRING);
        db.connectToDB();
        db.initialiseDummyData();
        Entry entry = db.getEntry(0);

        assertEquals("Pigeon Pose", entry.getEnglishName());
    }

    @Test
    void getEntries() throws SQLException, ClassNotFoundException {
        Database db = new Database(Database.TEST_CONNECTION_STRING);
        db.connectToDB();
        db.initialiseDummyData();
        ArrayList<Entry> entries = db.getEntries();

        assertEquals(2, entries.size());
    }
}