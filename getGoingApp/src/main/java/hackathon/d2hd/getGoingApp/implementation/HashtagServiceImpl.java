package hackathon.d2hd.getGoingApp.implementation;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.TopicDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.repository.HashtagRepository;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public class HashtagServiceImpl implements HashtagService {

    @Autowired
    private HashtagRepository hashtagRepository;

    @Override
    public String createHashtagId(TweetDto tweetDTO, String hashtag) {
        String dateString = tweetDTO.getLocalDateTime().toString().substring(0, 10);
        return hashtag + dateString;
    }

    // TODO: 8/11/22 Solve this today
    @Override
    public List<Hashtag> tweetDtoListToHashtagList(List<TweetDto> tweetDtoList, HashMap<String, Hashtag> hashtagHashMap) {
        for(TweetDto tweetDto: tweetDtoList) {
            List<String> stringHashtagList = tweetDto.getHashtagList();

            for(String stringHashtag: stringHashtagList) {
                String hashtagId = createHashtagId(tweetDto, stringHashtag);

                if(!hashtagHashMap.containsKey(hashtagId)) {
                    hashtagHashMap.put(hashtagId, new Hashtag(
                            hashtagId,
                            stringHashtag,
                            1L,
                            tweetDto.getLocalDateTime(),
                            tweetDto.getTweet_like_count(),
                            tweetDto.getTweet_retweet_count(),
                            tweetDto.getTweet_quote_tweet_count(),
                            BigDecimal.ZERO
                    ));
                }else {
                    Hashtag currentHashtag = hashtagHashMap.get(hashtagId);
                    currentHashtag.setNum_of_occurrence(currentHashtag.getNum_of_occurrence() + 1);
                    currentHashtag.setLike_count(currentHashtag.getLike_count() + tweetDto.getTweet_like_count());
                    currentHashtag.setRetweet_count(currentHashtag.getRetweet_count() + tweetDto.getTweet_retweet_count());
                    currentHashtag.setQuote_tweet_count(currentHashtag.getQuote_tweet_count() + tweetDto.getTweet_quote_tweet_count());
                }
            }
        }

        List<Hashtag> hashtagList = new ArrayList<>();
        hashtagHashMap.forEach((s, hashtag) -> {
            hashtagList.add(hashtag);
        });

        hashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());

        return hashtagList;
    }

    @Override
    public List<Hashtag> getTopicList(HashMap<String, Hashtag> topicHashMap) {
        List<Hashtag> hashtagList = new ArrayList<>();
        topicHashMap.forEach((s, topic) -> {
            hashtagList.add(topicHashMap.get(s));
        });

        return hashtagList;
    }

    @Override
    public void saveHashtag(Hashtag hashtag) {
        hashtagRepository.save(hashtag);
    }

    @Override
    public void saveTopicList(List<Hashtag> hashtagList) {
        hashtagList.forEach(topic -> {
            saveHashtag(topic);
        });
    }

    @Override
    public void clearTopicDatabase() {
        hashtagRepository.deleteAll();
    }

//    @Override
//    public List<Hashtag> tweetDtoListToTopicList(List<TweetDto> tweetDtoList) {
//        HashMap<String, Hashtag> topicHashMap = getHashtagHashmap(tweetDtoList);
//        List<Hashtag> hashtagList = getTopicList(topicHashMap);
//        hashtagRepository.saveAll(hashtagList);
//        return hashtagList;
//    }

    @Override
    public long sumOfAllTopicOccurrences(List<Hashtag> hashtagList) {
        long sumOfAllTopicOccurrences = 0l;
        for(Hashtag hashtag : hashtagList) {
            sumOfAllTopicOccurrences += hashtag.getNum_of_occurrence();
        }

        return sumOfAllTopicOccurrences;
    }

    @Override
    public List<Hashtag> getAllTopicsFromDatabase() {
        return hashtagRepository.findAll();
    }

    @Override
    public List<Hashtag> sortTopicsByNumOfOccurrence(List<Hashtag> hashtagList) {
        hashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence));
        return hashtagList;
    }

    @Override
    public void displayTopics(List<Hashtag> hashtagList) {
        hashtagList.forEach(topic -> {
            System.out.println(topic.getTopic_id() + ": " + topic.getNum_of_occurrence());
        });
    }

    @Override
    public List<Hashtag> getTodaysTopTopics(List<Hashtag> hashtagList) {
        //Sort the list in ascending order according to timestamp
        hashtagList.sort(Comparator.comparing(Hashtag::getTimestamp));

        //The last element of the list should be the latest, so we get its timestamp for comparison
        LocalDate latestTimestamp = hashtagList.get(hashtagList.size() - 1).getTimestamp().toLocalDate();

        //Remove topics from the topic list that do not have the same timestamp
        hashtagList.removeIf(currentTopic -> (!currentTopic.getTimestamp().toLocalDate().equals(latestTimestamp)));
        hashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());
        return hashtagList;
    }
    @Override
    public List<Long> getTopicOccurrenceHistory(Hashtag hashtag) {
        LocalDateTime topicDate = hashtag.getTimestamp();
        LocalDateTime startingDate = topicDate.minusDays(8);

        //Returns all topics after this starting date
        //Need to filter and get only the topics that match the topic_name of the parameter
        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampBetweenOrderByTimestampAsc(startingDate, topicDate);
        hashtagList.removeIf(
                currentTopic -> (!currentTopic.getTopic_name().equals(hashtag.getTopic_name()))
        );

        List<Long> topicCountHistory = new ArrayList<>();
        hashtagList.forEach(currentTopic -> {
            topicCountHistory.add(currentTopic.getNum_of_occurrence());
        });

        return topicCountHistory;


        //Now that the list is sorted, we can add


//        LocalDateTime current_time = LocalDateTime.now();
//        LocalDateTime dateToStartSearch = current_time.minusDays(7);
//
//        List<Hashtag> aTopicList = topicRepository.findAllByTimestampAfter(dateToStartSearch);
//        Long [] sevenDayArray = new Long [7];
//
//
//        for(Hashtag topic1: aTopicList) {
//            LocalDateTime timeStampOfTopic = topic1.getTimestamp();
//            Duration difference_in_days = Duration.between(timeStampOfTopic, current_time);
//
//            if(topic1.getTopic_name().equals(hashtag.getTopic_name()) && difference_in_days.toDays() != 0) {
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

    public TopicDto topicToTopicDto(Hashtag hashtag) {
        return new TopicDto(
                hashtag.getTopic_id(),
                hashtag.getTopic_name(),
                hashtag.getNum_of_occurrence(),
                hashtag.getTimestamp(),
                hashtag.getLike_count(),
                hashtag.getRetweet_count(),
                hashtag.getQuote_tweet_count(),
                hashtag.getGeneral_sentiment(),
                getTopicOccurrenceHistory(hashtag)
        );
    }

    @Override
    public List<TopicDto> topicListToTopicDtoList(List<Hashtag> hashtagList) {
        List<TopicDto> topicDtoList = new ArrayList<>();

        hashtagList.forEach(topic -> {
            topicDtoList.add(topicToTopicDto(topic));
        });

        return topicDtoList;
    }
}
