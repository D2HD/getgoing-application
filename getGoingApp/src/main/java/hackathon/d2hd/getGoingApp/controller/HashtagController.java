package hackathon.d2hd.getGoingApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/hashtag")
public class HashtagController {
    @Autowired
    private HashtagService hashtagService;
    @Autowired
    private TweetController tweetController;

    @GetMapping("/tweetDatabaseToHashtagDatabase")
    public List<Hashtag> tweetDatabaseToHashtagDatabase() throws JsonProcessingException {
        List<TweetDto> tweetDtoList = tweetController.getAllTweetDtos();
        HashMap<String, Hashtag> hashtagHashMap = new HashMap<>();
        return hashtagService.tweetDtoListToHashtagList(tweetDtoList, hashtagHashMap);
    }
}
