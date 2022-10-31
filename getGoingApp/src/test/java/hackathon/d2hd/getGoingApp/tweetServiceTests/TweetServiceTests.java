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
import java.util.List;

// TODO: 31/10/22 Seventh Change
/*
- so i created a class that just tests for anything withing my TweetService
- annotate the classes with @SpringBootTest
- include your service, in this case would be TweetService and annotate it with @AutoWired
- you will see that I have an instance variable of a file, this file contains some return data from Hashscraper
- the file is located in getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperTestData.json
- look for Eigth Change to see the test for the deserializer API
 */
@SpringBootTest
class TweetServiceTests {

    private File hashScraperJsonFile = new File("/Users/seanmarinas/appetizer/getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperTestData.json");
    @Autowired
    private TweetService tweetService;

    // TODO: 31/10/22 Eighth Change
    /*
    - a test is a method, so its signature can be anything you want
    - for tests in Spring, annotate it with @Test
    - for my test i just called the deserializer API which returns a list of Tweets
    - then i printed each element to ensure it isn't empty, alternatively i could've used assertEquals which you'll see in the Ninth Change
    - look for Ninth Change to see my Unit test for the that turns a Tweet to a TweetDto
     */
    @Test
    public void testJsonToTweetDeserializerAPI() throws IOException {
        List<Tweet> tweetList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
        tweetList.forEach(tweet -> {
            System.out.println(tweet.toString());
        });
    }

    // TODO: 31/10/22 Ninth Change
    /*
    - i called the deserializer which returns a list of tweets
    - i counted the number of tweets and stored it in a tweetListCount variable
    - next i made an empty list of TweetDTOs, which i will append in the next line'
    - for each tweet in the tweet list, i called the tweetToTweetDTO API which returns a TweetDto and added it to the tweetDTOList
    - i counted the number of elements in the TweetDto list
    - the i used Assertions.assertEquals which accepts 2 parameters, in this case tweetListCount and tweetDTOListCount which will either pass or fail the test depending if both variables are equal
     */
    @Test
    public void testTweetListToTweetDtoListAPI() throws IOException {
        List <Tweet> tweetList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
        long tweetListCount = tweetList.stream().count();

        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetList);
        long tweetDTOListCount = tweetDtoList.stream().count();

        Assertions.assertEquals(tweetListCount, tweetDTOListCount);
    }
}
