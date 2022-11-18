package hackathon.d2hd.getGoingApp.implementation;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.repository.HashtagRepository;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

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
    public Hashtag tweetDtoToHashtag(TweetDto tweetDto) {
        return new Hashtag(
                createHashtagId(tweetDto, tweetDto.getTopic()),
                tweetDto.getTopic(),
                1L,
                tweetDto.getLocalDateTime().toLocalDate(),
                tweetDto.getTweet_like_count(),
                tweetDto.getTweet_retweet_count(),
                tweetDto.getGeneral_sentiment()
        );
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
                            tweetDto.getLocalDateTime().toLocalDate(),
                            tweetDto.getTweet_like_count(),
                            tweetDto.getTweet_retweet_count(),
                            tweetDto.getGeneral_sentiment()
                    ));
                }else {
                    Hashtag currentHashtag = hashtagHashMap.get(hashtagId);
                    currentHashtag.setNum_of_occurrence(currentHashtag.getNum_of_occurrence() + 1);
                    currentHashtag.setLike_count(currentHashtag.getLike_count() + tweetDto.getTweet_like_count());
                    currentHashtag.setRetweet_count(currentHashtag.getRetweet_count() + tweetDto.getTweet_retweet_count());
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
    public void clearHashtagDatabase() {
        hashtagRepository.deleteAll();
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
            System.out.println(hashtagDto.getHashtag_id() + ": " + hashtagDto.getNum_of_occurrence() + " " + hashtagDto.getNum_of_occurrence() + hashtagDto.getDaily_hashtag_count());
        });
    }

    @Override
    public List<Hashtag> getTodaysTopHashtags(List<Hashtag> hashtagList) {
        //Sort the list in ascending order according to timestamp
        hashtagList.sort(Comparator.comparing(Hashtag::getTimestamp));

        //The last element of the list should be the latest, so we get its timestamp for comparison
        LocalDate latestTimestamp = hashtagList.get(hashtagList.size() - 1).getTimestamp();

        //Remove hashtags from the hashtag list that do not have the same timestamp
        hashtagList.removeIf(currentTopic -> (!currentTopic.getTimestamp().equals(latestTimestamp)));
        hashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());
        return hashtagList;
    }
    @Override
    public List<Long> getDailyHashtagCount(Hashtag hashtag) {
        LocalDate hashtagDate = hashtag.getTimestamp();
        LocalDate startingDate = hashtagDate.minusDays(8);

        //Returns all hashtags after this starting date
        //Need to filter and get only the hashtags that match the hashtag_name of the parameter
        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampBetweenOrderByTimestampAsc(startingDate, hashtagDate);
        hashtagList.removeIf(
                currentHashtag -> (!currentHashtag.getHashtag_name().equals(hashtag.getHashtag_name()))
        );

        Long [] dailyHashtagCount = new Long[7];
        Arrays.fill(dailyHashtagCount, 0);

        hashtagList.forEach(currentHashtag -> {
            LocalDate currentHashtagDate = currentHashtag.getTimestamp();
            int differenceInDays = (int) Duration.between(startingDate, currentHashtagDate).toDays();
            dailyHashtagCount[differenceInDays] = currentHashtag.getNum_of_occurrence();
        });

        List<Long> hashtagCountHistory = new ArrayList<>();
        hashtagList.forEach(currentTopic -> {
            hashtagCountHistory.add(currentTopic.getNum_of_occurrence());
        });

        return hashtagCountHistory;
    }

    @Override
    public HashtagDto hashtagToHashtagDto(Hashtag hashtag) {
        return new HashtagDto(
                hashtag.getHashtag_id(),
                hashtag.getHashtag_name(),
                hashtag.getNum_of_occurrence(),
                hashtag.getTimestamp(),
                hashtag.getLike_count(),
                hashtag.getRetweet_count(),
                hashtag.getGeneral_sentiment(),
                getDailyHashtagCount(hashtag),
                weeklyHashtagCount(hashtag)
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

    private List<Hashtag> getHashtags(LocalDate currentDateTime, Comparator<Hashtag> comparing) {
        //endDate initialised to the day before currentDate
        //reason being is that the Hashtags for currentDate may still be populating
        LocalDate endDate = currentDateTime.minusDays(1L);
        LocalDate startDate = endDate.minusDays(7L);


        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampBetween(startDate, endDate);
        hashtagList.sort(comparing.reversed());
        List<Hashtag> top5HashtagList = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            top5HashtagList.add(hashtagList.get(i));
        }

        return top5HashtagList;
    }

    @Override
    public List<Hashtag> sevenDayTop5HashtagListByCount(LocalDate currentDateTime) {
        return getHashtags(currentDateTime, Comparator.comparing(Hashtag::getNum_of_occurrence));
    }

    @Override
    public List<Hashtag> sevenDayTop5HashtagListByLike(LocalDate currentDateTime) {
        return getHashtags(currentDateTime, Comparator.comparing(Hashtag::getLike_count));
    }

    @Override
    public List<Hashtag> currentTop5HashtagList(LocalDate currentDateTime) {
        List<Hashtag> currentTop5HashtagList = hashtagRepository.findAllByTimestampIs(currentDateTime);
        currentTop5HashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());

        return currentTop5HashtagList;
    }

    private Long countOfWeek(List<Hashtag> weeklyHashtagList) {
        if(weeklyHashtagList.isEmpty()) return 0L;

        Long occurrenceCount = 0L;
        for(Hashtag hashtag: weeklyHashtagList) {
            occurrenceCount += hashtag.getNum_of_occurrence();
        }

        return occurrenceCount;
    }

    private Long[] weeklyHashtagCount(Hashtag hashtag) {
        LocalDate week4EndDate = hashtag.getTimestamp();
        LocalDate week4StartDate = week4EndDate.minusDays(7L);
        List<Hashtag> week4HashtagList = hashtagRepository.findAllByTimestampBetween(week4StartDate, week4EndDate);
        week4HashtagList.removeIf(week4Hashtag -> (!week4Hashtag.getHashtag_name().equals(hashtag.getHashtag_name())));
        Long week4OccurrenceCount = countOfWeek(week4HashtagList);

        LocalDate week3EndDate = week4StartDate.minusDays(1L);
        LocalDate week3StartDate = week3EndDate.minusDays(7L);
        List<Hashtag> week3HashtagList = hashtagRepository.findAllByTimestampBetween(week3StartDate, week3EndDate);
        week3HashtagList.removeIf(week3Hashtag -> (!week3Hashtag.getHashtag_name().equals(hashtag.getHashtag_name())));
        Long week3OccurrenceCount = countOfWeek(week3HashtagList);

        LocalDate week2EndDate = week3StartDate.minusDays(1L);
        LocalDate week2StartDate = week2EndDate.minusDays(7L);
        List<Hashtag> week2HashtagList = hashtagRepository.findAllByTimestampBetween(week2StartDate, week2EndDate);
        week2HashtagList.removeIf(week2Hashtag -> (!week2Hashtag.getHashtag_name().equals(hashtag.getHashtag_name())));
        Long week2OccurrenceCount = countOfWeek(week2HashtagList);

        LocalDate week1EndDate = week2StartDate.minusDays(1L);
        LocalDate week1StartDate = week1EndDate.minusDays(7L);
        List<Hashtag> week1HashtagList = hashtagRepository.findAllByTimestampBetween(week1StartDate, week1EndDate);
        week1HashtagList.removeIf(week1Hashtag -> (!week1Hashtag.getHashtag_name().equals(hashtag.getHashtag_name())));
        Long week1OccurrenceCount = countOfWeek(week1HashtagList);

        return new Long[] {week1OccurrenceCount, week2OccurrenceCount, week3OccurrenceCount, week4OccurrenceCount};
    }
}
