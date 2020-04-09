package space.jaggard.yogaforposers.entry;

import java.util.UUID;

public class Entry {

    String englishName;
    String sanskritName;
    String poseType;
    String healthBenefits;
    String imgURL;
    UUID id;

    public Entry(String englishName, String sanskritName, String poseType,
          String healthBenefits, String imgURL){
        this.id = UUID.randomUUID();
        this.englishName = englishName;
        this.sanskritName = sanskritName;
        this.poseType = poseType;
        this.healthBenefits = healthBenefits;
        this.imgURL = imgURL;
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

    public String getEnglishName() { return englishName; }

    public String getSanskritName() { return sanskritName; }

    public String getPoseType() { return poseType; }

    public String getHealthBenefits() { return healthBenefits; }

    public String getImgURL() { return imgURL; }

    public String getID() { return id.toString(); }

}
