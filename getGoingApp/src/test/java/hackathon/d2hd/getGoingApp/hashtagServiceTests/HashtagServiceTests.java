package hackathon.d2hd.getGoingApp.hashtagServiceTests;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.repository.HashtagRepository;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
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

    @Autowired
    private HashtagRepository hashtagRepository;

    @Test
    public void testTweetDtoListToHashtagListAPI() throws IOException {
        List<TweetDto> tweetDtoList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
        HashMap<String, Hashtag> hashtagHashMap = new HashMap<>();
        List<Hashtag> hashtagList = hashtagService.tweetDtoListToHashtagList(tweetDtoList, hashtagHashMap);
        hashtagService.displayHashtags(hashtagList);
    }

    @Test
    public void testSevenDayTop5HashtagListByCountAPI() {
        LocalDate currentDateTime = LocalDate.now();
        List<Hashtag> hashtagList = hashtagService.sevenDayTop5HashtagListByCount(currentDateTime);
        hashtagService.displayHashtags(hashtagList);
    }

    @Test
    public void testSevenDayTop5HashtagListByLikeAPI() {
        LocalDate currentDateTime = LocalDate.now();
        List<Hashtag> hashtagList = hashtagService.sevenDayTop5HashtagListByLike(currentDateTime);
        hashtagService.displayHashtags(hashtagList);
    }

    @Test
    public void currentTop5HashtagListAPI() {
        LocalDate now = LocalDate.now().minusDays(4L);
        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampIs(now);
        hashtagService.displayHashtags(hashtagList);
    }

    @Test
    public void hashtagRepoMethodsTest() {
        LocalDate start = LocalDate.of(2022, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2022, Month.DECEMBER, 31);
        List<Hashtag> hashtagList1 = hashtagRepository.findAllByTimestampBetween(start, start);

        hashtagService.displayHashtags(hashtagList1);
    }

    @Test
    public void testGetTopicOccurrenceHistory() {
        List<Hashtag> hashtagList = hashtagService.getAllHashtagsFromDatabase();
        hashtagList.sort(Comparator.comparing(Hashtag::getTimestamp).reversed());

        List<Long> topicOccurrence = hashtagService.getTopicOccurrenceHistory(hashtagList.get(0));
        System.out.println(topicOccurrence);
    }

    @Test
    public void testStringTheSame() {
        String s = "hello";
        String v = "bye";

        Assertions.assertEquals(s,v);
    }

}
