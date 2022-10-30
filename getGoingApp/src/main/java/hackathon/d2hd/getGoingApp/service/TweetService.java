package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDTO;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public interface TweetService {
    public List<Tweet> JsonToTweetDeserializer(File jsonFile) throws IOException;

    // TODO: 30/10/22 Third Change
    /*
    - interface that converts a Tweet to a TweetDTO
    - look for the Fourth Change to see the TweetDTO
    */

    public TweetDTO tweetToTweetDTO(Tweet tweet);
    public LocalDateTime stringToLocalDateTime(String localDateTimeString);
}
