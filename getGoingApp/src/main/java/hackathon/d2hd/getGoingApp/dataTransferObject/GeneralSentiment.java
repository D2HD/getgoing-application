package hackathon.d2hd.getGoingApp.dataTransferObject;

public class GeneralSentiment {
    private Double positive_sentiment;
    private Double negative_sentiment;

    public GeneralSentiment() {
    }
    public GeneralSentiment(Double positive_sentiment, Double negative_sentiment) {
        this.positive_sentiment = positive_sentiment;
        this.negative_sentiment = negative_sentiment;
    }

    public Double getPositive_sentiment() {
        return positive_sentiment;
    }

    public void setPositive_sentiment(Double positive_sentiment) {
        this.positive_sentiment = positive_sentiment;
    }

    public Double getNegative_sentiment() {
        return negative_sentiment;
    }

    public void setNegative_sentiment(Double negative_sentiment) {
        this.negative_sentiment = negative_sentiment;
    }
}
