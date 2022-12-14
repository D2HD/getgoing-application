package hackathon.d2hd.getGoingApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.time.LocalDate;
import java.util.List;

public interface FreeService {
    String hashscraperCall(String keyword);
    List<HashtagDto> getTop5HashtagDtos();
    List<TweetDto> keywordSearchToTweeDtoList(String response) throws JsonProcessingException;
    HashtagDto tweetDtoListToPremiumHashtag(List<TweetDto> tweetDtoList);
    LocalDate stringToLocalDate(String stringToLocalDate);
    String hashscraperDateCall(String start, String end);
}
