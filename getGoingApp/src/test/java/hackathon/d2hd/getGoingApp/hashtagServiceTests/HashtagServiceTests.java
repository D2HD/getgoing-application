package hackathon.d2hd.getGoingApp.hashtagServiceTests;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
    public void testHashtagToHashtagDtoList() throws IOException {
        List<Hashtag> hashtagList = hashtagService.getAllHashtagsFromDatabase();
        hashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());
        List<HashtagDto> hashtagDtoList = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            hashtagDtoList.add(hashtagService.hashtagToHashtagDto(hashtagList.get(i)));
        }

        hashtagService.displayHashtagDtos(hashtagDtoList);
    }
}
