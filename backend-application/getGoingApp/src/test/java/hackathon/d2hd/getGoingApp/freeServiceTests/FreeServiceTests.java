package hackathon.d2hd.getGoingApp.freeServiceTests;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.FreeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class FreeServiceTests {

    @Autowired
    private FreeService freeService;

    @Test
    public void testTweetDtoListToPremiumHashtag() throws IOException {
        String response = freeService.hashscraperCall("cats");
        System.out.println(response);
        List<TweetDto> tweetDtoList = freeService.keywordSearchToTweeDtoList(response);
        for(TweetDto tweetDto: tweetDtoList) {
            System.out.println(tweetDto.toString());
        }
        HashtagDto hashtag = freeService.tweetDtoListToPremiumHashtag(tweetDtoList);
        System.out.println(hashtag.toString());
    }
}
