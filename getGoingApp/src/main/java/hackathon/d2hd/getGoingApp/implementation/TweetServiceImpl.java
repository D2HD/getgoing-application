package hackathon.d2hd.getGoingApp.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.repository.TweetRepository;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public List<Tweet> JsonToTweetDeserializer(File jsonFile) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonFile);
        JsonNode dataNode = rootNode.get("data");
        return  objectMapper.readValue(dataNode.toString(), new TypeReference<List<Tweet>>() {});
    }

    @Override
    public void saveTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    @Override
    public void saveTweetList(List<Tweet> tweetList) {
        tweetRepository.saveAll(tweetList);
    }

    public List<Tweet> getAllTweetsFromDatabase() {
        return tweetRepository.findAll();
    }

    @Override
    public void clearDatabase(){
        tweetRepository.deleteAll();
    }

    @Override
    public TweetDto tweetToTweetDto(Tweet tweet) {
        return new TweetDto(
                tweet.getValue1(),
                tweet.getValue2().replace(" ", ""),
                tweet.getValue3(),
                Long.parseLong(tweet.getValue4()),
                tweet.getValue5(),
                tweet.getValue6(),
                tweet.getValue7(),
                Long.parseLong(tweet.getValue8()),
                Long.parseLong(tweet.getValue9()),
                Long.parseLong(tweet.getValue10()),
                Long.parseLong(tweet.getValue11()),
                tweet.getValue12(),
                tweet.getValue13(),
                stringToLocalDateTime(tweet.getValue14()),
                tweet.getValue15()
        );
    }

    @Override
    public LocalDateTime stringToLocalDateTime(String localDateTimeString) {
        String formattedString = localDateTimeString.substring(0, localDateTimeString.length()-6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        /*
        - after parsing, there will be a 'T' in the gap between 'dd' and 'HH' indicating time
        - for example if the original formattedString was "2022-10-31 16:05:39"
        - it would be converted to "2022-10-31T16:05:39"
        - doesn't affect the workflow much but just something to keep in mind
         */
        return LocalDateTime.parse(formattedString, formatter);
    }

    @Override
    public List<TweetDto> tweetListToTweetDtoList(List<Tweet> tweetList) {
        List<TweetDto> tweetDtoList = new ArrayList<>();

        tweetList.forEach(tweet -> {
            tweetDtoList.add(tweetToTweetDto(tweet));
        });

        return tweetDtoList;
    }
}
