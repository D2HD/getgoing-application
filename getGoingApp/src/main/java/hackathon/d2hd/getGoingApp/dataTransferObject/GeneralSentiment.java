package hackathon.d2hd.getGoingApp.dataTransferObject;

public class GeneralSentiment {
    private Long positive_sentiment;
    private Long negative_sentiment;

    public GeneralSentiment(Long positive_sentiment, Long negative_sentiment) {
        this.positive_sentiment = positive_sentiment;
        this.negative_sentiment = negative_sentiment;
    }

    public Long getPositive_sentiment() {
        return positive_sentiment;
    }

    public void setPositive_sentiment(Long positive_sentiment) {
        this.positive_sentiment = positive_sentiment;
    }

    public Long getNegative_sentiment() {
        return negative_sentiment;
    }

    public void setNegative_sentiment(Long negative_sentiment) {
        this.negative_sentiment = negative_sentiment;
    }

}
