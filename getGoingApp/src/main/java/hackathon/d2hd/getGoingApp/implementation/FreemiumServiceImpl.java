package hackathon.d2hd.getGoingApp.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.GeneralSentiment;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.FreemiumService;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class FreemiumServiceImpl implements FreemiumService {
    @Autowired
    private TweetService tweetService;

    @Autowired
    private HashtagService hashtagService;

    @Override
    public List<HashtagDto> getTop5HashtagDtos(){
        List<Hashtag> todaysTop5HashtagList = hashtagService.getTodaysTop5Hashtags();
        List<HashtagDto> hashtagDtoList = hashtagService.hashtagListToHashtagDtoList(todaysTop5HashtagList);

        for(int i = 0; i < hashtagDtoList.size(); i++) {
            HashtagDto currentDto = hashtagDtoList.get(i);

            currentDto.setDaily_hashtag_count(
                    new Long[]{
                            Math.round((Math.random() * 51)),
                            Math.round((Math.random() * 51)),
                            Math.round((Math.random() * 51)),
                            Math.round((Math.random() * 51)),
                            Math.round((Math.random() * 51)),
                            Math.round((Math.random() * 51)),
                            Math.round((Math.random() * 51))
                    }
            );

            currentDto.setWeekly_hashtag_count(
                    new Long[]{
                            Math.round((Math.random() * 51)),
                            Math.round((Math.random() * 51)),
                            Math.round((Math.random() * 51)),
                            Math.round((Math.random() * 51))
                    }
            );

            currentDto.setWeekly_general_sentiment(
                    new GeneralSentiment[]{
                            new GeneralSentiment(Math.round(Math.random() * 50), Math.round(Math.random() * 50)),
                            new GeneralSentiment(Math.round(Math.random() * 50), Math.round(Math.random() * 50)),
                            new GeneralSentiment(Math.round(Math.random() * 50), Math.round(Math.random() * 50)),
                            new GeneralSentiment(Math.round(Math.random() * 50), Math.round(Math.random() * 50))
                    }
            );

            currentDto.setLike_count(
                    Math.round(Math.random() * 50)
            );


            currentDto.setGeneral_sentiment_of_the_day(
                    new GeneralSentiment(
                            Math.round(Math.random() * 50),
                            Math.round(Math.random() * 50)
                    ));

            currentDto.setGeneral_sentiment_of_the_week(
                    new GeneralSentiment(
                            Math.round(Math.random() * 50),
                            Math.round(Math.random() * 50)
                    ));

            currentDto.setGeneral_sentiment_of_the_week(
                    new GeneralSentiment(
                            Math.round(Math.random() * 50),
                            Math.round(Math.random() * 50)
                    ));

            currentDto.setDaily_retweet_count(
                    new Long[]{
                            Math.round(Math.random() * 101),
                            Math.round(Math.random() * 101),
                            Math.round(Math.random() * 101),
                            Math.round(Math.random() * 101),
                            Math.round(Math.random() * 101),
                            Math.round(Math.random() * 101),
                            Math.round(Math.random() * 101)
                    });
        }

        return hashtagDtoList;
    }

    @Override
    public List<TweetDto> getTop10TweetList(String response) throws JsonProcessingException {
        List<Tweet> tweetList = tweetService.hashscraperResponseBodyToTweetDeserializer(response);
        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetList);
        tweetDtoList.sort(Comparator.comparing(TweetDto::getTweet_like_count).reversed());

        int size = tweetDtoList.size();
        while (size > 10) {
            tweetDtoList.remove(tweetDtoList.get(tweetDtoList.size() - 1));
        }

        return tweetDtoList;
    }
}
