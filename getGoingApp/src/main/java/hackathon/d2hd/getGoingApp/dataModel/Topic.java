package hackathon.d2hd.getGoingApp.dataModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Topic {
    private String topic_id;
    private String topic_name;
    private Long num_of_occurrence;
    private LocalDateTime timestamp;
    private Long like_count;
    private Long retweet_count;
    private Long quote_tweet_count ;
    private BigDecimal general_sentiment;

    public Topic() {}

    public Topic(String topic_id, String topic_name, Long num_of_occurrence, LocalDateTime timestamp, Long like_count, Long retweet_count, Long quote_tweet_count, BigDecimal general_sentiment) {
        this.topic_id = topic_id;
        this.topic_name = topic_name;
        this.num_of_occurrence = num_of_occurrence;
        this.timestamp = timestamp;
        this.like_count = like_count;
        this.retweet_count = retweet_count;
        this.quote_tweet_count = quote_tweet_count;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getLike_count() {
        return like_count;
    }

    public void setLike_count(Long like_count) {
        this.like_count = like_count;
    }

    public Long getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(Long retweet_count) {
        this.retweet_count = retweet_count;
    }

    public Long getQuote_tweet_count() {
        return quote_tweet_count;
    }

    public void setQuote_tweet_count(Long quote_tweet_count) {
        this.quote_tweet_count = quote_tweet_count;
    }

    public BigDecimal getGeneral_sentiment() {
        return general_sentiment;
    }

    public void setGeneral_sentiment(BigDecimal general_sentiment) {
        this.general_sentiment = general_sentiment;
    }
}
