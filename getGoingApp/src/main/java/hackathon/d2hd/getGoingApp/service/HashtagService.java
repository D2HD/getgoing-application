package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.util.HashMap;
import java.util.List;

public interface HashtagService {
    String createHashtagId(TweetDto tweetDTO, String hashtag);
    List<Hashtag> tweetDtoListToHashtagList(List<TweetDto> tweetDtoList, HashMap<String, Hashtag> hashtagHashMap);
    void saveHashtagList(List<Hashtag> hashtagList);
    void clearTopicDatabase();
    long sumOfAllTopicOccurrences(List<Hashtag> hashtagList);
    List<Hashtag> getAllHashtagsFromDatabase();
    void displayHashtags(List<Hashtag> hashtagList);
    void displayHashtagDtos(List<HashtagDto> hashtagDtoList);
    List<Hashtag> getTodaysTopHashtags(List<Hashtag> hashtagList);
    List<Long> getTopicOccurrenceHistory(Hashtag hashtag);
    HashtagDto hashtagToHashtagDto(Hashtag hashtag);
    List<HashtagDto> hashtagListToHashtagDtoList(List<Hashtag> hashtagList);


}
