package com.trainoo.crawler.novel;

/**
 * Created by zhoutao on 2018/6/28 17:18
 */
public class Chapter {

    private int id;

    String title;

    String content;

    int titleNum;

    public Chapter(){}

    public Chapter(String title, String content, int titleNum){
        this.title = title;
        this.content = content;
        this.titleNum = titleNum;
    }

    public int getTitleNum() {
        return titleNum;
    }

    public void setTitleNum(int titleNum) {
        this.titleNum = titleNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", titleNum=" + titleNum +
                '}';
    }
}


