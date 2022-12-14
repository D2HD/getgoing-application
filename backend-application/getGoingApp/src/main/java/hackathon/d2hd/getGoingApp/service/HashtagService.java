package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.util.HashMap;
import java.util.List;

public interface HashtagService {
    String createHashtagId(TweetDto tweetDto, String hashtag);
    List<Hashtag> tweetDtoListToHashtagList(List<TweetDto> tweetDtoList, HashMap<String, Hashtag> hashtagHashMap);
    void saveHashtagList(List<Hashtag> hashtagList);
    void clearHashtagDatabase();
    List<Hashtag> getAllHashtagsFromDatabase();
    List<Hashtag> getTodaysTop5Hashtags();
    Long[] getDailyHashtagCount(Hashtag hashtag);
    HashtagDto hashtagToHashtagDto(Hashtag hashtag);
    List<HashtagDto> hashtagListToHashtagDtoList(List<Hashtag> hashtagList);

}
