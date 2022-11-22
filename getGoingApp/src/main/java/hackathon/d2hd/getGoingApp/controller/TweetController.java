package hackathon.d2hd.getGoingApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

/**
 * Provides the Tweet API for the GetGoing_ application.
 * Methods implemented are accessed via the URI.
 * <p />
 * Example: <a href="http://localhost:8080/api/tweet/getTweetDatabaseSize">
 *           http://localhost:8080/api/tweet/getTweetDatabaseSize</a>
 * @author Sean Marinas
 */
@RestController
@RequestMapping("/api/tweet")
public class TweetController {
    /**
     * Contains the methods of the {@link TweetService} interface.
     * <p />
     * The implementation of the interface is found in {@link
     * hackathon.d2hd.getGoingApp.implementation.TweetServiceImpl
     * TweetServiceImpl}.
     */
    @Autowired
    private TweetService tweetService;

    /**
     * @return The total number of tweets in the database.
     */
    @GetMapping("/getTweetDatabaseSize")
    public int getTweetDatabaseSize() {
        return tweetService.tweetDatabaseSize();
    }

    /**
     * @return A list of all Tweets from the database.
     */
    @GetMapping("/getAllTweets")
    public List<Tweet> getAllTweets() {
        return tweetService.getAllTweets();
    }

    /**
     * @return A list of all TweetsDtos from the database
     */
    @GetMapping("/getAllTweetDtos")
    public List<TweetDto> getAllTweetDtos() {
        List<Tweet> tweetList = tweetService.getAllTweets();
        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetList);
        tweetDtoList.sort(Comparator.comparing(TweetDto::getLocalDateTime).reversed());

        return tweetDtoList;
    }

    /**
     * Empties the tweet database.
     */
    @DeleteMapping("/clearTweetDatabase")
    public void clearTweetDatabase() {
        tweetService.clearTweetDatabase();
    }
}
