package edu.northeastern.numad22fa_team_22;

import java.util.ArrayList;
import java.util.List;

public class User {
    //User Name
    public String username;
    // Sent sticker count per user
    public int stickerCount;
    //Total cost of the sent Stickers
    public double  totalStickerCost;
    public List<Sticker> sentStickerList;
    public List<Sticker> receiveStickerList;

    public List<Sticker> getReceiveStickerList() {
        return receiveStickerList;
    }

    public List<Sticker> getSenderStickerList() {
        return sentStickerList;
    }

    public List<Sticker> getSticker() {
        return sentStickerList;
    }

    public void addSenderStickerList(Sticker stickerName) {
        this.sentStickerList.add(stickerName);
    }

    public void addReceiverStickerList(Sticker stickerName) {
        this.receiveStickerList.add(stickerName);
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStickerCount() {
        return stickerCount;
    }

    public void setStickerCount(int stickerCount) {
        this.stickerCount = stickerCount;
    }

    public double getTotalStickerCost() {
        return totalStickerCost;
    }

    public void setTotalStickerCost(double totalStickerCost) {
        this.totalStickerCost = totalStickerCost;
    }

    public User(String username, int count, double cost) {
        this.username = username;
        this.stickerCount = count;
        this.totalStickerCost = cost;
        this.sentStickerList = new ArrayList<>();
        this.receiveStickerList = new ArrayList<>();

    }


}
