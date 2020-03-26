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

    public String stringify(){
        return "---------------------------------------------\n" +
                "ENGLISH NAME: " + englishName + "\n" +
                "SANSKRIT NAME: " + sanskritName + "\n"+
                "POSE TYPE: " + poseType + "\n"+
                "HEALTH BENEFITS: " + healthBenefits +
                "\n---------------------------------------------\n";
    }

    public void updateEnglishName(String input){
        englishName = input;
    }

    public void updateSanskritName(String input) { sanskritName = input; }

    public void updatePoseType(String input) { poseType = input; }

    public void updateBenefits(String input) { healthBenefits = input; }




}
