package hackathon.d2hd.getGoingApp.implementation;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.repository.HashtagRepository;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                            tweetDto.getGeneral_sentiment()
                    ));
                }else {
                    Hashtag currentHashtag = hashtagHashMap.get(hashtagId);
                    currentHashtag.setNum_of_occurrence(currentHashtag.getNum_of_occurrence() + 1);
                    currentHashtag.setLike_count(currentHashtag.getLike_count() + tweetDto.getTweet_like_count());
                    currentHashtag.setGeneral_sentiment(
                            currentHashtag.getGeneral_sentiment() + tweetDto.getGeneral_sentiment());
                }
            }
        }

        List<Hashtag> hashtagList = new ArrayList<>();
        hashtagHashMap.forEach((s, hashtag) -> {
            hashtag.setGeneral_sentiment(hashtag.getGeneral_sentiment() / hashtag.getNum_of_occurrence());
            hashtagList.add(hashtag);
        });

        hashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());

        return hashtagList;
    }
    @Override
    public void clearTopicDatabase() {
        hashtagRepository.deleteAll();
    }

    @Override
    public long sumOfAllTopicOccurrences(List<Hashtag> hashtagList) {
        long sumOfAllTopicOccurrences = 0l;
        for(Hashtag hashtag : hashtagList) {
            sumOfAllTopicOccurrences += hashtag.getNum_of_occurrence();
        }

        return sumOfAllTopicOccurrences;
    }

    @Override
    public List<Hashtag> getAllHashtagsFromDatabase() {
        return hashtagRepository.findAll();
    }


    @Override
    public void displayHashtags(List<Hashtag> hashtagList) {
        hashtagList.forEach(hashtag -> {
            System.out.println(hashtag.getHashtag_id() + ": " + hashtag.getNum_of_occurrence() + " " + hashtag.getGeneral_sentiment());
        });
    }

    @Override
    public void displayHashtagDtos(List<HashtagDto> hashtagDtoList) {
        hashtagDtoList.forEach(hashtagDto -> {
            System.out.println(hashtagDto.getHashtag_id() + ": " + hashtagDto.getNum_of_occurrence() + " " + hashtagDto.getNum_of_occurrence() + hashtagDto.getPast_hashtag_count());
        });
    }

    @Override
    public List<Hashtag> getTodaysTopHashtags(List<Hashtag> hashtagList) {
        //Sort the list in ascending order according to timestamp
        hashtagList.sort(Comparator.comparing(Hashtag::getTimestamp));

        //The last element of the list should be the latest, so we get its timestamp for comparison
        LocalDate latestTimestamp = hashtagList.get(hashtagList.size() - 1).getTimestamp().toLocalDate();

        //Remove hashtags from the hashtag list that do not have the same timestamp
        hashtagList.removeIf(currentTopic -> (!currentTopic.getTimestamp().toLocalDate().equals(latestTimestamp)));
        hashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());
        return hashtagList;
    }
    @Override
    public List<Long> getTopicOccurrenceHistory(Hashtag hashtag) {
        LocalDateTime hashtagDate = hashtag.getTimestamp();
        LocalDateTime startingDate = hashtagDate.minusDays(8);

        //Returns all hashtags after this starting date
        //Need to filter and get only the hashtags that match the hashtag_name of the parameter
        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampBetweenOrderByTimestampAsc(startingDate, hashtagDate);
        hashtagList.removeIf(
                currentHashtag -> (!currentHashtag.getHashtag_name().equals(hashtag.getHashtag_name()))
        );

        List<Long> hashtagCountHistory = new ArrayList<>();
        hashtagList.forEach(currentTopic -> {
            hashtagCountHistory.add(currentTopic.getNum_of_occurrence());
        });

        return hashtagCountHistory;


        //Now that the list is sorted, we can add


//        LocalDateTime current_time = LocalDateTime.now();
//        LocalDateTime dateToStartSearch = current_time.minusDays(7);
//
//        List<Hashtag> aTopicList = hashtagRepository.findAllByTimestampAfter(dateToStartSearch);
//        Long [] sevenDayArray = new Long [7];
//
//
//        for(Hashtag hashtag1: aTopicList) {
//            LocalDateTime timeStampOfTopic = hashtag1.getTimestamp();
//            Duration difference_in_days = Duration.between(timeStampOfTopic, current_time);
//
//            if(hashtag1.getTopic_name().equals(hashtag.getTopic_name()) && difference_in_days.toDays() != 0) {
//                sevenDayArray[(int) difference_in_days.toDays() - 1] = hashtag1.getNum_of_occurrence();
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

    @Override
    public HashtagDto hashtagToHashtagDto(Hashtag hashtag) {
        return new HashtagDto(
                hashtag.getHashtag_id(),
                hashtag.getHashtag_name(),
                hashtag.getNum_of_occurrence(),
                hashtag.getTimestamp(),
                hashtag.getLike_count(),
                hashtag.getGeneral_sentiment(),
                getTopicOccurrenceHistory(hashtag)
        );
    }

    @Override
    public List<HashtagDto> hashtagListToHashtagDtoList(List<Hashtag> hashtagList) {
        List<HashtagDto> hashtagDtoList = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            hashtagDtoList.add(hashtagToHashtagDto(hashtagList.get(i)));
        }

        return hashtagDtoList;
    }
    @Override
    public void saveHashtagList(List<Hashtag> hashtagList) {
        hashtagRepository.saveAll(hashtagList);
    }

    private List<Hashtag> getHashtags(LocalDateTime currentDateTime, Comparator<Hashtag> comparing) {
        //endDate initialised to the day before currentDate
        //reason being is that the Hashtags for currentDate may still be populating
        LocalDateTime endDate = currentDateTime.minusDays(1L);
        LocalDateTime startDate = endDate.minusDays(7L);


        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampBetween(startDate, endDate);
        hashtagList.sort(comparing.reversed());
        List<Hashtag> top5HashtagList = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            top5HashtagList.add(hashtagList.get(i));
        }

        return top5HashtagList;
    }

    @Override
    public List<Hashtag> sevenDayTop5HashtagListByCount(LocalDateTime currentDateTime) {
        return getHashtags(currentDateTime, Comparator.comparing(Hashtag::getNum_of_occurrence));
    }

    @Override
    public List<Hashtag> sevenDayTop5HashtagListByLike(LocalDateTime currentDateTime) {
        return getHashtags(currentDateTime, Comparator.comparing(Hashtag::getLike_count));
    }

    @Override
    public List<Hashtag> currentTop5HashtagList(LocalDateTime currentDateTime) {
        List<Hashtag> currentTop5HashtagList = hashtagRepository.findAllByTimestampContaining(currentDateTime);
        currentTop5HashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());

        return currentTop5HashtagList;
    }
}
