package com.example.ideahack;

import com.google.firebase.firestore.Exclude;

public class Note {

    ////////
    int submit;
    int giveVote ;
    String userid;
    String username;

    public Note(int submit, int giveVote, String userid, String username) {
        this.submit = submit;
        this.giveVote = giveVote;
        this.userid = userid;
        this.username = username;
    }

    public Note() {
    }

    public int getSubmit() {
        return submit;
    }

    public void setSubmit(int submit) {
        this.submit = submit;
    }

    public int getGiveVote() {
        return giveVote;
    }

    public void setGiveVote(int giveVote) {
        this.giveVote = giveVote;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
