package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Tweet;

import java.util.HashMap;
import java.util.List;

public interface TopicService {
    // TODO: 28/10/22 Add APIs for Topics
    public HashMap<String, Long> getTopics(List<Tweet> tweetList);
}
