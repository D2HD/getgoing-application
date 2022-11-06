package hackathon.d2hd.getGoingApp.topicServiceTests;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.TopicService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
    public void testClearDatabaseAPI() {
        topicService.clearTopicDatabase();
    }

    @Test
    public void testCreateTopicIdAPI() throws IOException {
        TweetDto testObject = tweetService.tweetListToTweetDtoList(tweetService.JsonToTweetDeserializer(hashScraperJsonFile)).get(0);

        String unformattedTopicId = testObject.getTopic() + testObject.getLocalDateTime();
        String formattedTopicId = topicService.createTopicId(testObject);

        System.out.println("Unformatted Topic Id: " + unformattedTopicId);
        System.out.println("Formatted Topic Id: " +formattedTopicId);

        Assertions.assertNotEquals(unformattedTopicId, formattedTopicId);
    }

    @Test
    public void testGetTopicListAPI() throws IOException {
        tweetService.clearDatabase();

        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetService.JsonToTweetDeserializer(hashScraperJsonFile));
        HashMap<String, Topic> topicHashMap = topicService.getTopicHashmap(tweetDtoList);
        List<Topic> topicList = topicService.getTopicList(topicHashMap);

        long count = 0l;
        for(Topic topic: topicList) {
            System.out.println(topic.getTopic_id() + ": " + topic.getNum_of_occurrence() + "\n");
            count += topic.getNum_of_occurrence();
        }

        System.out.println(count);
    }

    @Test
    public void testSortTopicsByNumOfOccurrenceAPI() throws IOException {
        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetService.JsonToTweetDeserializer(hashScraperJsonFile));
        List<Topic> unsortedTopicList = topicService.tweetDtoListToTopicList(tweetDtoList);
        List<Topic> sortedTopicList = topicService.sortTopicsByNumOfOccurrence(topicService.tweetDtoListToTopicList(tweetDtoList));

        System.out.println("Unsorted Topic List");
        topicService.displayTopics(unsortedTopicList);

        System.out.println("Sorted Topic List");
        topicService.displayTopics(sortedTopicList);

        Assertions.assertNotEquals(unsortedTopicList, sortedTopicList);
    }

    @Test
    public void testGetTop5Topics() throws IOException {
        List<Tweet> hashscaperTweetList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
        tweetService.saveTweetList(hashscaperTweetList);
        List<Tweet> tweetRepositoryTweetList = tweetService.getAllTweetsFromDatabase();

        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetRepositoryTweetList);
        List<Topic> tweetDtoListToTopicListTopicList = topicService.tweetDtoListToTopicList(tweetDtoList);
        topicService.saveTopicList(tweetDtoListToTopicListTopicList);
        List<Topic> topicRepositoryList = topicService.getAllTopicsFromDatabase();
        List<Topic> top5TopicList = topicService.getTop5Topics(topicRepositoryList);
        List<Topic> top5TopicComparisonList = top5TopicList;

        long topicRepositoryListCount = topicRepositoryList.stream().count();
        long top5TopicListCount = top5TopicList.stream().count();

        Assertions.assertNotEquals(topicRepositoryListCount, top5TopicListCount);
        Assertions.assertEquals(5l, top5TopicListCount);
    }


}
