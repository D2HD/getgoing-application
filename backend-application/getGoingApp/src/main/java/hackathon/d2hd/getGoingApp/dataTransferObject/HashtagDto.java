package hackathon.d2hd.getGoingApp.dataTransferObject;

import java.time.LocalDate;
import java.util.Arrays;

public class HashtagDto {
    /**A concatenation of the hashtag and the date.
     * E.g: #basketball2022-11-20*/
    private String hashtag_id;
    /**The name of the hashtag.*
     * E.g: #basketbal*/
    private String hashtag_name;
    /**The number of likes of the Tweet that the hashtag is from.*/
    private Long like_count;
    /**A Long array with a length of 7. Each index represents the number of times the hashtag has been searched within the corresponding day over 7 days.
     * The list is sorted chronologically, with index 0 being the 7th day and index 6 being the current day.*/
    private Long[] daily_hashtag_count;
    /**A Long array with a length of 4. Each index represents the number of times the hashtag has been searched within the corresponding week over 4 weeks.
     * The list is sorted chronologically, with index 0 being the 4th week and index 3 being the current week.*/
    private Long[] weekly_hashtag_count;
    /**A General Sentiment array with a length of 4. Each index represents the GeneralSentiment of the hashtag within the corresponding week over 4 weeks.
     * A GeneralSentiment stores the count of the number of positive and negative sentiments that a hashtag has.
     * The list is sorted chronologically, with index 0 being the 4th week and index 3 being the current week.*/
    private GeneralSentiment[] weekly_general_sentiment;
    /**Stores the count of the number of positive and negative sentiments that a hashtag has in the current day in relation to its timestamp*/
    private GeneralSentiment general_sentiment_of_the_day;
    /**Stores the count of the number of positive and negative sentiments that a hashtag has in the current week in relation to its timestamp*/
    private GeneralSentiment general_sentiment_of_the_week;
    /**A Long array with a length of 7. Each index represents the number of retweets of the hashtag  within the corresponding day over 7 days.
     * The list is sorted chronologically, with index 0 being the 7th day and index 6 being the current day.*/
    private Long[] daily_retweet_count;
    /**The number of retweets of the Tweet that the hashtag is from*/
    private Long retweet_count;
    /**The general sentiment of the Tweet that the hashtag is from*/
    private Double general_sentiment;
    /**The number of times the hashtag has been searched*/
    private Long num_of_occurrence;
    /**The date of the tweet in YYYY-MM-DD format that the hashtag is from*/
    private LocalDate timestamp;

    public HashtagDto() {}

    public HashtagDto(String hashtag_id, String hashtag_name, Long like_count, Long[] daily_hashtag_count, Long[] weekly_hashtag_count, GeneralSentiment[] weekly_general_sentiment, GeneralSentiment general_sentiment_of_the_day, GeneralSentiment general_sentiment_of_the_week, Long[] daily_retweet_count, Long retweet_count, Double general_sentiment, Long num_of_occurrence, LocalDate timestamp) {
        this.hashtag_id = hashtag_id;
        this.hashtag_name = hashtag_name;
        this.like_count = like_count;
        this.daily_hashtag_count = daily_hashtag_count;
        this.weekly_hashtag_count = weekly_hashtag_count;
        this.weekly_general_sentiment = weekly_general_sentiment;
        this.general_sentiment_of_the_day = general_sentiment_of_the_day;
        this.general_sentiment_of_the_week = general_sentiment_of_the_week;
        this.daily_retweet_count = daily_retweet_count;
        this.retweet_count = retweet_count;
        this.general_sentiment = general_sentiment;
        this.num_of_occurrence = num_of_occurrence;
        this.timestamp = timestamp;
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

    public Long getLike_count() {
        return like_count;
    }

    public void setLike_count(Long like_count) {
        this.like_count = like_count;
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

    public GeneralSentiment[] getWeekly_general_sentiment() {
        return weekly_general_sentiment;
    }

    public void setWeekly_general_sentiment(GeneralSentiment[] weekly_general_sentiment) {
        this.weekly_general_sentiment = weekly_general_sentiment;
    }

    public GeneralSentiment getGeneral_sentiment_of_the_day() {
        return general_sentiment_of_the_day;
    }

    public void setGeneral_sentiment_of_the_day(GeneralSentiment general_sentiment_of_the_day) {
        this.general_sentiment_of_the_day = general_sentiment_of_the_day;
    }

    public GeneralSentiment getGeneral_sentiment_of_the_week() {
        return general_sentiment_of_the_week;
    }

    public void setGeneral_sentiment_of_the_week(GeneralSentiment general_sentiment_of_the_week) {
        this.general_sentiment_of_the_week = general_sentiment_of_the_week;
    }

    public Long[] getDaily_retweet_count() {
        return daily_retweet_count;
    }

    public void setDaily_retweet_count(Long[] daily_retweet_count) {
        this.daily_retweet_count = daily_retweet_count;
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

    @Override
    public String toString() {
        return "HashtagDto{" +
                "hashtag_id='" + hashtag_id + '\'' +
                ", hashtag_name='" + hashtag_name + '\'' +
                ", like_count=" + like_count +
                ", daily_hashtag_count=" + Arrays.toString(daily_hashtag_count) +
                ", weekly_hashtag_count=" + Arrays.toString(weekly_hashtag_count) +
                ", weekly_general_sentiment=" + Arrays.toString(weekly_general_sentiment) +
                ", general_sentiment_of_the_day=" + general_sentiment_of_the_day +
                ", general_sentiment_of_the_week=" + general_sentiment_of_the_week +
                ", daily_retweet_count=" + Arrays.toString(daily_retweet_count) +
                ", retweet_count=" + retweet_count +
                ", general_sentiment=" + general_sentiment +
                ", num_of_occurrence=" + num_of_occurrence +
                ", timestamp=" + timestamp +
                '}';
    }
}
