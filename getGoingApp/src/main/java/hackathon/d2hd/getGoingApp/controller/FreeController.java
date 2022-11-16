package hackathon.d2hd.getGoingApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private HashtagService hashtagService;

    @DeleteMapping("/clearTweetDatabase")
    public void clearDatabase() {
        tweetService.clearTweetDatabase();
    }

    @GetMapping("/tweetDatabaseSize")
    public int databaseSize() {
        return tweetService.tweetDatabaseSize();
    }

    /**
     * The following 3 APIs will be the ones passed to Frontend
     * 1. currentTop5HashtagDtoList
     * 2. sevenDayTop5HashtagDtoListByCount returns a Hashtag DTO list containing 5 HashtagDTO objects, sorted by num_of_occurrence in descending order
     * 3. sevenDayTop5HashtagDtoListByLike returns a Hashtag DTO list containing 5 HashtagDTO objects, sorted by like in descending order
     */

    @GetMapping("/currentTop5HashtagDtoList")
    public List<HashtagDto> currentTop5HashtagDtoList() {
        LocalDate localDateTimeToday = LocalDate.now();
        LocalDate localDateTimeYesterday = localDateTimeToday.minusDays(3L);
        List<Hashtag> currentTop5HashtagList = hashtagService.currentTop5HashtagList(localDateTimeYesterday);

        return hashtagService.hashtagListToHashtagDtoList(currentTop5HashtagList);
    }
    @GetMapping("/sevenDayTop5HashtagListByCount")
    public List<HashtagDto> sevenDayTop5HashtagDtoListByCount() {
        LocalDate currentDateTime = LocalDate.now();
        List <Hashtag> hashtagList = hashtagService.sevenDayTop5HashtagListByCount(currentDateTime);
        return hashtagService.hashtagListToHashtagDtoList(hashtagList);
    }

    @GetMapping("/sevenDayTop5HashtagListByLike")
    public List<HashtagDto> sevenDayTop5HashtagDtoListByLike() {
        LocalDate currentDateTime = LocalDate.now();
        List <Hashtag> hashtagList = hashtagService.sevenDayTop5HashtagListByLike(currentDateTime);
        return hashtagService.hashtagListToHashtagDtoList(hashtagList);
    }


    //APIs for BE
    @GetMapping()
    public String hashscraperCall() {
        WebClient client = WebClient.create("https://www.hashscraper.com/api/twitter/");
        String response = client.post()
                .uri("?apikey=" + api_key + "&keyword=%23forsale&max_count=20&")
                .header("Content-Type", "application/json version=2")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }
    @GetMapping("/hashscraperToTweetDtoList")
    public List<TweetDto> tweetDtoListToday() throws JsonProcessingException {
        String hashscraperResponseBody = hashscraperCall();
        List<Tweet> tweetList = tweetService.hashscraperResponseBodyToTweetDeserializer(hashscraperResponseBody);
        tweetService.saveTweetList(tweetList);

        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetList);
        return tweetDtoList;
    }

    @GetMapping("/getAllTweetDtos")
    public List<TweetDto> getAllTweetDtos() {
        List <Tweet> tweetList = tweetService.getAllTweets();
        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetList);
        tweetDtoList.sort(Comparator.comparing(TweetDto::getLocalDateTime).reversed());

        return tweetDtoList;
    }

    @GetMapping("/tweetDtosToHashtags")
    public List<Hashtag> tweetDtosToHashtags() {
        List<TweetDto> tweetDtoList = getAllTweetDtos();
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

