package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.util.HashMap;
import java.util.List;

public interface TopicService {
    public String createTopicId(TweetDto tweetDTO);
    Topic tweetDtoToTopic(TweetDto tweetDto);
    public HashMap<String, Topic> getTopicHashmap(List<TweetDto> tweetDtoList);
    public List<Topic> getTopicList(HashMap<String, Topic> topicHashMap);
    public void saveTopic(Topic topic);
    public void saveTopicList(List<Topic> topicList);
    public void clearTopicDatabase();
    public List<Topic> tweetDtoListToTopicList(List<TweetDto> tweetDtoList);
    public long sumOfAllTopicOccurrences(List<Topic> topicList);

    // TODO: 2/11/22 API for getting only specific Topics
    public List<Topic> getAllTopicsFromDatabase();
    public List<Topic> sortTopicsByNumOfOccurrence(List<Topic> topicList);
    public void displayTopics(List<Topic> topicList);
    public List<Topic> getTop5Topics(List<Topic> topicList);

}
