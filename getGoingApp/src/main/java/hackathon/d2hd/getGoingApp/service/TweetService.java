package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Tweet;

import java.util.List;

public interface TweetService {
    public Tweet getTweet(String url);
    public List<Tweet> getTweetList();
    public Long countTotalTweets();
    public Long countByContent(String content);
    public void addTweet(Tweet tweet);
    public void clearDatabase();

}
