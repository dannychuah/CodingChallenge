package com.danny.codingchallenge.model;

public class Topic {
    private int upVote = 0;
    private int downVote = 0;
    private String topicDescription = "";

    public Topic() {
    }
    public Topic(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public Topic(String topicDescription, int upVote, int downVote ){
        this.topicDescription = topicDescription;
        this.upVote = upVote;
        this.downVote =downVote;

    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setUpVote(int upVote){
        this.upVote = upVote;
    }

    public void incrementUpVote(){
        ++this.upVote;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setDownVote(int downVote){
        this.downVote =downVote;
    }

    public void incrementDownVote(){
        ++this.downVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public  String toString(){
        return "Topic :"+ topicDescription + ",Upvote :"+upVote+ ",Downvote :"+downVote;
    }
}
