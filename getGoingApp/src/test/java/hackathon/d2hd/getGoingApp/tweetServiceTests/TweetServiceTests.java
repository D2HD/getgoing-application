package hackathon.d2hd.getGoingApp.tweetServiceTests;

import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootTest
class TweetServiceTests {

    private File hashScraperJsonFile = new File("/Users/seanmarinas/appetizer/getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperTestData.json");
    @Autowired
    private TweetService tweetService;

    @Test
    public void testJsonToTweetDeserializerAPI() throws IOException {
        List<Tweet> tweetList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
        tweetList.forEach(tweet -> {
            System.out.println(tweet.toString());
        });
    }

    @Test
    public void testSaveTweetList() throws IOException {
        tweetService.clearDatabase();

        //this is the tweetList from the raw data from hashscraper
        List<Tweet> hashscraperTweetList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
        tweetService.saveTweetList(hashscraperTweetList);
        long hashscraperTweetListCount = hashscraperTweetList.stream().count();

        //after saving the tweets to the repo, I performed a pull of tall the tweets from the database
        List<Tweet> tweetRepositoryTweetList = tweetService.getAllTweetsFromDatabase();
        long tweetRepositoryTweetListCount = tweetRepositoryTweetList.stream().count();

        /*
        - I realised that the number of tweets from both lists are different
        - my immediate assumption was that there were probably duplicates of tweets in hashscraperTweetList
        - the number differs from tweetRepositoryTweetList because when a save to the repo is performed, duplicates will not be added
        - i checked this by displaying the a key value pair from a hashmap as seen below
        - the hashmap maps the url of the tweet to the number of times it has appeared in the repo
         */

        Assertions.assertNotEquals(hashscraperTweetListCount, tweetRepositoryTweetListCount);

        HashMap<String, Long> hs = new HashMap<>();
        hashscraperTweetList.forEach(tweet -> {
            String url = tweet.getValue1();
            if(!hs.containsKey(url)) {
                hs.put(url, 1L);
            }else{
                hs.put(url, hs.get(url) + 1);
            }
        });

        //as seen from the code below, some urls have more than 1 occurence
        hs.forEach((s, aLong) -> {
            System.out.println(s + ": " + aLong);
        });

        //i created a duplicateCount that increments by one when a url has more than 1 ocurrence
        AtomicLong duplicateCount = new AtomicLong();
        hs.forEach((s, aLong) -> {
            if(aLong > 1l) {
                duplicateCount.getAndIncrement();
            }
        });

        //i compared to the duplicateCount to the difference in counts between hashscraperTweetListCount and tweetRepositoryTweetListCount
        Assertions.assertEquals((hashscraperTweetListCount - tweetRepositoryTweetListCount), Long.parseLong(String.valueOf(duplicateCount.get())));

        tweetService.clearDatabase();
    }

    @Test
    public void testTweetListToTweetDtoListAPI() throws IOException {
        List <Tweet> tweetList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
        long tweetListCount = tweetList.stream().count();

        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetList);
        long tweetDTOListCount = tweetDtoList.stream().count();

        Assertions.assertEquals(tweetListCount, tweetDTOListCount);
    }
}
