package com.example.writerchainapp.Constructors;


import java.io.Serializable;

public class Chapters implements Serializable {
    private String ChapterName;
    private String chapterID;
    private String chapterFKID;
    private String chainChapID;
    private String chapterAuthor;
    private String chapterAuthorID;
    private String chapterDescription;
    private String dateCreated;
    private String chapterGenre;


    public Chapters() {
    }

    public Chapters(String chapterName, String chapterID, String chapterFKID, String chainChapID, String chapterAuthor, String chapterAuthorID, String chapterDescription, String dateCreated, String chapterGenre) {
        this.ChapterName = chapterName;
        this.chapterID = chapterID;
        this.chapterFKID = chapterFKID;
        this.chainChapID = chainChapID;
        this.chapterAuthor = chapterAuthor;
        this.chapterAuthorID = chapterAuthorID;
        this.chapterDescription = chapterDescription;
        this.dateCreated = dateCreated;
        this.chapterGenre = chapterGenre;
    }

    public String getChapterFKID() {
        return chapterFKID;
    }

    public void setChapterFKID(String chapterFKID) {
        this.chapterFKID = chapterFKID;
    }

    public String getChapterAuthorID() {
        return chapterAuthorID;
    }

    public void setChapterAuthorID(String chapterAuthorID) {
        this.chapterAuthorID = chapterAuthorID;
    }

    public String getchainChapID() {
        return chainChapID;
    }

    public void setchainChapID(String chainChapID) {
        this.chainChapID = chainChapID;
    }

    public String getChapterName() {
        return ChapterName;
    }

    public void setChapterName(String chapterName) {
        ChapterName = chapterName;
    }

    public String getChapterID() {
        return chapterID;
    }

    public void setChapterID(String chapterID) {
        this.chapterID = chapterID;
    }

    public String getChapterAuthor() {
        return chapterAuthor;
    }

    public void setChapterAuthor(String chapterAuthor) {
        this.chapterAuthor = chapterAuthor;
    }

    public String getChapterDescription() {
        return chapterDescription;
    }

    public void setChapterDescription(String chapterDescription) {
        this.chapterDescription = chapterDescription;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getChapterGenre() {
        return chapterGenre;
    }

    public void setChapterGenre(String chapterGenre) {
        this.chapterGenre = chapterGenre;
    }

    @Override
    public String toString() {
        return "Chapters{" +
                "ChapterName='" + ChapterName + '\'' +
                ", chapterID='" + chapterID + '\'' +
                ", chapterFKID='" + chapterFKID + '\'' +
                ", chainChapID='" + chainChapID + '\'' +
                ", chapterAuthor='" + chapterAuthor + '\'' +
                ", chapterAuthorID='" + chapterAuthorID + '\'' +
                ", chapterDescription='" + chapterDescription + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", chapterGenre='" + chapterGenre + '\'' +
                '}';
    }
}