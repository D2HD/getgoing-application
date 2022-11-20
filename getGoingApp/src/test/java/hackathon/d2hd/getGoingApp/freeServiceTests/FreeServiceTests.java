package hackathon.d2hd.getGoingApp.freeServiceTests;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.FreeService;
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
public class FreeServiceTests {

    @Autowired
    private FreeService freeService;

    @Test
    public void testTweetDtoListToPremiumHashtagList() throws IOException {
        String response = freeService.hashscraperCall("cats");
        List<TweetDto> tweetDtoList = freeService.keywordSearchToTweeDtoList(response);
        System.out.println(tweetDtoList.size());

    }
}
