package hackathon.d2hd.getGoingApp.dataModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "hashtag")
public class Hashtag {
    @Id
    @Column(name = "hashtag_id")
    private String hashtag_id;
    @Column(name = "hashtag_name")
    private String hashtag_name;
    @Column(name = "num_of_occurrence")
    private Long num_of_occurrence;
    @Column(name = "date")
    private LocalDate timestamp;
    @Column(name = "like_count")
    private Long like_count;
    @Column(name = "retweet_count")
    private Long retweet_count;
    @Column(name = "general_sentiment")
    private Double general_sentiment;
    public Hashtag() {}

    public Hashtag(String hashtag_id, String hashtag_name, Long num_of_occurrence, LocalDate timestamp, Long like_count, Long retweet_count, Double general_sentiment) {
        this.hashtag_id = hashtag_id;
        this.hashtag_name = hashtag_name;
        this.num_of_occurrence = num_of_occurrence;
        this.timestamp = timestamp;
        this.like_count = like_count;
        this.retweet_count = retweet_count;
        this.general_sentiment = general_sentiment;
    }

    public String getHashtag_id() {
        return hashtag_id;
    }

    public void setHashtag_id(String hashtag_id) {
        this.hashtag_id = hashtag_id;
    }

    public String getHashtag_name() {
        return hashtag_name;
    }

    public void setHashtag_name(String hashtag_name) {
        this.hashtag_name = hashtag_name;
    }

    public Long getNum_of_occurrence() {
        return num_of_occurrence;
    }

    public void setNum_of_occurrence(Long num_of_occurrence) {
        this.num_of_occurrence = num_of_occurrence;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
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

    public Double getGeneral_sentiment() {
        return general_sentiment;
    }

    public void setGeneral_sentiment(Double general_sentiment) {
        this.general_sentiment = general_sentiment;
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "hashtag_id='" + hashtag_id + '\'' +
                ", hashtag_name='" + hashtag_name + '\'' +
                ", num_of_occurrence=" + num_of_occurrence +
                ", timestamp=" + timestamp +
                ", like_count=" + like_count +
                ", retweet_count=" + retweet_count +
                ", general_sentiment=" + general_sentiment +
                '}';
    }
}
