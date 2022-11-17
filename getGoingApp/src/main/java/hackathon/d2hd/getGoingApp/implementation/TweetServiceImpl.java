package hackathon.d2hd.getGoingApp.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public List<Tweet> hashscraperResponseBodyToTweetDeserializer(String hashscraperResponseBody) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(hashscraperResponseBody);
        JsonNode dataNode = rootNode.get("data");
        List<Tweet> tweetList = objectMapper.readValue(dataNode.toString(), new TypeReference<List<Tweet>>() {})
                .stream()
                .distinct()
                .collect(Collectors.toList());
        return tweetList;
    }

    @Override
    public List<TweetDto> JsonToTweetDeserializer(File jsonFile) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonFile);
        JsonNode dataNode = rootNode.get("data");
        List<Tweet> tweetList = objectMapper.readValue(dataNode.toString(), new TypeReference<List<Tweet>>() {});
        tweetRepository.saveAll(tweetList);
        return tweetListToTweetDtoList(tweetRepository.findAll());
    }

    @Override
    public void clearTweetDatabase(){
        tweetRepository.deleteAll();
    }

    @Override
    public TweetDto tweetToTweetDto(Tweet tweet) throws JsonProcessingException {
        return new TweetDto(
                tweet.getValue1(),
                tweet.getValue2().toLowerCase(),
                tweet.getValue3(),
                Long.parseLong(tweet.getValue4()),
                tweet.getValue5(),
                tweet.getValue7(),
                getHashtagList(tweet.getValue7()),
                Long.parseLong(tweet.getValue8()),
                Long.parseLong(tweet.getValue9()),
                Long.parseLong(tweet.getValue10()),
                Long.parseLong(tweet.getValue11()),
                stringToLocalDateTime(tweet.getValue14()),
                tweetJsonToGeneralSentiment(tweet.getValue15())
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
            try {
                tweetDtoList.add(tweetToTweetDto(tweet));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        return tweetDtoList;
    }

    @Override
    public List<String> getHashtagList(String content) {
        // TODO: 17/11/22 String builder
        //Get the content of the tweet
        List<String> hashtagList = Arrays.asList(content.split(" "));
        List<String> filteredHashtagList = new ArrayList<>();

        hashtagList.forEach(s -> {
            if(s.contains("#")) filteredHashtagList.add(s.toLowerCase());
        });

//        return filteredHashtagList;
        return hashtagList;
    }

    @Override
    public Double tweetJsonToGeneralSentiment(String tweet_json) throws JsonProcessingException {
        if(tweet_json.length() < 10) return Double.valueOf(0.0);
        JsonNode rootNode = objectMapper.readTree(tweet_json);
        List<TweetDto.TweetJson> tweetJsonList = objectMapper.readValue(tweet_json, new TypeReference<List<TweetDto.TweetJson>>() {});
        Double totalSentiment = Double.valueOf(0.0);

        for(TweetDto.TweetJson tweetJson: tweetJsonList) {
            totalSentiment += tweetJson.getScore();
        }

        return totalSentiment / tweetJsonList.size();
    }

    @Override
    public int tweetDatabaseSize() {
        return tweetRepository.findAll().size();
    }
    @Override
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    @Override
    public void saveTweetList(List<Tweet> tweetList) {
        tweetRepository.saveAll(tweetList);
    }
}
