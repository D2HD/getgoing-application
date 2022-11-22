package hackathon.d2hd.getGoingApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.FreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/free")
public class GetGoingController {
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
     * API for the Free mode
     * Performs the Twitter scraping API from Hashscraper
     * Keyword is initialised to "#forsale" by default which scrapes the for sale portion of twitter
     * @return
     * A list of 5 HashtagDtos sorted by number of occurrence in descending order
     */
    @GetMapping("/currentTop5HashtagDtoList")
    public List<HashtagDto> currentTop5HashtagDtoList() {
        return freeService.getTop5HashtagDtos();
    }

    /**
     * Performs the Twitter scraping API from Hashscraper.
     * Scrapes for Tweets that match the userInput and returns a list of TweetDtos
     * @param userInput
     * A String of the keyword that the user has searched. This String is taken from FE and passed through as a parameter in this API
     * @return
     * A list of 10 TweetDtos sorted by number of retweets in descending order
     * @throws JsonProcessingException
     */
    @GetMapping("/keywordSearchToTweetDtoList/{userInput}")
    public List<TweetDto> keywordSearchToTweetDtoList(@PathVariable String userInput) throws JsonProcessingException {
        String response = freeService.hashscraperCall(userInput);

        return freeService.keywordSearchToTweeDtoList(response);
    }

    /**
     * Performs the Twitter scraping API from Hashscraper.
     * Scrapes for Tweets that match the keyword which has been initialised by userInput and a single HashtagDto object
     * @param userInput
     * A String of the keyword that the user has searched. This String is taken from FE and passed through as a parameter in this API
     * @return
     * A single HashtagDto object that contains information of the hashtag searched over a number of days
     * @throws JsonProcessingException
     */
    @GetMapping("/keywordSearchToHashtagDto/{userInput}")
    public HashtagDto keywordSearchToHashtagDto(@PathVariable String userInput) throws JsonProcessingException {
        String response = freeService.hashscraperCall(userInput);
        List<TweetDto> tweetDtoList = freeService.keywordSearchToTweeDtoList(response);
        return freeService.tweetDtoListToPremiumHashtag(tweetDtoList);
    }
}

