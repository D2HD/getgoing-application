package hackathon.d2hd.getGoingApp.freemiumTests;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class FreemiumServiceTests {

    File file = new File("/Users/seanmarinas/appetizer/getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperForSale.json");

    @Autowired
    private TweetService tweetService;

    @Autowired
    private HashtagService hashtagService;

    @Test
    public void testFreemiumWorkflow() throws IOException {
        tweetService.clearDatabase();
        hashtagService.clearTopicDatabase();
        hashtagService.clearTopicDatabase();
        //Turn the JSON file into a TweetDto List
        List<TweetDto> tweetDtoList = tweetService.JsonToTweetDeserializer(file);

        //Create an empty Hashmap
        HashMap<String, Hashtag> hashtagHashMap = new HashMap<>();

//        List<Hashtag> hashtagList = hashtagService.tweetDtoListToHashtagList(tweetDtoList, hashtagHashMap);
//        hashtagList.forEach(hashtag -> {
//            System.out.println(hashtag.getTopic_id() + ": " + hashtag.getNum_of_occurrence());
//        });
    }
}
