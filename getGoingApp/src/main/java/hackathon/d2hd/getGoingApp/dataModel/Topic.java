package hackathon.d2hd.getGoingApp.dataModel;

import java.math.BigDecimal;
import java.util.Date;

public class Topic {
    String topic_id;
    String topic_name;
    Long num_of_occurrence;
    Date timestamp;
    static Long like_count = 0L;
    static Long retweet_count = 0L;
    static Long quote_tweet_count = 0L;
    BigDecimal general_sentiment;

    public Topic() {}

    public Topic(String topic_id, String topic_name, Long num_of_occurrence, Date timestamp, BigDecimal general_sentiment) {
        this.topic_id = topic_id;
        this.topic_name = topic_name;
        this.num_of_occurrence = num_of_occurrence;
        this.timestamp = timestamp;
        this.general_sentiment = general_sentiment;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public Long getNum_of_occurrence() {
        return num_of_occurrence;
    }

    public void setNum_of_occurrence(Long num_of_occurrence) {
        this.num_of_occurrence = num_of_occurrence;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public static Long getLike_count() {
        return like_count;
    }

    public static void setLike_count(Long like_count) {
        Topic.like_count = like_count;
    }

    public static Long getRetweet_count() {
        return retweet_count;
    }

    public static void setRetweet_count(Long retweet_count) {
        Topic.retweet_count = retweet_count;
    }

    public static Long getQuote_tweet_count() {
        return quote_tweet_count;
    }

    public static void setQuote_tweet_count(Long quote_tweet_count) {
        Topic.quote_tweet_count = quote_tweet_count;
    }

    public BigDecimal getGeneral_sentiment() {
        return general_sentiment;
    }

    public void setGeneral_sentiment(BigDecimal general_sentiment) {
        this.general_sentiment = general_sentiment;
    }
}
