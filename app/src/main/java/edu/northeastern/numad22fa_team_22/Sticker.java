package edu.northeastern.numad22fa_team_22;

public class Sticker {
    public String stickerOwner;
    // date when sticker was sent
    public String stickerSentDate;
    // Cost of the Sticker
    public Double stickerCost;

    public String stickerName;

    public Sticker(){};

    public Sticker(String owner, String sentDate, Double Cost, String stickerName){
        this.stickerOwner = owner;
        this.stickerSentDate = sentDate;
        this.stickerCost = Cost;
        this.stickerName = stickerName;
    }
    public String getStickerName() {
        return stickerName;
    }

    public void setStickerName(String stickerName) {
        this.stickerName = stickerName;
    }

    public Double getStickerCost() {
        return stickerCost;
    }

    public void setStickerCost(Double stickerCost) {
        this.stickerCost = stickerCost;
    }

    public String getStickerOwner() {
        return stickerOwner;
    }

    public void setStickerOwner(String stickerOwner) {
        this.stickerOwner = stickerOwner;
    }

    public String getStickerSentDate() {
        return stickerSentDate;
    }

    public void setStickerSentDate(String stickerSentDate) {
        this.stickerSentDate = stickerSentDate;
    }

}
