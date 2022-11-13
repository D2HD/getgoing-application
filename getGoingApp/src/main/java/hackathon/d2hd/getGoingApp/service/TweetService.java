package hackathon.d2hd.getGoingApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface TweetService {
    List<TweetDto> JsonToTweetDeserializer(String hashscraperResponseBody) throws JsonProcessingException;
    List<TweetDto> JsonToTweetDeserializer(File jsonFile) throws IOException;
    void saveTweet(Tweet tweet);
    void clearDatabase();
    TweetDto tweetToTweetDto(Tweet tweet) throws JsonProcessingException;
    LocalDateTime stringToLocalDateTime(String localDateTimeString);
    List<TweetDto> tweetListToTweetDtoList(List<Tweet> tweetList);
    List<String> getHashtagList(String content);
    Double tweetJsonToGeneralSentiment(String tweet_json) throws JsonProcessingException;
}
