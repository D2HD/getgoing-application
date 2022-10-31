package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.util.HashMap;
import java.util.List;

public interface TopicService {
    Topic tweetDtoToTopic(TweetDto tweetDto);

    // TODO: 28/10/22 Add APIs for Topics
    public HashMap<String, Topic> getTopics(List<TweetDto> tweetDtoList);
    public String createTopicId(TweetDto tweetDTO);
}
