package com.danny.codingchallenge.model;

public class Topic {
    private int upVote = 0;
    private int downVote = 0;
    private String topicDescription = "";

    public Topic() {
    }

    public Topic(String topicDescription, int upVote, int downVote ){

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

    public int getUpVote() {
        return upVote;
    }

    public void setDownVote(int downVote){
        this.downVote =downVote;
    }

    public int getDownVote() {
        return downVote;
    }
}
