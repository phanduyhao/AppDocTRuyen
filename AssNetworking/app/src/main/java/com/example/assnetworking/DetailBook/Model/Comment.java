package com.example.assnetworking.DetailBook.Model;

public class Comment {
    private String fullname;
    private String content;
    private String commenttime;

    public Comment() {
    }

    public Comment(String fullname, String content, String commenttime) {
        this.fullname = fullname;
        this.content = content;
        this.commenttime = commenttime;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }
}
