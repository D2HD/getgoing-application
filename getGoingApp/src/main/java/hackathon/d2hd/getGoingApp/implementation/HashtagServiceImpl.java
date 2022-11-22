package hackathon.d2hd.getGoingApp.implementation;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataTransferObject.GeneralSentiment;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.repository.HashtagRepository;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class HashtagServiceImpl implements HashtagService {

    @Autowired
    private HashtagRepository hashtagRepository;

    /**
     * Creates a Hashtag object from a TweetDto object by concatenating the String of the desIred hashtag with the date of the TweetDto
     * @param tweetDto
     * @param hashtag
     * @return
     * A String object that is the HashtagId
     */
    @Override
    public String createHashtagId(TweetDto tweetDto, String hashtag) {
        String dateString = tweetDto.getLocalDateTime().toString().substring(0, 10);
        return hashtag + dateString;
    }

    /**
     * Converts a list of TweetDto objects into a list of Hashtag objects and saves them into the Hashtag repository
     * @param tweetDtoList
     * @param hashtagHashMap
     * @return
     * A list of Hashtag objects
     */
    @Override
    public List<Hashtag> tweetDtoListToHashtagList(List<TweetDto> tweetDtoList, HashMap<String, Hashtag> hashtagHashMap) {
        for (TweetDto tweetDto : tweetDtoList) {
            List<String> stringHashtagList = tweetDto.getHashtagList();

            for (String stringHashtag : stringHashtagList) {
                String hashtagId = createHashtagId(tweetDto, stringHashtag);

                if (!hashtagHashMap.containsKey(hashtagId)) {
                    hashtagHashMap.put(hashtagId, new Hashtag(
                            hashtagId,
                            stringHashtag,
                            1L,
                            tweetDto.getLocalDateTime().toLocalDate(),
                            tweetDto.getTweet_like_count(),
                            tweetDto.getTweet_retweet_count(),
                            tweetDto.getGeneral_sentiment()
                    ));
                } else {
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

        hashtagRepository.saveAll(hashtagList);
        hashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());

        return hashtagList;
    }

    /**
     * Deletes all the Hashtag objects from the Hashtag database
     */
    @Override
    public void clearHashtagDatabase() {
        hashtagRepository.deleteAll();
    }

    /**
     * Gets a list of all Hashtag objects from the Hashtag Database
     * @return
     * A List of Hashtag from the Hashtag database
     */
    @Override
    public List<Hashtag> getAllHashtagsFromDatabase() {
        List<Hashtag> hashtagList = hashtagRepository.findAll();
        hashtagList.sort(Comparator.comparing(Hashtag::getTimestamp).reversed());
        return hashtagRepository.findAll();
    }

    /**
     * Gets a list of the most popular hashtags based on number of occurrence
     * @return
     * A list of the most popular hashtags based on number of occurrence
     */
    @Override
    public List<Hashtag> getTodaysTop5Hashtags() {
        List<Hashtag> hashtagList = hashtagRepository.findAll();
        hashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());
        List<Hashtag> todaysTop5HashtagList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            todaysTop5HashtagList.add(hashtagList.get(i));
        }

        todaysTop5HashtagList.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());
        return todaysTop5HashtagList;
    }

    /**
     * Converts a Hashtag object into a HashtagDto object
     * @param hashtag
     * @return
     * A HashtagDto object
     */
    @Override
    public HashtagDto hashtagToHashtagDto(Hashtag hashtag) {
        return new HashtagDto(
                hashtag.getHashtag_id(),
                hashtag.getHashtag_name(),
                hashtag.getLike_count(),
                getDailyHashtagCount(hashtag),
                weeklyHashtagCount(hashtag),
                weeklyGeneralSentiment(hashtag),
                getGeneralSentimentOfTheDay(hashtag),
                getGeneralSentimentOfTheWeek(hashtag),
                getDailyRetweetCount(hashtag),
                hashtag.getRetweet_count(),
                hashtag.getGeneral_sentiment(),
                hashtag.getNum_of_occurrence(),
                hashtag.getTimestamp()
        );
    }

    /**
     * Converts a list of Hashtag objects into a list of HashtagDtos
     * @param hashtagList
     * @return
     * A list of HashtagDtos
     */

    @Override
    public List<HashtagDto> hashtagListToHashtagDtoList(List<Hashtag> hashtagList) {
        List<HashtagDto> hashtagDtoList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            hashtagDtoList.add(hashtagToHashtagDto(hashtagList.get(i)));
        }

        return hashtagDtoList;
    }

    /**
     * Saves individual Hashtag objects into the Hashtag repository
     * @param hashtagList
     */
    @Override
    public void saveHashtagList(List<Hashtag> hashtagList) {
        for (Hashtag hashtag : hashtagList) {
            hashtagRepository.save(hashtag);
        }
    }

    /**
     * Get the number of times a specific hashtag has been searched in a week
     * @param weeklyHashtagList List of a single type of Hashtag over the last seven days based
     * @return
     * A Long object that is the count of number of occurrence of a Hashtag object in a week
     */
    private Long countOfWeek(List<Hashtag> weeklyHashtagList) {
        if (weeklyHashtagList.isEmpty()) return 0L;

        Long occurrenceCount = 0L;
        for (Hashtag hashtag : weeklyHashtagList) {
            occurrenceCount += hashtag.getNum_of_occurrence();
        }

        return occurrenceCount;
    }

    /**
     * Get an Array of size 4 that contains the number of occurrence of a Hashtag over 4 weeks
     * Each index in the Array represents the week in relation to the date of the Hashtag object
     * Index 0 will be the oldest week while index 4 will be the current week
     * @param hashtag
     * @return
     * A Long Array of size 4 with each index containing the weekly number of occurrence of a Hashtag object
     */

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

        return new Long[]{week1OccurrenceCount, week2OccurrenceCount, week3OccurrenceCount, week4OccurrenceCount};
    }

    /**
     * Returns the GeneralSentiment object of a Hashtag over a specific week
     * Will contain the count of the number of positive and negative sentiments
     * @param hashtagList List of a single type of Hashtag over the last seven days based
     * @return
     * A GeneralSentiment object of a Hashtag over a specific week
     */
    private GeneralSentiment generalSentimentOfTheWeek(List<Hashtag> hashtagList) {
        if(hashtagList.isEmpty()) return new GeneralSentiment(
                0L,
                0L
        );

        Long positiveCount = 0L;
        Long negativeCount = 0L;

        for(Hashtag hashtag: hashtagList) {
            GeneralSentiment currentSentiment = getGeneralSentimentOfTheDay(hashtag);
            positiveCount += currentSentiment.getPositive_sentiment();
            negativeCount += currentSentiment.getNegative_sentiment();
        }



        return new GeneralSentiment(
                positiveCount,
                negativeCount
        );
    }

    /**
     * Get an Array of size 4 that contains the GeneralSentiment of a Hashtag over 4 weeks
     * Each index in the Array represents the week in relation to the date of the Hashtag object
     * Index 0 will be the oldest week while index 4 will be the current week
     * @param hashtag
     * @return
     * A GeneralSentiment Array of size 4 with each index containing the GeneralSentiment of a Hashtag object
     */
    private GeneralSentiment[] weeklyGeneralSentiment(Hashtag hashtag) {
        LocalDate week4EndDate = hashtag.getTimestamp();
        LocalDate week4StartDate = week4EndDate.minusDays(7L);
        List<Hashtag> week4HashtagList = hashtagRepository.findAllByTimestampBetween(week4StartDate, week4EndDate);
        week4HashtagList.removeIf(week4Hashtag -> (!week4Hashtag.getHashtag_name().equals(hashtag.getHashtag_name())));
        GeneralSentiment week4GeneralSentiment = generalSentimentOfTheWeek(week4HashtagList);

        LocalDate week3EndDate = week4StartDate.minusDays(1L);
        LocalDate week3StartDate = week3EndDate.minusDays(7L);
        List<Hashtag> week3HashtagList = hashtagRepository.findAllByTimestampBetween(week3StartDate, week3EndDate);
        week3HashtagList.removeIf(week3Hashtag -> (!week3Hashtag.getHashtag_name().equals(hashtag.getHashtag_name())));
        GeneralSentiment week3GeneralSentiment = generalSentimentOfTheWeek(week3HashtagList);

        LocalDate week2EndDate = week3StartDate.minusDays(1L);
        LocalDate week2StartDate = week2EndDate.minusDays(7L);
        List<Hashtag> week2HashtagList = hashtagRepository.findAllByTimestampBetween(week2StartDate, week2EndDate);
        week2HashtagList.removeIf(week2Hashtag -> (!week2Hashtag.getHashtag_name().equals(hashtag.getHashtag_name())));
        GeneralSentiment week2GeneralSentiment = generalSentimentOfTheWeek(week2HashtagList);

        LocalDate week1EndDate = week2StartDate.minusDays(1L);
        LocalDate week1StartDate = week1EndDate.minusDays(7L);
        List<Hashtag> week1HashtagList = hashtagRepository.findAllByTimestampBetween(week1StartDate, week1EndDate);
        week1HashtagList.removeIf(week1Hashtag -> (!week1Hashtag.getHashtag_name().equals(hashtag.getHashtag_name())));
        GeneralSentiment week1GeneralSentiment = generalSentimentOfTheWeek(week1HashtagList);

        return new GeneralSentiment[]{week1GeneralSentiment, week2GeneralSentiment, week3GeneralSentiment, week4GeneralSentiment};
    }

    /**
     * Returns the GeneralSentiment object of a Hashtag over a day
     * Will contain the count of the number of positive and negative sentiments
     * @param hashtag
     * @return
     * A GeneralSentiment object of a Hashtag over a day
     */
    private GeneralSentiment getGeneralSentimentOfTheDay(Hashtag hashtag) {
        LocalDate currentDate = hashtag.getTimestamp();
        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampIs(currentDate);
        hashtagList.removeIf(
                currentHashtag -> (!currentHashtag.getHashtag_name().equals(hashtag.getHashtag_name()))
        );

        Long positiveCount = 0L;
        Long negativeCount = 0L;

        for (Hashtag currentHashtag : hashtagList) {
            if (currentHashtag.getGeneral_sentiment() < 0) {
                negativeCount += 1;

            } else {
                positiveCount += 1;
            }
        }
        return new GeneralSentiment(
                positiveCount,
                negativeCount
        );
    }

    /**
     * Get an Array of size 7 that contains the number of occurrence of a Hashtag over 7 days
     * Each index in the Array represents the day in relation to the date of the Hashtag object
     * Index 0 will be the oldest day while index 4 will be the current day
     * @param hashtag
     * @return
     * A Long Array of size 7 that contains the number of occurrence of a Hashtag over 7 days
     */
    @Override
    public Long[] getDailyHashtagCount(Hashtag hashtag) {
        LocalDate hashtagDate = hashtag.getTimestamp();
        LocalDate startingDate = hashtagDate.minusDays(6L);

        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampBetweenOrderByTimestampAsc(startingDate, hashtagDate);
        hashtagList.removeIf(
                currentHashtag -> (!currentHashtag.getHashtag_name().equals(hashtag.getHashtag_name()))
        );

        Long[] dailyHashtagCount = new Long[7];
        Arrays.fill(dailyHashtagCount, Long.parseLong("0"));

        hashtagList.forEach(currentHashtag -> {
            LocalDate currentHashtagDate = currentHashtag.getTimestamp();
            int differenceInDays = Math.toIntExact(ChronoUnit.DAYS.between(startingDate, currentHashtagDate));
            dailyHashtagCount[differenceInDays] = currentHashtag.getNum_of_occurrence();
        });

        return dailyHashtagCount;
    }

    /**
     * Get an Array of size 7 that contains the number of retweets of a Hashtag over 7 days
     * Each index in the Array represents the day in relation to the date of the Hashtag object
     * Index 0 will be the oldest day while index 4 will be the current day
     * @param hashtag
     * @return
     * A Long Array of size 7 that contains the number of retweets of a Hashtag over 7 days
     */
    private Long[] getDailyRetweetCount(Hashtag hashtag) {
        Long[] dailyRetweetCount = new Long[7];
        Arrays.fill(dailyRetweetCount, 0L);
        LocalDate hashtagDate = hashtag.getTimestamp();
        LocalDate startingDate = hashtagDate.minusDays(6L);
        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampBetween(startingDate, hashtagDate);
        hashtagList.removeIf(
                currentHashtag -> (!currentHashtag.getHashtag_name().equals(hashtag.getHashtag_name()))
        );

        hashtagList.forEach(currentHashtag -> {
            LocalDate currentHashtagDate = currentHashtag.getTimestamp();
            int differenceInDays = Math.toIntExact(ChronoUnit.DAYS.between(startingDate, currentHashtagDate));
            dailyRetweetCount[differenceInDays] = currentHashtag.getRetweet_count();
        });


        return dailyRetweetCount;
    }

    /**
     * Returns the GeneralSentiment object of a Hashtag over a week
     * Will contain the count of the number of positive and negative sentiments
     * @param hashtag
     * @return
     * A GeneralSentiment object of a Hashtag over a wekk
     */
    private GeneralSentiment getGeneralSentimentOfTheWeek(Hashtag hashtag) {
        GeneralSentiment[] dailyGeneralSentiment = new GeneralSentiment[7];
        Arrays.fill(dailyGeneralSentiment,
                new GeneralSentiment(
                        0L,
                        0L
                ));

        LocalDate hashtagDate = hashtag.getTimestamp();
        LocalDate startingDate = hashtagDate.minusDays(6L);
        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampBetween(startingDate, hashtagDate);
        hashtagList.removeIf(
                currentHashtag -> (!currentHashtag.getHashtag_name().equals(hashtag.getHashtag_name()))
        );

        hashtagList.forEach(currentHashtag -> {
            LocalDate currentHashtagDate = currentHashtag.getTimestamp();
            int differenceInDays = Math.toIntExact(ChronoUnit.DAYS.between(startingDate, currentHashtagDate));
            dailyGeneralSentiment[differenceInDays] = getGeneralSentimentOfTheDay(currentHashtag);
        });

        Long positiveSentiment = 0L;
        Long negativeSentiment = 0L;

        for(GeneralSentiment generalSentiment : dailyGeneralSentiment) {
            positiveSentiment += generalSentiment.getPositive_sentiment();
            negativeSentiment += generalSentiment.getNegative_sentiment();
        }

        return new GeneralSentiment(
                positiveSentiment,
                negativeSentiment
        );
    }
}

