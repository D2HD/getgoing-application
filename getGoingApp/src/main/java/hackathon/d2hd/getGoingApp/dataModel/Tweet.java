package hackathon.d2hd.getGoingApp.dataModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tweet")
public class Tweet {
    @Id
    @Column(name = "url")
    private String url;

    @Column(name = "main_content")
    private String main_content;

    @Column(name = "profile_name")
    private String profile_name;

    @Column(name = "tweet_endpoint")
    private String tweet_endpoint;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "retweets")
    private Long retweets;

    @Column(name = "quote_tweets")
    private Long quote_tweets;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "tweet_json")
    private String tweet_json;

    public Tweet(String url, String main_content, String profile_name, String tweet_endpoint, String content, Long likes, Long retweets, Long quote_tweets, String timestamp, String tweet_json) {
        this.url = url;
        this.main_content = main_content;
        this.profile_name = profile_name;
        this.tweet_endpoint = tweet_endpoint;
        this.content = content;
        this.likes = likes;
        this.retweets = retweets;
        this.quote_tweets = quote_tweets;
        this.timestamp = timestamp;
        this.tweet_json = tweet_json;
    }

    public Tweet() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMain_content() {
        return main_content;
    }

    public void setMain_content(String main_content) {
        this.main_content = main_content;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }

    public String getTweet_endpoint() {
        return tweet_endpoint;
    }

    public void setTweet_endpoint(String tweet_endpoint) {
        this.tweet_endpoint = tweet_endpoint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getRetweets() {
        return retweets;
    }

    public void setRetweets(Long retweets) {
        this.retweets = retweets;
    }

    public Long getQuote_tweets() {
        return quote_tweets;
    }

    public void setQuote_tweets(Long quote_tweets) {
        this.quote_tweets = quote_tweets;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTweet_json() {
        return tweet_json;
    }

    public void setTweet_json(String tweet_json) {
        this.tweet_json = tweet_json;
    }
}
