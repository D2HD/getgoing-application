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
    private File hashScraperJsonFile = new File("/Users/seanmarinas/appetizer/getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperForSale.json");
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TweetService tweetService;

    @Test
    public void testJsonToTweetDeserializerAPI() throws IOException {
        //Check that the list is not null
        List<TweetDto> tweetDtoList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
        Assertions.assertNotNull(tweetDtoList);

        //Check that the size of tweetDtoList matches the testTweetDtoList
        JsonNode rootNode = objectMapper.readTree(hashScraperJsonFile);
        JsonNode dataNode = rootNode.get("data");
        List<Tweet> tweetList = objectMapper.readValue(dataNode.toString(), new TypeReference<List<Tweet>>() {});
        Assertions.assertEquals(tweetList.size(), tweetDtoList.size());
    }

    @Test
    public void testTweetJsonToGeneralSentimentAPI() throws IOException {
        List<TweetDto> tweetDtoList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
        List<Double> generalSentimentList = new ArrayList<>();
        tweetDtoList.forEach(tweetDto -> {
            generalSentimentList.add(tweetDto.getGeneral_sentiment());
        });
        generalSentimentList.sort(Comparator.naturalOrder());

        JsonNode rootNode = objectMapper.readTree(hashScraperJsonFile);
        JsonNode dataNode = rootNode.get("data");
        List<Tweet> tweetList = objectMapper.readValue(dataNode.toString(), new TypeReference<List<Tweet>>() {});
        List<Double> testGeneralSentimentList = new ArrayList<>();
        tweetList.forEach(tweet -> {
            try {
                testGeneralSentimentList.add(tweetService.tweetJsonToGeneralSentiment(tweet.getValue15()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        testGeneralSentimentList.sort(Comparator.naturalOrder());
        Assertions.assertEquals(testGeneralSentimentList, generalSentimentList);
    }
}
