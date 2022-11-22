package hackathon.d2hd.getGoingApp.tweetServiceTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
class TweetServiceTests {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TweetService tweetService;
    @Test
    public void TestTweetToTweetDtoAPI() throws JsonProcessingException {
        List<Tweet> tweetList = tweetService.getAllTweets();
        Tweet tweet = tweetList.get(0);
        TweetDto tweetDto = tweetService.tweetToTweetDto(tweet);
        Assertions.assertNotNull(tweetDto);
        tweetList.forEach(tweet1 -> {
            System.out.println(tweet1.getValue1() + " " + tweet1.getValue15());
        });

        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetList);

    }
}
