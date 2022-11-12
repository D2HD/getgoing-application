package hackathon.d2hd.getGoingApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public interface TweetService {
    public List<TweetDto> JsonToTweetDeserializer(File jsonFile) throws IOException;
    public void saveTweet(Tweet tweet);
    public void saveTweetList(List<Tweet> tweetList);
    public List<Tweet> getAllTweetsFromDatabase();
    public void clearDatabase();
    public TweetDto tweetToTweetDto(Tweet tweet) throws JsonProcessingException;
    public LocalDateTime stringToLocalDateTime(String localDateTimeString);
    List<TweetDto> tweetListToTweetDtoList(List<Tweet> tweetList);
    List<String> getHashtagList(String content);
    Double tweetJsonToGeneralSentiment(String tweet_json) throws JsonProcessingException;
}
