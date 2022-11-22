package hackathon.d2hd.getGoingApp.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.FreeService;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Implementation of features used in GetGoing_ free plan.
 * @author Sean M.
 * @author Atharva D.
 * @author Baraq K.
 */
@Service
public class FreeServiceImpl implements FreeService {
    /** Parameter for {@link #hashscraperCall}, obtained from Spring application.properties */
    @Value("${api_key}")
    private String api_key;

    /** Parameter for {@link #hashscraperCall}, obtained from Spring application.properties */
    @Value("${keyword}")
    private String keyword;

    /** @see TweetService */
    @Autowired
    private TweetService tweetService;

    /** @see TweetService */
    @Autowired
    private HashtagService hashtagService;

    /**
     * @return The top five hashtags based on the number of occurrence.
     */
    @Override
    public List<HashtagDto> getTop5HashtagDtos(){
        List<Hashtag> todaysTop5HashtagList = hashtagService.getTodaysTop5Hashtags();
        List<HashtagDto> hashtagDtoList = hashtagService.hashtagListToHashtagDtoList(todaysTop5HashtagList);
        hashtagDtoList.sort(Comparator.comparing(HashtagDto::getNum_of_occurrence).reversed());

        return hashtagDtoList;
    }

    /**
     * Performs a keyword search using {@link TweetService#hashscraperResponseBodyToTweetDeserializer}
     * to obtain a list of TweetDto objects.
     * @param  response The response body of Hashscraper, a String JSON.
     * @return A list of TweetDto objects.
     * @throws JsonProcessingException Error when parsing JSON String into individual String values for the {@link Tweet} object.
     */
    @Override
    public List<TweetDto> keywordSearchToTweeDtoList(String response) throws JsonProcessingException {
        List<Tweet> tweetList = tweetService.hashscraperResponseBodyToTweetDeserializer(response);
        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetList);
        tweetDtoList.sort(Comparator.comparing(TweetDto::getTweet_retweet_count).reversed());

        return tweetDtoList;
    }

    /**
     * Converts a String representation of a date to the LocalDate object.
     * @param stringToLocalDate As used in {@link #hashscraperDateCall}
     * @return YYYY-MM-DD representation of a date in LocalDate.
     */
    @Override
    public LocalDate stringToLocalDate(String stringToLocalDate) {
        return LocalDate.of(
                Integer.parseInt(stringToLocalDate.substring(0, 4)),
                Month.of(Integer.parseInt(stringToLocalDate.substring(5, 7))),
                Integer.parseInt(stringToLocalDate.substring(8))
        );

    }


    /**
     * Performs a POST request to Hashscraper's API to obtain the tweets as specified by the keyword.
     * <p />
     * This method is limited to 10 tweets to prevent a timeout.
     * @param keyword A specifier for the type of tweets searched by Hashscraper.
     * @return A tweet JSON with a maximum of 10 tweets.
     */
    @Override
    public String hashscraperCall(String keyword) {
        WebClient client = WebClient.create("https://www.hashscraper.com/api/twitter/");
        String response = client.post()
                .uri("?apikey=" + api_key + "&keyword=" + keyword + "&max_count=10&")
                .header("Content-Type", "application/json version=2")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

    /**
     * Performs {@link #hashscraperCall(String) hashscraperCall}
     * with the date parameters.
     * @param start The start date for Hashscraper to obtain the tweets.
     * @param end Then end date for Hashscraper to obtain the tweets.
     * @return A tweet JSON with a maximum of 10 tweets.
     */
    @Override
    public String hashscraperDateCall(String start, String end) {
        LocalDate start_date = stringToLocalDate(start);
        LocalDate end_date = stringToLocalDate(end);

        WebClient client = WebClient.create("https://www.hashscraper.com/api/twitter/");
        String response = client.post()
                .uri("?apikey=" + api_key + "&keyword=" + keyword + "&max_count=10" + "&start_date=" + start_date + "&end_date=" + end_date)
                .header("Content-Type", "application/json version=2")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

    /**
     * Updates the number of occurrence of a hashtag,
     * its like and retweet count, and overall general sentiment.
     * @param tweetDtoList A list of TweetDto objects.
     * @return A transformed HashtagDto from the list of Tweets
     */
    @Override
    public HashtagDto tweetDtoListToPremiumHashtag(List<TweetDto> tweetDtoList) {
        HashMap<String, Hashtag> hashtagHashMap = new HashMap<>();
        String id = "";

        for (TweetDto tweetDto : tweetDtoList) {
            String hashtagId = hashtagService.createHashtagId(tweetDto, tweetDto.getTopic());
            id = hashtagId;
            if (!hashtagHashMap.containsKey(hashtagId)) {
                hashtagHashMap.put(hashtagId, new Hashtag(
                        hashtagId,
                        tweetDto.getTopic(),
                        1L,
                        tweetDto.getLocalDateTime().toLocalDate(),
                        tweetDto.getTweet_like_count(),
                        tweetDto.getTweet_retweet_count(),
                        tweetDto.getGeneral_sentiment()
                ));
            } else {
                Hashtag currentHashtag = hashtagHashMap.get(hashtagId);
                currentHashtag.setNum_of_occurrence(currentHashtag.getNum_of_occurrence() + 1L);
                currentHashtag.setLike_count(currentHashtag.getLike_count() + tweetDto.getTweet_like_count());
                currentHashtag.setRetweet_count(currentHashtag.getRetweet_count() + tweetDto.getTweet_retweet_count());
                currentHashtag.setGeneral_sentiment(
                        currentHashtag.getGeneral_sentiment() + tweetDto.getGeneral_sentiment());
            }
        }

        return hashtagService.hashtagToHashtagDto(hashtagHashMap.get(id));
    }
}
