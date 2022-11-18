package hackathon.d2hd.getGoingApp.hashtagServiceTests;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.GeneralSentiment;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.repository.HashtagRepository;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Logger;

@SpringBootTest
public class HashtagServiceTests {

    private Logger logger = Logger.getLogger(HashtagServiceTests.class.getName());
    private File hashScraperJsonFile = new File("/Users/seanmarinas/appetizer/getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperForSale.json");
    @Autowired
    private TweetService tweetService;
    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Test
    public void testTweetDtoListToHashtagListAPI() throws IOException {
        List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetService.getAllTweets());
        HashMap<String, Hashtag> hashtagHashMap = new HashMap<>();
        List<Hashtag> hashtagList = hashtagService.tweetDtoListToHashtagList(tweetDtoList, hashtagHashMap);
        hashtagService.saveHashtagList(hashtagList);
    }

    @Test
    public void testSevenDayTop5HashtagListByCountAPI() {
        LocalDate currentDateTime = LocalDate.now();
        List<Hashtag> hashtagList = hashtagService.sevenDayTop5HashtagListByCount(currentDateTime);
        hashtagService.displayHashtags(hashtagList);
    }

    @Test
    public void testSevenDayTop5HashtagListByLikeAPI() {
        LocalDate currentDateTime = LocalDate.now();
        List<Hashtag> hashtagList = hashtagService.sevenDayTop5HashtagListByLike(currentDateTime);
        hashtagService.displayHashtags(hashtagList);
    }

    @Test
    public void currentTop5HashtagListAPI() {
        LocalDate now = LocalDate.now().minusDays(4L);
        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampIs(now);
        hashtagService.displayHashtags(hashtagList);
    }

    @Test
    public void hashtagRepoMethodsTest() {
        LocalDate start = LocalDate.of(2022, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2022, Month.DECEMBER, 31);
        List<Hashtag> hashtagList1 = hashtagRepository.findAllByTimestampBetween(start, start);

        hashtagService.displayHashtags(hashtagList1);
    }


    private Long countOfWeek(List<Hashtag> hashtagList) {
        if(hashtagList.isEmpty()) return 0L;

        Long occurrenceCount = 0L;
        for(Hashtag hashtag: hashtagList) {
            occurrenceCount += hashtag.getNum_of_occurrence();
        }

        return occurrenceCount;
    }
    private Long[] weeklyCount(Hashtag hashtag) {
        //For week 4
        LocalDate week4EndDate = hashtag.getTimestamp();
        LocalDate week4StartDate = week4EndDate.minusDays(7L);
        List<Hashtag> week4HashtagList = hashtagRepository.findAllByTimestampBetween(week4StartDate, week4EndDate);
        logger.info(String.valueOf(week4HashtagList.size()));
        week4HashtagList.removeIf(week4Hashtag -> (!week4Hashtag.getHashtag_name().equals(hashtag.getHashtag_name())));
        logger.info(String.valueOf(week4HashtagList.size()));
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

    @Test
    public void testGetHashtagOccurrenceHistory() {
        List<Hashtag> hashtagList1 = hashtagService.getAllHashtagsFromDatabase();
        hashtagList1.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());
        Hashtag hashtag = hashtagList1.get(0);
        System.out.println(hashtag.getNum_of_occurrence());

        LocalDate hashtagDate = hashtag.getTimestamp();
        LocalDate startingDate = hashtagDate.minusDays(6L);

        //Returns all hashtags after this starting date
        //Need to filter and get only the hashtags that match the hashtag_name of the parameter
        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampBetweenOrderByTimestampAsc(startingDate, hashtagDate);
        hashtagList.removeIf(
                currentHashtag -> (!currentHashtag.getHashtag_name().equals(hashtag.getHashtag_name()))
        );

        Long [] dailyHashtagCount = new Long[7];
        Arrays.fill(dailyHashtagCount, Long.parseLong("0"));

        hashtagList.forEach(currentHashtag -> {
            LocalDate currentHashtagDate = currentHashtag.getTimestamp();
            int differenceInDays = Math.toIntExact(ChronoUnit.DAYS.between(startingDate, currentHashtagDate));
            dailyHashtagCount[differenceInDays] = currentHashtag.getNum_of_occurrence();
        });

        for(Long l: dailyHashtagCount) {
            System.out.println(l);
        }
    }

    @Test
    public void testGetDailyRetweetCount() {
        List<Hashtag> hashtagList1 = hashtagService.getAllHashtagsFromDatabase();
        hashtagList1.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());
        Hashtag hashtag = hashtagList1.get(0);

        Long[] dailyRetweetCount = hashtagService.getDailyHashtagCount(hashtag);
        for(Long l: dailyRetweetCount) {
            System.out.println(l);
        }
    }

    private GeneralSentiment getGeneralSentimentOfTheDay(Hashtag hashtag) {
        LocalDate currentDate = hashtag.getTimestamp();
        List<Hashtag> hashtagList = hashtagRepository.findAllByTimestampIs(currentDate);
        hashtagList.removeIf(
                currentHashtag -> (!currentHashtag.getHashtag_name().equals(hashtag.getHashtag_name()))
        );

        Long positiveCount = 0L;
        Double positiveSentiment = Double.valueOf(0.0);
        Long negativeCount = 0L;
        Double negativeSentiment = Double.valueOf(0.0);

        Double positiveAverage = Double.valueOf(0.0);
        Double negativeAverage = Double.valueOf(0.0);

        for (Hashtag currentHashtag: hashtagList) {
            if(currentHashtag.getGeneral_sentiment() < 0) {
                negativeCount += 1;
                negativeSentiment += currentHashtag.getGeneral_sentiment();
                negativeAverage = negativeSentiment / negativeCount;

            }else{
                positiveCount += 1;
                positiveSentiment += currentHashtag.getGeneral_sentiment();
                positiveAverage = positiveSentiment / positiveCount;
            }
        }
        return new GeneralSentiment(
                positiveAverage,
                negativeAverage
        );
    }

    @Test
    public void getGeneralSentimentOfTheDay() {
        List<Hashtag> hashtagList1 = hashtagService.getAllHashtagsFromDatabase();
        hashtagList1.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());
        Hashtag hashtag = hashtagList1.get(0);

        GeneralSentiment generalSentiment = getGeneralSentimentOfTheDay(hashtag);
        System.out.println("Positive Sentiment: " + generalSentiment.getPositive_sentiment());
        System.out.println("Negative Sentiment: " + generalSentiment.getNegative_sentiment());

    }

    private GeneralSentiment generalSentimentOfTheWeek(List<Hashtag> hashtagList) {
        if(hashtagList.isEmpty()) return new GeneralSentiment(Double.valueOf(0.0), Double.valueOf(0.0));

        Double positiveSentiment = Double.valueOf(0.0);
        Double negativeSentiment = Double.valueOf(0.0);

        for(Hashtag hashtag: hashtagList) {
            GeneralSentiment currentSentiment = getGeneralSentimentOfTheDay(hashtag);
            positiveSentiment += currentSentiment.getPositive_sentiment();
            negativeSentiment += currentSentiment.getNegative_sentiment();
        }

        int denominator = hashtagList.size();

        return new GeneralSentiment(
                positiveSentiment / denominator,
                negativeSentiment / denominator
        );
    }
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

    @Test
    public void testWeeklyGeneralSentiment() {
        List<Hashtag> hashtagList1 = hashtagService.getAllHashtagsFromDatabase();
        hashtagList1.sort(Comparator.comparing(Hashtag::getNum_of_occurrence).reversed());
        Hashtag hashtag = hashtagList1.get(0);

        GeneralSentiment [] generalSentiments = weeklyGeneralSentiment(hashtag);

        for(GeneralSentiment g: generalSentiments) {
            System.out.println("Positive: " + g.getPositive_sentiment());
            System.out.println("Positive: " + g.getNegative_sentiment());
        }
    }

    @Test
    public void testSevenDayTop5HashtagDtoListByCount() {
        LocalDate currentDateTime = LocalDate.now();
        List <Hashtag> hashtagList = hashtagService.sevenDayTop5HashtagListByCount(currentDateTime);
        List<HashtagDto> hashtagDtoList = hashtagService.hashtagListToHashtagDtoList(hashtagList);
        Random rand = new Random();

        for(int i = 0; i < hashtagDtoList.size(); i++) {
            HashtagDto currentDto = hashtagDtoList.get(i);
            currentDto.setGeneral_sentiment_of_the_day(
                    new GeneralSentiment(
                            rand.nextDouble(0, 50),
                            rand.nextDouble(0, 50)
                    ));

            currentDto.setGeneral_sentiment_of_the_week(
                    new GeneralSentiment(
                            rand.nextDouble(0, 50),
                            rand.nextDouble(0, 50)
                    ));

            currentDto.setDaily_retweet_count(
                    new Long[]{
                            rand.nextLong(0, 100),
                            rand.nextLong(0, 100),
                            rand.nextLong(0, 100),
                            rand.nextLong(0, 100),
                            rand.nextLong(0, 100),
                            rand.nextLong(0, 100),
                            rand.nextLong(0, 100)
                    });
        }
    }

}
