package hackathon.d2hd.getGoingApp.implementation;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataTransferObject.TopicDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.repository.TopicRepository;
import hackathon.d2hd.getGoingApp.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public String createTopicId(TweetDto tweetDTO) {
        String topicName = tweetDTO.getTopic();
        String dateString = tweetDTO.getLocalDateTime().toString().substring(0, 10);
        return topicName + dateString;
    }

    @Override
    public Topic tweetDtoToTopic(TweetDto tweetDto) {
        return new Topic(
                createTopicId(tweetDto),
                tweetDto.getTopic(),
                1L,
                tweetDto.getLocalDateTime(),
                tweetDto.getTweet_like_count(),
                tweetDto.getTweet_retweet_count(),
                tweetDto.getTweet_quote_tweet_count(),
                BigDecimal.ZERO
        );
    }

    @Override
    public HashMap<String, Topic> getTopicHashmap(List<TweetDto> tweetDtoList) {
        HashMap<String, Topic> topicHashMap = new HashMap();

        tweetDtoList.forEach(tweetDTO -> {
            String topicId = createTopicId(tweetDTO);
            if(!topicHashMap.containsKey(topicId)) {
                topicHashMap.put(topicId, tweetDtoToTopic(tweetDTO));
            }else {
                Topic currentTopic = topicHashMap.get(topicId);
                currentTopic.setNum_of_occurrence(currentTopic.getNum_of_occurrence() + 1);
                currentTopic.setLike_count(currentTopic.getLike_count() + tweetDTO.getTweet_like_count());
                currentTopic.setRetweet_count(currentTopic.getRetweet_count() + tweetDTO.getTweet_retweet_count());
                currentTopic.setQuote_tweet_count(currentTopic.getQuote_tweet_count() + tweetDTO.getTweet_quote_tweet_count());
            }
        });

        return topicHashMap;
    }

    @Override
    public List<Topic> getTopicList(HashMap<String, Topic> topicHashMap) {
        List<Topic> topicList = new ArrayList<>();
        topicHashMap.forEach((s, topic) -> {
            topicList.add(topicHashMap.get(s));
        });

        return topicList;
    }

    @Override
    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public void saveTopicList(List<Topic> topicList) {
        topicList.forEach(topic -> {
            saveTopic(topic);
        });
    }

    @Override
    public void clearTopicDatabase() {
        topicRepository.deleteAll();
    }

    @Override
    public List<Topic> tweetDtoListToTopicList(List<TweetDto> tweetDtoList) {
        HashMap<String, Topic> topicHashMap = getTopicHashmap(tweetDtoList);
        return getTopicList(topicHashMap);
    }

    @Override
    public long sumOfAllTopicOccurrences(List<Topic> topicList) {
        long sumOfAllTopicOccurrences = 0l;
        for(Topic topic: topicList) {
            sumOfAllTopicOccurrences += topic.getNum_of_occurrence();
        }

        return sumOfAllTopicOccurrences;
    }

    @Override
    public List<Topic> getAllTopicsFromDatabase() {
        return topicRepository.findAll();
    }

    @Override
    public List<Topic> sortTopicsByNumOfOccurrence(List<Topic> topicList) {
        topicList.sort(Comparator.comparing(Topic::getNum_of_occurrence));
        return topicList;
    }

    @Override
    public void displayTopics(List<Topic> topicList) {
        topicList.forEach(topic -> {
            System.out.println(topic.getTopic_id() + ": " + topic.getNum_of_occurrence());
        });
    }

    @Override
    public List<Topic> getTop5Topics(List<Topic> topicList) {
        //The last element of the list should be the latest, so we get its timestamp for comparison
        topicList.sort(Comparator.comparing(Topic::getTimestamp));
        LocalDate latestTimestamp = topicList.get(topicList.size() - 1).getTimestamp().toLocalDate();
        topicList.removeIf(currentTopic -> (!currentTopic.getTimestamp().toLocalDate().equals(latestTimestamp)));
        topicList.sort(Comparator.comparing(Topic::getNum_of_occurrence).reversed());
        return topicList;
//        topicList = sortTopicsByNumOfOccurrence(topicList);
//        List<Topic> top5TopicList = new ArrayList<>();
//
//        for(int i = 0; i < 5; i++) {
//            top5TopicList.add(topicList.get(i));
//        }
//
//        return top5TopicList;
    }
    @Override
    public List<Long> getSevenDay(Topic topic) {
        LocalDateTime topicDate = topic.getTimestamp();
        LocalDateTime startingDate = topicDate.minusDays(8);

        //Returns all topics after this starting date
        //Need to filter and get only the topics that match the topic_name of the parameter
        List<Topic> topicList = topicRepository.findAllByTimestampBetweenOrderByTimestampAsc(startingDate, topicDate);
        topicList.removeIf(
                currentTopic -> (!currentTopic.getTopic_name().equals(topic.getTopic_name()))
        );

        List<Long> topicCountHistory = new ArrayList<>();
        topicList.forEach(currentTopic -> {
            topicCountHistory.add(currentTopic.getNum_of_occurrence());
        });

        return topicCountHistory;


        //Now that the list is sorted, we can add


//        LocalDateTime current_time = LocalDateTime.now();
//        LocalDateTime dateToStartSearch = current_time.minusDays(7);
//
//        List<Topic> aTopicList = topicRepository.findAllByTimestampAfter(dateToStartSearch);
//        Long [] sevenDayArray = new Long [7];
//
//
//        for(Topic topic1: aTopicList) {
//            LocalDateTime timeStampOfTopic = topic1.getTimestamp();
//            Duration difference_in_days = Duration.between(timeStampOfTopic, current_time);
//
//            if(topic1.getTopic_name().equals(topic.getTopic_name()) && difference_in_days.toDays() != 0) {
//                sevenDayArray[(int) difference_in_days.toDays() - 1] = topic1.getNum_of_occurrence();
//            }
//        }
//
//        for(int i = 0; i< sevenDayArray.length; i++){
//            if(sevenDayArray[i] == null) {
//                sevenDayArray[i] = 0L;
//                System.out.println("Nothing was here at " + i);
//            }
//        }
//
//        return sevenDayArray;
    }

    public TopicDto topicToTopicDto(Topic topic) {
        return new TopicDto(
                topic.getTopic_id(),
                topic.getTopic_name(),
                topic.getNum_of_occurrence(),
                topic.getTimestamp(),
                topic.getLike_count(),
                topic.getRetweet_count(),
                topic.getQuote_tweet_count(),
                topic.getGeneral_sentiment(),
                getSevenDay(topic)
        );
    }
}
