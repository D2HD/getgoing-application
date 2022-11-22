package hackathon.d2hd.getGoingApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface TweetService {
    List<Tweet> hashscraperResponseBodyToTweetDeserializer(String hashscraperResponseBody) throws JsonProcessingException;
    void clearTweetDatabase();
    TweetDto tweetToTweetDto(Tweet tweet) throws JsonProcessingException;
    LocalDateTime stringToLocalDateTime(String localDateTimeString);
    List<TweetDto> tweetListToTweetDtoList(List<Tweet> tweetList);
    List<String> getHashtagList(String content);
    Double tweetJsonToGeneralSentiment(String tweet_json) throws JsonProcessingException;
    int tweetDatabaseSize();
    List<Tweet> getAllTweets();
    void saveTweetList(List<Tweet> tweetList);

}
