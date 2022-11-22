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

@RestController
@RequestMapping("/api/tweet")
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @GetMapping("/getTweetDatabaseSize")
    public int getTweetDatabaseSize() {
        return tweetService.tweetDatabaseSize();
    }

    @GetMapping("/getAllTweets")
    public List<Tweet> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("/getAllTweetDtos")
    public List<TweetDto> getAllTweetDtos() throws JsonProcessingException {
        List <Tweet> tweetList = tweetService.getAllTweets();
        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetList);
        tweetDtoList.sort(Comparator.comparing(TweetDto::getLocalDateTime).reversed());

        return tweetDtoList;
    }

    @GetMapping("/saveAllTweets")
    public void saveAllTweets() {

    }

    @DeleteMapping("/clearTweetDatabase")
    public void clearTweetDatabase() {
        tweetService.clearTweetDatabase();
    }
}
