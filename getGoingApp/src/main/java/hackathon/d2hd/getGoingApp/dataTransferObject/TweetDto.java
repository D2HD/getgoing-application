package hackathon.d2hd.getGoingApp.dataTransferObject;

import java.time.LocalDateTime;

public class TweetDto {
    private String url;
    private String topic;
    private String profile_name;
    private long tweet_id;
    private String username;
    private String profile_picture_url;
    private String tweet_content;
    private long tweet_like_count;
    private long tweet_retweet_count;
    private long tweet_reply_count;
    private long tweet_quote_tweet_count;
    private String photo_urls;
    private String photo_url_number;
    private LocalDateTime localDateTime;
    private String tweet_json;

    public TweetDto() {}

    public TweetDto(String url, String topic, String profile_name, long tweet_id, String username, String profile_picture_url, String tweet_content, long tweet_like_count, long tweet_retweet_count, long tweet_reply_count, long tweet_quote_tweet_count, String photo_urls, String photo_url_number, LocalDateTime localDateTime, String tweet_json) {
        this.url = url;
        this.topic = topic;
        this.profile_name = profile_name;
        this.tweet_id = tweet_id;
        this.username = username;
        this.profile_picture_url = profile_picture_url;
        this.tweet_content = tweet_content;
        this.tweet_like_count = tweet_like_count;
        this.tweet_retweet_count = tweet_retweet_count;
        this.tweet_reply_count = tweet_reply_count;
        this.tweet_quote_tweet_count = tweet_quote_tweet_count;
        this.photo_urls = photo_urls;
        this.photo_url_number = photo_url_number;
        this.localDateTime = localDateTime;
        this.tweet_json = tweet_json;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    public long getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(long tweet_id) {
        this.tweet_id = tweet_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_picture_url() {
        return profile_picture_url;
    }

    public void setProfile_picture_url(String profile_picture_url) {
        this.profile_picture_url = profile_picture_url;
    }

    public String getTweet_content() {
        return tweet_content;
    }

    public void setTweet_content(String tweet_content) {
        this.tweet_content = tweet_content;
    }

    public long getTweet_like_count() {
        return tweet_like_count;
    }

    public void setTweet_like_count(long tweet_like_count) {
        this.tweet_like_count = tweet_like_count;
    }

    public long getTweet_retweet_count() {
        return tweet_retweet_count;
    }

    public void setTweet_retweet_count(long tweet_retweet_count) {
        this.tweet_retweet_count = tweet_retweet_count;
    }

    public long getTweet_reply_count() {
        return tweet_reply_count;
    }

    public void setTweet_reply_count(long tweet_reply_count) {
        this.tweet_reply_count = tweet_reply_count;
    }

    public long getTweet_quote_tweet_count() {
        return tweet_quote_tweet_count;
    }

    public void setTweet_quote_tweet_count(long tweet_quote_tweet_count) {
        this.tweet_quote_tweet_count = tweet_quote_tweet_count;
    }

    public String getPhoto_urls() {
        return photo_urls;
    }

    public void setPhoto_urls(String photo_urls) {
        this.photo_urls = photo_urls;
    }

    public String getPhoto_url_number() {
        return photo_url_number;
    }

    public void setPhoto_url_number(String photo_url_number) {
        this.photo_url_number = photo_url_number;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getTweet_json() {
        return tweet_json;
    }

    public void setTweet_json(String tweet_json) {
        this.tweet_json = tweet_json;
    }
}



