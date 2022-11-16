package hackathon.d2hd.getGoingApp.hashtagServiceTests;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class HashtagServiceTests {

    private File hashScraperJsonFile = new File("/Users/seanmarinas/appetizer/getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperForSale.json");
    @Autowired
    private TweetService tweetService;
    @Autowired
    private HashtagService hashtagService;

    @Test
    public void testTweetDtoListToHashtagListAPI() throws IOException {
        List<TweetDto> tweetDtoList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
        HashMap<String, Hashtag> hashtagHashMap = new HashMap<>();
        List<Hashtag> hashtagList = hashtagService.tweetDtoListToHashtagList(tweetDtoList, hashtagHashMap);
        hashtagService.displayHashtags(hashtagList);
    }

    @Test
    public void testSevenDayTop5HashtagListByCountAPI() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Hashtag> hashtagList = hashtagService.sevenDayTop5HashtagListByCount(currentDateTime);
        hashtagService.displayHashtags(hashtagList);
    }

    @Test
    public void testSevenDayTop5HashtagListByLikeAPI() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Hashtag> hashtagList = hashtagService.sevenDayTop5HashtagListByLike(currentDateTime);
        hashtagService.displayHashtags(hashtagList);
    }

    @Test
    public void currentTop5HashtagListAPI() {
    }
}
