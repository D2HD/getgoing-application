package hackathon.d2hd.getGoingApp.implementation;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.TopicService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Override
    public String createTopicId(TweetDto tweetDTO) {
        String topicName = tweetDTO.getTopic();
        String dateString = tweetDTO.getLocalDateTime().toString().substring(0, 10);
        return topicName + dateString;
    }

    @Override
    public Topic tweetDtoToTopic(TweetDto tweetDto) {
        return new Topic(
                createTopicId(tweetDto),
                tweetDto.getTopic(),
                1L,
                tweetDto.getLocalDateTime(),
                tweetDto.getTweet_like_count(),
                tweetDto.getTweet_retweet_count(),
                tweetDto.getTweet_quote_tweet_count(),
                BigDecimal.ZERO
        );
    }

    @Override
    public HashMap<String, Topic> getTopicHashmap(List<TweetDto> tweetDtoList) {
        HashMap<String, Topic> topicHashMap = new HashMap();

        tweetDtoList.forEach(tweetDTO -> {
            String topicName = tweetDTO.getTopic();
            if(!topicHashMap.containsKey(topicName)) {
                topicHashMap.put(topicName, tweetDtoToTopic(tweetDTO));
            }else {
                Topic currentTopic = topicHashMap.get(topicName);
                currentTopic.setNum_of_occurrence(currentTopic.getNum_of_occurrence() + 1);
                currentTopic.setLike_count(currentTopic.getLike_count() + tweetDTO.getTweet_like_count());
                currentTopic.setRetweet_count(currentTopic.getRetweet_count() + tweetDTO.getTweet_retweet_count());
                currentTopic.setQuote_tweet_count(currentTopic.getQuote_tweet_count() + tweetDTO.getTweet_quote_tweet_count());
            }
        });

        return topicHashMap;
    }

    @Override
    public List<Topic> getTopicList(HashMap <String, Topic> topicHashMap) {
        List<Topic> topicList = new ArrayList<>();
        topicHashMap.forEach((s, topic) -> {
            topicList.add(topicHashMap.get(s));
        });

        return topicList;
    }
}
