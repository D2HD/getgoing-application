package hackathon.d2hd.getGoingApp.implementation;

import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    public TopicServiceImpl() {
    }

    // TODO: 28/10/22 Add Implementation for TopicService
    @Override
    public HashMap<String, Long> getTopics(List<Tweet> tweetList) {
        HashMap <String, Long> topicHashMap = new HashMap();

        tweetList.forEach(data -> {
            String topic = data.getValue2();
            if(!topicHashMap.containsKey(topic)) {
                topicHashMap.put(topic, 0L);
            }else {
                topicHashMap.put(topic, topicHashMap.get(topic) + 1);
            }
        });

        return topicHashMap;
    }

    public void topic(List<Tweet> tweetList) {
        HashMap<String, Topic> topicHashMap = new HashMap<>();


    }
}
