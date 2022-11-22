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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TweetRepository tweetRepository;

    /**
     * Converts the String of the response body from the Hashscraper API call and converts it into a list of Tweet objects that
     * will be saved into the TweetRepository
     * @param hashscraperResponseBody
     * @return
     * A list of Tweet objects based on the data passed through from the response body that has been passed through as a parameter
     * @throws JsonProcessingException
     */
    @Override
    public List<Tweet> hashscraperResponseBodyToTweetDeserializer(String hashscraperResponseBody) throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(hashscraperResponseBody);
        JsonNode dataNode = rootNode.get("data");
        List<Tweet> tweetList = objectMapper.readValue(dataNode.toString(), new TypeReference<List<Tweet>>() {})
                .stream()
                .distinct()
                .collect(Collectors.toList());

        tweetRepository.saveAll(tweetList);
        return tweetList;
    }

    /**
     * Deletes all the Tweet objects from the Tweet database
     */
    @Override
    public void clearTweetDatabase(){
        tweetRepository.deleteAll();
    }

    /**
     * Converts the Tweet object that has been passed through as a parameter into a TweetDto object
     * @param tweet
     * @return
     * A TweetDto object
     * @throws JsonProcessingException
     */
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

    /**
     * Converts the string passed from the parameter into a LocalDateTime object
     * @param localDateTimeString
     * @return
     * LocalDateTime object based on the String localDateTime parameter
     */
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

    /**
     * Converts a list of Tweet objects into a list of TweetDto objects
     * @param tweetList
     * @return
     * A list of TweetDtos
     */
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

    /**
     * @param content The user's tweet, maximum 280 characters
     * @return List of hashtag(s) fulfilling the specifications in lowercase
     */
    @Override
    public List<String> getHashtagList(String content) {
        Matcher matcher = Pattern.compile("\u0023\\w+").matcher(content);
        List<String> hashtagList = new ArrayList<>();
        // Get every hashtag in lowercase, regardless of what it is.
        processEachHashtag:
        while (matcher.find()) {
            String hashtag = matcher.group().toLowerCase();
            // Skip duplicate hashtag.
            if (hashtagList.contains(hashtag)) continue;
            // Filter the hashtag.
            // TODO: Expand the blacklist, to exclude impertinent words.
            String[] blacklistWords = {"anal", "anus", "arse", "ass",
                    "balls", "ballsack", "bastard", "biatch",
                    "bitch", "bloody", "blow job", "blowjob",
                    "bollock", "bollok", "boner", "boob",
                    "bugger", "bum", "butt",
                    "clitoris", "cock", "coon", "crap",
                    "cunt", "damn", "dick", "dildo",
                    "dyke", "fag", "feck",
                    "felching", "fellate", "fellatio", "flange",
                    "fuck", "fudge packer", "fudgepacker",
                    "Goddamn", "hell", "homo", "jerk",
                    "jizz", "knob end", "knobend", "labia",
                    "lmao", "lmfao", "muff", "nigga",
                    "nigger", "omg", "penis", "piss",
                    "poop", "prick", "pube", "pussy",
                    "queer", "scrotum", "sex",
                    "sh1t", "shit", "slut", "smegma",
                    "spunk", "tit", "tosser", "turd",
                    "twat", "vagina", "wank", "whore",
                    "wtf", "forsale", "ebay", "selling", "dealoftheday"
            };
            for (String profanity : blacklistWords) {
                // Do not include profanities in the list.
                if (hashtag.contains(profanity)) continue processEachHashtag;
            }
            // Hashtag is not a profanity, include in the list.
            hashtagList.add(hashtag);
        }
        return hashtagList;
    }

    /**
     * @param tweet_json value15 of a Tweet object.
     * @see Tweet#getValue15() Tweet.value15
     * @return The general sentiment of a tweet, value ranges from -1.0 to 1.0
     * @throws JsonProcessingException
     */
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

    /**
     * Gets the current size of the Tweet database
     * @return
     * An integer value based on the Tweet database size
     */
    @Override
    public int tweetDatabaseSize() {
        return tweetRepository.findAll().size();
    }

    /**
     * Gets a list of all Tweet objects from the Tweet Database
     * @return
     * A List of Tweets from the Tweet database
     */
    @Override
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    /**
     * Saves all the tweets passed through from the parameter into the Tweet database
     * @param tweetList
     */
    @Override
    public void saveTweetList(List<Tweet> tweetList) {
        tweetRepository.saveAll(tweetList);
    }
}