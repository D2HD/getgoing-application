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

/**
 * Implementation of TweetService.
 * <p />
 * Contains methods obtain and/or convert a {@link Tweet} to other datatypes.
 */
@Service
public class TweetServiceImpl implements TweetService {

    /** Compared with a parameter that <b>contains</b> its element. */
    private final static String[] blacklistWords = {
        "anal", "anus", "arse", "ass",
        "balls", "ballsack", "bastard", "biatch",
        "bitch", "bloody", "blow job", "blowjob",
        "bollock", "bollok", "boner", "boob",
        "bugger", "bum", "butt",
        "clitoris", "cock", "coon", "crap", "cunt",
        "damn", "dick", "dildo", "dyke",
        "fag", "feck", "felching", "fellate", "fellatio", "flange",
        "fuck", "fudge packer", "fudgepacker",
        "goddamn",
        "hell", "homo",
        "jerk", "jizz",
        "knob end", "knobend",
        "labia", "lmao", "lmfao",
        "muff",
        "nigga", "nigger",
        "omg",
        "penis", "piss", "poop", "prick", "pube", "pussy",
        "queer", "scrotum",
        "sex", "sh1t", "shit", "slut", "smegma", "spunk",
        "tit", "tosser", "turd", "twat",
        "vagina",
        "wank", "whore", "wtf"
    };
    /** Compared with a parameter that <b>matches</b> its element. */
    private final static String[] blacklistWordsExact = {
        "collectors", "hi", "fyp", "sup"
    };

    /** Reads JSON: Hashscraper{} and Hashscraper.data{} */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /** Execute SQL statements. */
    @Autowired
    private TweetRepository tweetRepository;

    /**
     * Converts Hashcraper response body (JSON) into a String,
     * parses the String into a list of Tweets
     * @param  hashscraperResponseBody A maximum of 10 JSON of tweets within the 'data' of rootNode.
     * @return A list of Tweets (maximum 10) obtained from the Hashscraper response body.
     * @throws JsonProcessingException Error when parsing JSON String into individual String values for the {@link Tweet} object.
     */
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

    /**
     * @param tweet A Tweet object (every field is a string)
     * @return A TweetDto object (every field is its appropriate datatype)
     * @throws JsonProcessingException Error when parsing JSON String into individual String values for the {@link Tweet} object.
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
     * Converts value14 of a Tweet object into a LocalDateTime object.
     * <p />
     * After parsing, there will be a 'T' in the gap between 'dd' and 'HH' indicating the time.
     * Example:
     * <p />
     * Original formattedString:  {@code "2022-10-31 16:05:39"}
     * Converted formattedString: {@code "2022-10-31T16:05:39"}
     * The 'T' doesn't affect the workflow of the application.
     * @param localDateTimeString String, Format: '2022-11-06 15:49:38 +0900'.
     * @return LocalDateTime, Format: '2022-10-31T16:05:39'.
     */
    @Override
    public LocalDateTime stringToLocalDateTime(String localDateTimeString) {
        String formattedString = localDateTimeString.substring(0, localDateTimeString.length()-6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(formattedString, formatter);
    }

    /**
     * @param tweetList A list of Tweet objects (every field is a string)
     * @return A list of TweetDto objects (every field is its appropriate datatype)
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
     * Extract every unique and valid hashtag from a user's tweet.
     * Hashtags that contain/are words from the blacklist are skipped.
     * The regular expression used matches a single pound/hash sign,
     * followed by one or more alphanumeric/underscore character.
     * @param content The user's tweet, maximum 280 characters.
     * @return List of hashtag(s) fulfilling the specifications in lowercase.
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
            for (String profanity : blacklistWords) {
                if (hashtag.contains(profanity)) {
                    // Do not include profanities in the list.
                    continue processEachHashtag;
                }
            }
            for (String word : blacklistWordsExact) {
                if (hashtag.equals(word)) {
                    // Do not include exact matches of any element of blacklistWordsExact.
                    continue processEachHashtag;
                }
            }
            hashtagList.add(hashtag);
        }
        return hashtagList;
    }

    /**
     * Extract the general sentiment of a tweet.
     * Computes the average for tweets with multiple general sentiments.
     * @param tweet_json value15 of a Tweet object.
     * @see Tweet#getValue15() Tweet.value15
     * @return The general sentiment of a tweet, value ranges from -1.0 to 1.0
     * @throws JsonProcessingException Error when parsing JSON String into individual String values for the {@link Tweet} object.
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

    /** @return Total number of tweets saved in the database. */
    @Override
    public int tweetDatabaseSize() {
        return tweetRepository.findAll().size();
    }

    /** @return List of every Tweet from the database. */
    @Override
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    /** Empties the database. */
    @Override
    public void clearTweetDatabase(){
        tweetRepository.deleteAll();
    }

    /** Performs the SQL insert into the database of the given parameters. */
    @Override
    public void saveTweetList(List<Tweet> tweetList) {
        tweetRepository.saveAll(tweetList);
    }
}
