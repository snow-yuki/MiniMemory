package com.marinesnow.minimemory;

/**
 * Created by thinkpad on 2017/7/2.
 */
public class BuleData {
    String content;
    int score = 0;
    public BuleData(String content, int score ) {
        this.content = content;
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
