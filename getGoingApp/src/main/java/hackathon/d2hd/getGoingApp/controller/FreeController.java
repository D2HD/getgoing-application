package hackathon.d2hd.getGoingApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.FreemiumService;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/free")
public class FreeController {
    @Value("${api_key}")
    private String api_key;
    @Value("${keyword}")
    private String keyword;
    @Autowired
    private TweetService tweetService;

    @Autowired
    private FreemiumService freemiumService;
    @Autowired
    private HashtagService hashtagService;

    @GetMapping()
    public String hashscraperCall() {
        WebClient client = WebClient.create("https://www.hashscraper.com/api/twitter/");
        String response = client.post()
                .uri("?apikey=" + api_key + "&keyword=%" + keyword + "&max_count=20&")
                .header("Content-Type", "application/json version=2")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }


    /**
     * The following 3 APIs will be the ones passed to Frontend
     * 1. currentTop5HashtagDtoList
     * 2. sevenDayTop5HashtagDtoListByCount returns a Hashtag DTO list containing 5 HashtagDTO objects, sorted by num_of_occurrence in descending order
     * 3. sevenDayTop5HashtagDtoListByLike returns a Hashtag DTO list containing 5 HashtagDTO objects, sorted by like in descending order
     * @return Returns a hashtagDto list that has been processed
     */

    @GetMapping("/currentTop5HashtagDtoList")
    public List<HashtagDto> currentTop5HashtagDtoList() {
        return freemiumService.getTop5HashtagDtos();
    }


    @GetMapping("/keywordSearch/{userInput}")
    public List<TweetDto> keywordSearch(@PathVariable String userInput) throws JsonProcessingException {
        WebClient client = WebClient.create("https://www.hashscraper.com/api/twitter/");
        String response = client.post()
                .uri("?apikey=" + api_key + "&keyword=" + userInput + "&max_count=10&")
                .header("Content-Type", "application/json version=2")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return freemiumService.getTop10TweetList(response);
    }

    @GetMapping("/hashscraperDateSearch/{start}/{end}")
    public String hashscraperDateSearch(@PathVariable String start, @PathVariable String end) throws JsonProcessingException {

        LocalDate start_date = LocalDate.of(
                Integer.parseInt(start.substring(0, 4)),
                Month.of(Integer.parseInt(start.substring(5, 7))),
                Integer.parseInt(start.substring(8))
        );
        LocalDate end_date = LocalDate.of(
                Integer.parseInt(end.substring(0, 4)),
                Month.of(Integer.parseInt(end.substring(5, 7))),
                Integer.parseInt(end.substring(8))
        );
        WebClient client = WebClient.create("https://www.hashscraper.com/api/twitter/");
        String response = client.post()
                .uri("?apikey=" + api_key + "&keyword=" + keyword + "&max_count=20" + "&start_date=" + start_date + "&end_date=" + end_date)
                .header("Content-Type", "application/json version=2")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        List<Tweet> tweetList = tweetService.hashscraperResponseBodyToTweetDeserializer(response);
        tweetService.saveTweetList(tweetList);
        return "Saved for Start date: " + start + "End date: " + end_date + "\nCurrent database size:" + tweetService.getAllTweets().size();
    }



    @GetMapping("/tweetDtosToHashtags")
    public List<Hashtag> tweetDtosToHashtags(String string) {
        List<TweetDto> tweetDtoList = null;
        HashMap<String, Hashtag> hashtagHashMap = new HashMap<>();
        List<Hashtag> hashtagList = hashtagService.tweetDtoListToHashtagList(tweetDtoList, hashtagHashMap);
        hashtagService.saveHashtagList(hashtagList);
        return hashtagList;
    }

    @GetMapping("/getAllHashtags")
    public List<Hashtag> getAllHashtags() {
        List<Hashtag> hashtagList = hashtagService.getAllHashtagsFromDatabase();
        hashtagList.sort(Comparator.comparing(Hashtag::getTimestamp).reversed());
        return hashtagList;
    }

    @GetMapping("/top5Hashtags")
    public List<HashtagDto> top5Hashtags() {
        List <Hashtag> hashtagList = getAllHashtags();
        List<HashtagDto> hashtagDtoList = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            hashtagDtoList.add(hashtagService.hashtagToHashtagDto(hashtagList.get(i)));
        }

        return hashtagDtoList;
    }

    @DeleteMapping("/clearHashtagDatabase")
    public void clearHashtagDatabase() {
        hashtagService.clearHashtagDatabase();
    }
}

