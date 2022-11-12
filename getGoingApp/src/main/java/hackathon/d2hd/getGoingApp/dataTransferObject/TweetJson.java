package hackathon.d2hd.getGoingApp.dataTransferObject;

import java.math.BigDecimal;

public class TweetJson {
    private String content;
    private Double score;

    public TweetJson() {}

    public TweetJson(String content, Double score) {
        this.content = content;
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
