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

    @GetMapping("/currentTop5HashtagDtoList")
    public List<HashtagDto> currentTop5HashtagDtoList() {
        return freeService.getTop5HashtagDtos();
    }

    //New Change
    @GetMapping("/keywordSearch/{userInput}")
    public List<TweetDto> keywordSearchToTweetDtoList(@PathVariable String userInput) throws JsonProcessingException {
        String response = freeService.hashscraperCall(userInput);

        return freeService.keywordSearchToTweeDtoList(response);
    }

    // TODO: 20/11/22 Finish Later
    @GetMapping("/keywordSearchToHashtagDto/{userInput}")
    public HashtagDto keywordSearchToHashtagDto(@PathVariable String userInput) throws JsonProcessingException {
        String response = freeService.hashscraperCall(userInput);
        List<TweetDto> tweetDtoList = freeService.keywordSearchToTweeDtoList(response);
        List<Hashtag> hashtagList = freeService.tweetDtoListToPremiumHashtagList(tweetDtoList, new HashMap<>());

        return null;
    }


    @GetMapping("/hashscraperDateSearch/{start}/{end}")
    public String hashscraperDateSearch(@PathVariable String start, @PathVariable String end) {
        return freeService.hashscraperDateCall(start, end);
    }
}

