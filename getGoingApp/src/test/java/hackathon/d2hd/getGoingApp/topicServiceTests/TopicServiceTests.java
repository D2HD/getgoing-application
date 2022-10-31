package hackathon.d2hd.getGoingApp.topicServiceTests;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.TopicService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class TopicServiceTests {

    private File hashScraperJsonFile = new File("/Users/seanmarinas/appetizer/getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperTestData.json");

    @Autowired
    private TweetService tweetService;
    @Autowired
    private TopicService topicService;

    public TopicServiceTests() throws IOException {
    }

    @Test
    public void testCreateTopicId() throws IOException {
        TweetDto testObject = tweetService.tweetListToTweetDtoList(tweetService.JsonToTweetDeserializer(hashScraperJsonFile)).get(0);

        String unformattedTopicId = testObject.getTopic() + testObject.getLocalDateTime();
        String formattedTopicId = topicService.createTopicId(testObject);

        System.out.println("Unformatted Topic Id: " + unformattedTopicId);
        System.out.println("Formatted Topic Id: " +formattedTopicId);

        Assertions.assertNotEquals(unformattedTopicId, formattedTopicId);
    }

    @Test
    public void testGetTopics() throws IOException {
        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetService.JsonToTweetDeserializer(hashScraperJsonFile));
        HashMap<String, Topic> topicHashMap = topicService.getTopics(tweetDtoList);

        topicHashMap.forEach((s, topic) -> {
            System.out.println();
            System.out.println("Topic: " + s);
            System.out.println("Occurrence Count: " + topic.getNum_of_occurrence());
            System.out.println("Like Count: " + topic.getLike_count());
            System.out.println("Retweet Count: " + topic.getRetweet_count());
            System.out.println("Quote Tweet Count: " + topic.getQuote_tweet_count());
            System.out.println();
        });
     }
}
