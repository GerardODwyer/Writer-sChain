package com.example.writerchainapp.Constructors;


import java.io.Serializable;

public class Users implements Serializable {
    private String userID;
    private String userDisplayName;
    private int userChainCount;
    private int userChapterCount;
    private boolean userAdmin;

    public Users() {
    }

    public Users(String userID, String userDisplayName, int userChainCount, int userChapterCount, boolean userAdmin) {
        this.userID = userID;
        this.userDisplayName = userDisplayName;
        this.userChainCount = userChainCount;
        this.userChapterCount = userChapterCount;
        this.userAdmin = userAdmin;
    }

    public boolean isUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(boolean userAdmin) {
        this.userAdmin = userAdmin;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public void increaseUserChapterCount() {
        int currentChapterCount = getUserChapterCount();
        int i = currentChapterCount + 1;
        setUserChapterCount(i);
    }

    public void decreaseUserChapterCount() {
        int currentChapterCount = getUserChapterCount();
        int i = currentChapterCount - 1;
        setUserChapterCount(i);
    }

    public void increaseUserChainCount() {
        int currentChainCount = getUserChainCount();
        int i = currentChainCount + 1;
        setUserChainCount(i);
    }

    public void decreaseUserChainCount() {
        int currentChainCount = getUserChainCount();
        int i = currentChainCount - 1;
        setUserChainCount(i);
    }

    public int getUserChainCount() {
        return userChainCount;
    }

    public void setUserChainCount(int userChainCount) {
        this.userChainCount = userChainCount;
    }

    public int getUserChapterCount() {
        return userChapterCount;
    }

    public void setUserChapterCount(int userChapterCount) {
        this.userChapterCount = userChapterCount;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userID='" + userID + '\'' +
                ", userDisplayName='" + userDisplayName + '\'' +
                ", userChainCount=" + userChainCount +
                ", userChapterCount=" + userChapterCount +
                ", userAdmin=" + userAdmin +
                '}';
    }
}