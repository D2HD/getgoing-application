package hackathon.d2hd.getGoingApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.FreeService;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/free")
public class FreeController {
    @Value("${keyword}")
    private String keyword;
    @Autowired
    private FreeService freeService;

    /**
     * Performs the Twitter scraping API from Hashscraper
     * URIs such as api_key and keyword are stored in the resources package.
     * By default, keyword is set to forsale to scrape Twitter's "for sale" page
     * Additionally, max_count is set to 20 which returns 20 Tweets relating to the keyword
     * @return
     * A String of the response body of the Hashscraper API
     */
    @GetMapping()
    public String hashscraperCall() {
        return freeService.hashscraperCall(keyword);
    }

    /**
     * Performs the Twitter scraping API from Hashscraper.
     * Scrapes for Tweets that match the userInput and returns a list of Tweets
     * @param userInput
     * A String of the keyword that the user has searched. This String is taken from FE and passed through as a parameter in this API
     * @return
     * A list of 10 Tweets sorted by number of retweets in descending order
     * @throws JsonProcessingException
     */
    @GetMapping("/keywordSearch/{userInput}")
    public List<TweetDto> keywordSearchToTweetDtoList(@PathVariable String userInput) throws JsonProcessingException {
        String response = freeService.hashscraperCall(userInput);

        return freeService.keywordSearchToTweeDtoList(response);
    }


    @GetMapping("/currentTop5HashtagDtoList")
    public List<HashtagDto> currentTop5HashtagDtoList() {
        return freeService.getTop5HashtagDtos();
    }


    @GetMapping("/keywordSearchToHashtagDto/{userInput}")
    public HashtagDto keywordSearchToHashtagDto(@PathVariable String userInput) throws JsonProcessingException {
        String response = freeService.hashscraperCall(userInput);
        List<TweetDto> tweetDtoList = freeService.keywordSearchToTweeDtoList(response);
        Hashtag hashtag = freeService.tweetDtoListToPremiumHashtagList(tweetDtoList);

        return null;
    }


    @GetMapping("/hashscraperDateSearch/{start}/{end}")
    public String hashscraperDateSearch(@PathVariable String start, @PathVariable String end) {
        return freeService.hashscraperDateCall(start, end);
    }
}

