package hackathon.d2hd.getGoingApp.implementation;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.FreemiumService;
import hackathon.d2hd.getGoingApp.service.TopicService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FreemiumServiceImpl implements FreemiumService {
    @Autowired
    private TweetService tweetService;

    @Autowired
    private TopicService topicService;

    @Override
    public List<Topic> freemiumWorkflow(File file) throws IOException {
        List<Tweet> hashscaperTweetList = tweetService.JsonToTweetDeserializer(file);
        tweetService.saveTweetList(hashscaperTweetList);
        List<Tweet> tweetRepositoryTweetList = tweetService.getAllTweetsFromDatabase();

        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetRepositoryTweetList);
        List<Topic> tweetDtoListToTopicListTopicList = topicService.tweetDtoListToTopicList(tweetDtoList);
        topicService.saveTopicList(tweetDtoListToTopicListTopicList);
        List<Topic> topicRepositoryList = topicService.getAllTopicsFromDatabase();

        return topicService.getTop5Topics(topicRepositoryList);
    }
}
