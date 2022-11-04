package hackathon.d2hd.getGoingApp.dataModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @Column(name = "topic_id")
    private String topic_id;

    @Column(name = "topic_name")
    private String topic_name;

    @Column(name = "num_of_occurrence")
    private Long num_of_occurrence;

    @Column(name = "datetime")
    private LocalDateTime timestamp;

    @Column(name = "like_count")
    private Long like_count;

    @Column(name = "retweet_count")
    private Long retweet_count;

    @Column(name = "quote_tweet_count")
    private Long quote_tweet_count ;

    @Column(name = "general_sentiment")
    private BigDecimal general_sentiment;

    @OneToMany
    private ArrayList<SevenDaySearch> seven_day_search;

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
        this.seven_day_search = new ArrayList<>();
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

    public ArrayList<SevenDaySearch> getSeven_day_search() {
        return seven_day_search;
    }

    public void setSeven_day_search(ArrayList<SevenDaySearch> seven_day_search) {
        this.seven_day_search = seven_day_search;
    }
}

@Entity
class SevenDaySearch {
    @Id
    private Long num_of_search;

    public SevenDaySearch() {
    }

    public Long getNum_of_search() {
        return num_of_search;
    }

    public void setNum_of_search(Long num_of_search) {
        this.num_of_search = num_of_search;
    }
}