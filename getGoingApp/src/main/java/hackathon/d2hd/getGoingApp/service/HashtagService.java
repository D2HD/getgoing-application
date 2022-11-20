package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.util.HashMap;
import java.util.List;

public interface HashtagService {
    /**
     * Creates a hashtagId by concatenating string hashtag and the timestamp of the tweetDto
     *
     * @param tweetDto The tweetDto object has a timestamp (YYYY-MM-DD)which will be appended to the end of the hashtagId. (E.g: 2022-11-19)
     * @param hashtag The
     * @return
     */
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
