package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.TopicDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.util.HashMap;
import java.util.List;

public interface HashtagService {
    String createHashtagId(TweetDto tweetDTO, String hashtag);
    List<Hashtag> tweetDtoListToHashtagList(List<TweetDto> tweetDtoList, HashMap<String, Hashtag> hashtagHashMap);
    List<Hashtag> getTopicList(HashMap<String, Hashtag> topicHashMap);
    void saveHashtag(Hashtag hashtag);
    void saveTopicList(List<Hashtag> hashtagList);
    void clearTopicDatabase();
    long sumOfAllTopicOccurrences(List<Hashtag> hashtagList);
    List<Hashtag> getAllTopicsFromDatabase();
    List<Hashtag> sortTopicsByNumOfOccurrence(List<Hashtag> hashtagList);
    void displayTopics(List<Hashtag> hashtagList);
    List<Hashtag> getTodaysTopTopics(List<Hashtag> hashtagList);
    List<Long> getTopicOccurrenceHistory(Hashtag hashtag);
    TopicDto topicToTopicDto(Hashtag hashtag);
    List<TopicDto> topicListToTopicDtoList(List<Hashtag> hashtagList);

}
