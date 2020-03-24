package space.jaggard.yogaforposers.entry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryTest {

    @Test void createEntryGeneratesANewEntry(){
        Entry entry = new Entry("Pigeon Pose", "Eka Pada Rajakapotasana",
                "Hip opener",  "Opens the hip joint");

        assertEquals("---------------------------------------------\n" +
                        "ENGLISH NAME: Pigeon Pose\n" +
                        "SANSKRIT NAME: Eka Pada Rajakapotasana\n"+
                        "POSE TYPE: Hip opener\n"+
                        "HEALTH BENEFITS: Opens the hip joint" +
                        "\n---------------------------------------------\n",
                        entry.create());
    }

}