package com.example.writerchainapp.Constructors;


import java.io.Serializable;

public class Chapters implements Serializable {
    private String ChapterName;
    private String chapterID;
    private String chainChapID;
    private String chapterAuthor;
    private String chapterDescription;
    private String dateCreated;
    private String chapterGenre;


    public Chapters() {
    }

    public Chapters(String chapterName, String chapterID, String chainChapID, String chapterAuthor, String chapterDescription, String dateCreated, String chapterGenre) {
        this.ChapterName = chapterName;
        this.chapterID = chapterID;
        this.chainChapID = chainChapID;
        this.chapterAuthor = chapterAuthor;
        this.chapterDescription = chapterDescription;
        this.dateCreated = dateCreated;
        this.chapterGenre = chapterGenre;
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

}