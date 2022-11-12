package hackathon.d2hd.getGoingApp.hashtagServiceTests;

import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
public class HashtagServiceTests {

    private File hashScraperJsonFile = new File("/Users/seanmarinas/appetizer/getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperTestData.json");
    @Autowired
    private TweetService tweetService;
    @Autowired
    private HashtagService hashtagService;

    public HashtagServiceTests() throws IOException {
    }

    @Test
    public void testClearDatabaseAPI() {
        hashtagService.clearTopicDatabase();
    }

    @Test
    public void testCreateTopicIdAPI() throws IOException {
    //        TweetDto testObject = tweetService.tweetListToTweetDtoList(tweetService.JsonToTweetDeserializer(hashScraperJsonFile)).get(0);
    //
    //        String unformattedTopicId = testObject.getTopic() + testObject.getLocalDateTime();
    //        String formattedTopicId = hashtagService.createTopicId(testObject);
    //
    //        System.out.println("Unformatted Hashtag Id: " + unformattedTopicId);
    //        System.out.println("Formatted Hashtag Id: " +formattedTopicId);
    //
    //        Assertions.assertNotEquals(unformattedTopicId, formattedTopicId);
    }

    @Test
    public void testGetTopicListAPI() throws IOException {
//        tweetService.clearDatabase();
//
//        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetService.JsonToTweetDeserializer(hashScraperJsonFile));
//        HashMap<String, Hashtag> topicHashMap = hashtagService.getTopicHashmap(tweetDtoList);
//        List<Hashtag> topicList = hashtagService.getTopicList(topicHashMap);
//
//        long count = 0l;
//        for(Hashtag topic: topicList) {
//            System.out.println(topic.getTopic_id() + ": " + topic.getNum_of_occurrence() + "\n");
//            count += topic.getNum_of_occurrence();
//        }
//
//        System.out.println(count);
    }

    @Test
    public void testSortTopicsByNumOfOccurrenceAPI() throws IOException {
//        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetService.JsonToTweetDeserializer(hashScraperJsonFile));
//        List<Hashtag> unsortedTopicList = hashtagService.tweetDtoListToTopicList(tweetDtoList);
//        List<Hashtag> sortedTopicList = hashtagService.sortTopicsByNumOfOccurrence(hashtagService.tweetDtoListToTopicList(tweetDtoList));
//
//        System.out.println("Unsorted Hashtag List");
//        hashtagService.displayTopics(unsortedTopicList);
//
//        System.out.println("Sorted Hashtag List");
//        hashtagService.displayTopics(sortedTopicList);
//
//        Assertions.assertNotEquals(unsortedTopicList, sortedTopicList);
    }

    @Test
    public void testGetTop5Topics() throws IOException {
//        List<Tweet> hashscaperTweetList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
//        tweetService.saveTweetList(hashscaperTweetList);
//        List<Tweet> tweetRepositoryTweetList = tweetService.getAllTweetsFromDatabase();
//
//        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetRepositoryTweetList);
//        List<Hashtag> tweetDtoListToTopicListTopicList = hashtagService.tweetDtoListToTopicList(tweetDtoList);
//        hashtagService.saveTopicList(tweetDtoListToTopicListTopicList);
//        List<Hashtag> topicRepositoryList = hashtagService.getAllTopicsFromDatabase();
//        List<Hashtag> top5TopicList = hashtagService.getTodaysTopTopics(topicRepositoryList);
//        List<Hashtag> top5TopicComparisonList = top5TopicList;
//
//        long topicRepositoryListCount = topicRepositoryList.stream().count();
//        long top5TopicListCount = top5TopicList.stream().count();
//
//        Assertions.assertNotEquals(topicRepositoryListCount, top5TopicListCount);
//        Assertions.assertEquals(5l, top5TopicListCount);
    }


}
