package hackathon.d2hd.getGoingApp;

import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.service.FreemiumService;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class GetGoingApplicationTests {
	private File hashScraperJsonFile = new File("/Users/seanmarinas/appetizer/getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperTestData.json");

	@Autowired
	private HashtagService hashtagService;

	@Autowired
	private TweetService tweetService;

	@Autowired
	private FreemiumService freemiumService;

	GetGoingApplicationTests() throws IOException {
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testFreemiumWorkflow() throws IOException {
//		//Clear both repositories for testing purposes
//		tweetService.clearDatabase();
//		hashtagService.clearTopicDatabase();
//
//		//Populate TweetRepository with return from Hashscraper
////		List<Tweet> hashscraperTweetList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
////		tweetService.saveTweetList(hashscraperTweetList);
//
//		//Populate HashtagRepository
//		List<Tweet> tweetRepositoryTweetList = tweetService.getAllTweetsFromDatabase();
//		List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetRepositoryTweetList);
//		List<Hashtag> topicDtoToHashtagList = hashtagService.tweetDtoListToTopicList(tweetDtoList);
//		hashtagService.saveTopicList(topicDtoToHashtagList);
//
//		//Pull new topic list from the HashtagRepository
//		List<Hashtag> topicRepositoryHashtagList = hashtagService.getAllTopicsFromDatabase();
//		long topicListCount = hashtagService.sumOfAllTopicOccurrences(topicDtoToHashtagList);
//		long topicRepositoryTopicListCount = hashtagService.sumOfAllTopicOccurrences(topicRepositoryHashtagList);
//
//		//Check that topicDtoToHashtagList and topicRepositoryHashtagList have the same number of elements
//		Assertions.assertEquals(topicListCount, topicRepositoryTopicListCount);
//
//		//Check that the sum of all the number of occurrences for each topic as the same as the count in tweetRepositoryTweetList
//		long tweetRepositoryTweetListCount = tweetRepositoryTweetList.stream().count();
//		long sumOfAllTopicOccurrences = hashtagService.sumOfAllTopicOccurrences(topicDtoToHashtagList);
//		Assertions.assertEquals(tweetRepositoryTweetListCount, sumOfAllTopicOccurrences);
	}

}
