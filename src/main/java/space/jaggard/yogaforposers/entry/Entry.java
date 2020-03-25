package space.jaggard.yogaforposers.entry;

import java.util.UUID;

public class Entry {

    String englishName;
    String sanskritName;
    String poseType;
    String healthBenefits;
    UUID id;

    public Entry(String englishName, String sanskritName, String poseType,
          String healthBenefits){
        this.englishName = englishName;
        this.sanskritName = sanskritName;
        this.poseType = poseType;
        this.healthBenefits = healthBenefits;
        this.id = UUID.randomUUID();
    }
    

    public String create(){
        return "---------------------------------------------\n" +
                "ENGLISH NAME: " + englishName + "\n" +
                "SANSKRIT NAME: " + sanskritName + "\n"+
                "POSE TYPE: " + poseType + "\n"+
                "HEALTH BENEFITS: " + healthBenefits +
                "\n---------------------------------------------\n";
    }


}
