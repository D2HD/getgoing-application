package hackathon.d2hd.getGoingApp.implementation;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.service.FreemiumService;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class FreemiumServiceImpl implements FreemiumService {
    @Autowired
    private TweetService tweetService;

    @Autowired
    private HashtagService hashtagService;

    @Override
    public List<HashtagDto> freemiumWorkflow(File file) throws IOException {
        //Deserialize JSON into a list of Tweet objects

        //Hashmap to contain the key value pairs of a hashtag
        HashMap<String, Hashtag> hashtagHashMap = new HashMap<>();







//        List<Tweet> hashscaperTweetList = tweetService.JsonToTweetDeserializer(file);
//        tweetService.saveTweetList(hashscaperTweetList);
//        List<Tweet> tweetRepositoryTweetList = tweetService.getAllTweetsFromDatabase();
//
//        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetRepositoryTweetList);
//        List<Hashtag> tweetDtoListToTopicList = hashtagService.tweetDtoListToTopicList(tweetDtoList);
//        hashtagService.saveTopicList(tweetDtoListToTopicList);
//        List<Hashtag> topicRepositoryList = hashtagService.getAllTopicsFromDatabase();
//        List<Hashtag> topTopicList = hashtagService.getTodaysTopTopics(topicRepositoryList);
//        List<HashtagDto> topicDtoList = new ArrayList<>();
//        topTopicList.forEach(topic -> {
//            topicDtoList.add(hashtagService.topicToTopicDto(topic));
//        });
//
//        return topicDtoList;

        return null;
    }
}
