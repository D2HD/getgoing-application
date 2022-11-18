package hackathon.d2hd.getGoingApp.dataTransferObject;

import java.lang.Double;
import java.time.LocalDate;
import java.util.List;

public class HashtagDto {
    private String hashtag_id;
    private String hashtag_name;
    private Long num_of_occurrence;
    private LocalDate timestamp;
    private Long like_count;
    private Long retweet_count;
    private Double general_sentiment;
    private Long[] daily_hashtag_count;
    private Long[] weekly_hashtag_count;

    public HashtagDto() {}

    public HashtagDto(String hashtag_id, String hashtag_name, Long num_of_occurrence, LocalDate timestamp, Long like_count, Long retweet_count, Double general_sentiment, Long[] daily_hashtag_count, Long[] weekly_hashtag_count) {
        this.hashtag_id = hashtag_id;
        this.hashtag_name = hashtag_name;
        this.num_of_occurrence = num_of_occurrence;
        this.timestamp = timestamp;
        this.like_count = like_count;
        this.retweet_count = retweet_count;
        this.general_sentiment = general_sentiment;
        this.daily_hashtag_count = daily_hashtag_count;
        this.weekly_hashtag_count = weekly_hashtag_count;
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

    public Long[] getDaily_hashtag_count() {
        return daily_hashtag_count;
    }

    public void setDaily_hashtag_count(Long[] daily_hashtag_count) {
        this.daily_hashtag_count = daily_hashtag_count;
    }

    public Long[] getWeekly_hashtag_count() {
        return weekly_hashtag_count;
    }

    public void setWeekly_hashtag_count(Long[] weekly_hashtag_count) {
        this.weekly_hashtag_count = weekly_hashtag_count;
    }
}
