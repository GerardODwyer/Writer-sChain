package com.example.writerchainapp.data.model;

public class Chapters {

    private long chapterId;
    private String chapterTitle;
    private String chapterBody;


    public Chapters(long chapterId, String chapterTitle, String chapterBody) {
        this.chapterId = chapterId;
        this.chapterTitle = chapterTitle;
        this.chapterBody = chapterBody;
    }

    public Chapters() {
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getChapterBody() {
        return chapterBody;
    }

    public void setChapterBody(String chapterBody) {
        this.chapterBody = chapterBody;
    }
}
