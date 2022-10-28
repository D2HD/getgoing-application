package hackathon.d2hd.getGoingApp.controller;

import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.implementation.TweetServiceImpl;
import hackathon.d2hd.getGoingApp.sample.TweetSamples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {
    @Autowired
    private TweetServiceImpl tweetService;
    private TweetSamples tweetSamples = new TweetSamples();

    @GetMapping("/getTweetList")
    public List<Tweet> getTweetList() {
        return tweetService.getTweetList();
    }

    @GetMapping("/countTotal")
    public Long countTotalTweets() {
        return tweetService.countTotalTweets();
    }

    @GetMapping("/getTopFiveTrends")

    @PostMapping("/addTweet")
    public void addTweet() {
        try{
            tweetService.addTweet(tweetSamples.getTweet1());
            tweetService.addTweet(tweetSamples.getTweet2());
            tweetService.addTweet(tweetSamples.getTweet1());
            tweetService.addTweet(tweetSamples.getTweet3());
        } catch(Exception e){
            throw new IllegalArgumentException("Unable to add tweet. Error Message: " + e);
        }
    }

    @PostMapping("/populateDatabase")
    public void populateDatabase() {
        ArrayList<Tweet> tweetArrayList = new ArrayList<>();
        tweetArrayList.add(tweetSamples.getTweet1());
        tweetArrayList.add(tweetSamples.getTweet2());
        tweetArrayList.add(tweetSamples.getTweet3());

        for(Tweet twt: tweetArrayList) {
            tweetService.addTweet(twt);
        }
    }

    @DeleteMapping("/clearDatabase")
    public void clearDatabase() {
        tweetService.clearDatabase();
    }

}
