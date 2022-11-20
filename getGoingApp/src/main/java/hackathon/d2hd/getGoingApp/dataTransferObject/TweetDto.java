package hackathon.d2hd.getGoingApp.dataTransferObject;

import java.time.LocalDateTime;
import java.util.List;

public class TweetDto {
    private String url;
    private String topic;
    private String profile_name;
    private long tweet_id;
    private String username;
    private String tweet_content;
    private List<String> hashtagList;
    private long tweet_like_count;
    private long tweet_retweet_count;
    private long tweet_reply_count;
    private long tweet_quote_tweet_count;
    private LocalDateTime localDateTime;
    private Double general_sentiment;

    public TweetDto() {}

    public TweetDto(String url, String topic, String profile_name, long tweet_id, String username, String tweet_content, List<String> hashtagList, long tweet_like_count, long tweet_retweet_count, long tweet_reply_count, long tweet_quote_tweet_count, LocalDateTime localDateTime, Double general_sentiment) {
        this.url = url;
        this.topic = topic;
        this.profile_name = profile_name;
        this.tweet_id = tweet_id;
        this.username = username;
        this.tweet_content = tweet_content;
        this.hashtagList = hashtagList;
        this.tweet_like_count = tweet_like_count;
        this.tweet_retweet_count = tweet_retweet_count;
        this.tweet_reply_count = tweet_reply_count;
        this.tweet_quote_tweet_count = tweet_quote_tweet_count;
        this.localDateTime = localDateTime;
        this.general_sentiment = general_sentiment;
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

    public String getTweet_content() {
        return tweet_content;
    }

    public void setTweet_content(String tweet_content) {
        this.tweet_content = tweet_content;
    }

    public List<String> getHashtagList() {
        return hashtagList;
    }

    public void setHashtagList(List<String> hashtagList) {
        this.hashtagList = hashtagList;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Double getGeneral_sentiment() {
        return general_sentiment;
    }

    public void setGeneral_sentiment(Double general_sentiment) {
        this.general_sentiment = general_sentiment;
    }

    public static class TweetJson {
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

    @Override
    public String toString() {
        return "TweetDto{" +
                "url='" + url + '\'' +
                ", topic='" + topic + '\'' +
                ", profile_name='" + profile_name + '\'' +
                ", tweet_id=" + tweet_id +
                ", username='" + username + '\'' +
                ", tweet_content='" + tweet_content + '\'' +
                ", hashtagList=" + hashtagList +
                ", tweet_like_count=" + tweet_like_count +
                ", tweet_retweet_count=" + tweet_retweet_count +
                ", tweet_reply_count=" + tweet_reply_count +
                ", tweet_quote_tweet_count=" + tweet_quote_tweet_count +
                ", localDateTime=" + localDateTime +
                ", general_sentiment=" + general_sentiment +
                '}';
    }
}


