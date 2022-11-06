package hackathon.d2hd.getGoingApp.implementation;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TopicDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.FreemiumService;
import hackathon.d2hd.getGoingApp.service.TopicService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FreemiumServiceImpl implements FreemiumService {
    @Autowired
    private TweetService tweetService;

    @Autowired
    private TopicService topicService;

    @Override
    public List<TopicDto> freemiumWorkflow(File file) throws IOException {
        tweetService.clearDatabase();
        topicService.clearTopicDatabase();
        List<Tweet> hashscaperTweetList = tweetService.JsonToTweetDeserializer(file);
        tweetService.saveTweetList(hashscaperTweetList);
        List<Tweet> tweetRepositoryTweetList = tweetService.getAllTweetsFromDatabase();

        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetRepositoryTweetList);
        List<Topic> tweetDtoListToTopicList = topicService.tweetDtoListToTopicList(tweetDtoList);
        topicService.saveTopicList(tweetDtoListToTopicList);
        List<Topic> topicRepositoryList = topicService.getAllTopicsFromDatabase();
        List<Topic> topTopicList = topicService.getTop5Topics(topicRepositoryList);
        List<TopicDto> topicDtoList = new ArrayList<>();
        topTopicList.forEach(topic -> {
            topicDtoList.add(topicService.topicToTopicDto(topic));
        });

        return topicDtoList;
    }
}
