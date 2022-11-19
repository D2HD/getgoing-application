package hackathon.d2hd.getGoingApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;

import java.util.List;

public interface FreemiumService {
    List<HashtagDto> getTop5HashtagDtos();
    List<TweetDto> getTop10TweetList(String response) throws JsonProcessingException;
    
}
