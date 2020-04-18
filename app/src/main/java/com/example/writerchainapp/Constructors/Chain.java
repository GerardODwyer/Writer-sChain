package com.example.writerchainapp.Constructors;


import com.example.writerchainapp.data.model.Chapters;

import java.io.Serializable;
import java.util.List;

public class Chain implements Serializable {
    public static final String COMDEY = "COMDEY";
    public static final String HORROR = "HORROR";
    public static final String LOVE = "LOVE";
    public static final String SCIFI = "SCIFI";
    public static final String MEDEVIL = "MEDEVIL";
    public static final String CRIME = "CRIME";
    public static final String MYSTERY = "MYSTERY";
    public static final String TRADGEY = "TRADGEY";
    public static final String WESTREN = "WESTREN";
    public static final String ACTION = "ACTION";
    public static final String ADVENTURE = "ADVENTURE";
    public static final String DRAMA = "DRAMA";
    public static final String FANFIC = "FANFIC";
    public static final String FANTASY = "FANTASY";
    public static final String FAIRYTALE = "FAIRYTALE";
    public static final String MAGICREALISM = "MAGICREALISM";
    public static final String CONSPIRACY = "CONSPIRACY";
    public static final String DETECTIVE = "DETECTIVE";
    private String chainName;
    private String chainID;
    private String chainAuthor;
    private String chainDescription;
    private String dateCreated;
    private String chainGenre;
    private int chapterCount;
    private List<Chapters> chaptersList;
    private String chainUpvotes;

    public Chain(String chainName, String chainID, String chainAuthor, String chainDescription,
                 String chainGenre, int chapterCount, String chainUpvotes, List<Chapters> chapters) {
        this.chainName = chainName;
        this.chainID = chainID;
        this.chainAuthor = chainAuthor;
        this.chainDescription = chainDescription;
        this.chainGenre = chainGenre;
        this.chapterCount = chapterCount;
        this.chainUpvotes = chainUpvotes;
        this.chaptersList = chapters;
    }

    public Chain() {

    }

    public List<Chapters> getChaptersList() {
        return chaptersList;
    }

    public void setChaptersList(List<Chapters> chaptersList) {
        this.chaptersList = chaptersList;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public String getChainID() {
        return chainID;
    }

    public void setChainID(String chainID) {
        this.chainID = chainID;
    }

    public String getChainAuthor() {
        return chainAuthor;
    }

    public void setChainAuthor(String chainAuthor) {
        this.chainAuthor = chainAuthor;
    }

    public String getChainDescription() {
        return chainDescription;
    }

    public void setChainDescription(String chainDescription) {
        this.chainDescription = chainDescription;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getChainGenre() {
        return chainGenre;
    }

    public void setChainGenre(String chainGenre) {
        this.chainGenre = chainGenre;
    }

    public int getChapterCount() {
        return chapterCount;
    }

    public void setChapterCount(int chapterCount) {
        this.chapterCount = chapterCount;
    }

    public String getChainUpvotes() {
        return chainUpvotes;
    }

    public void setChainUpvotes(String chainUpvotes) {
        this.chainUpvotes = chainUpvotes;
    }
}