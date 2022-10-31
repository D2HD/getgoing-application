package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.util.HashMap;
import java.util.List;

public interface TopicService {
    Topic tweetDtoToTopic(TweetDto tweetDto);

    HashMap<String, Topic> getTopicHashmap(List<TweetDto> tweetDtoList);

    public String createTopicId(TweetDto tweetDTO);

    abstract List<Topic> getTopicList(HashMap<String, Topic> topicHashMap);
}
