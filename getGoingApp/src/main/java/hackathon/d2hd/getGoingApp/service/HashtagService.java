package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface HashtagService {
    String createHashtagId(TweetDto tweetDTO, String hashtag);
    List<Hashtag> tweetDtoListToHashtagList(List<TweetDto> tweetDtoList, HashMap<String, Hashtag> hashtagHashMap);
    void saveHashtagList(List<Hashtag> hashtagList);
    void clearHashtagDatabase();

    List<Hashtag> getAllHashtagsFromDatabase();
    void displayHashtags(List<Hashtag> hashtagList);
    void displayHashtagDtos(List<HashtagDto> hashtagDtoList);
    List<Hashtag> getTodaysTopHashtags(List<Hashtag> hashtagList);
    List<Long> getDailyHashtagCount(Hashtag hashtag);
    HashtagDto hashtagToHashtagDto(Hashtag hashtag);
    List<HashtagDto> hashtagListToHashtagDtoList(List<Hashtag> hashtagList);
    List<Hashtag> sevenDayTop5HashtagListByCount(LocalDate currentDateTime);
    List<Hashtag> sevenDayTop5HashtagListByLike(LocalDate currentDateTime);
    List<Hashtag> currentTop5HashtagList(LocalDate currentDateTime);
    Hashtag tweetDtoToHashtag(TweetDto tweetDto);

}
