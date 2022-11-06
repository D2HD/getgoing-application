package hackathon.d2hd.getGoingApp;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TopicDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.FreemiumService;
import hackathon.d2hd.getGoingApp.service.TopicService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GetGoingApplicationTests {
	private File hashScraperJsonFile = new File("/Users/seanmarinas/appetizer/getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperTestData.json");

	@Autowired
	private TopicService topicService;

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
		//Clear both repositories for testing purposes
		tweetService.clearDatabase();
		topicService.clearTopicDatabase();

		//Populate TweetRepository with return from Hashscraper
		List<Tweet> hashscraperTweetList = tweetService.JsonToTweetDeserializer(hashScraperJsonFile);
		tweetService.saveTweetList(hashscraperTweetList);

		//Populate TopicRepository
		List<Tweet> tweetRepositoryTweetList = tweetService.getAllTweetsFromDatabase();
		List<TweetDto> tweetDtoList = tweetService.tweetListToTweetDtoList(tweetRepositoryTweetList);
		List<Topic> topicDtoToTopicList = topicService.tweetDtoListToTopicList(tweetDtoList);
		topicService.saveTopicList(topicDtoToTopicList);

		//Pull new topic list from the TopicRepository
		List<Topic> topicRepositoryTopicList = topicService.getAllTopicsFromDatabase();
		long topicListCount = topicService.sumOfAllTopicOccurrences(topicDtoToTopicList);
		long topicRepositoryTopicListCount = topicService.sumOfAllTopicOccurrences(topicRepositoryTopicList);

		//Check that topicDtoToTopicList and topicRepositoryTopicList have the same number of elements
		Assertions.assertEquals(topicListCount, topicRepositoryTopicListCount);

		//Check that the sum of all the number of occurrences for each topic as the same as the count in tweetRepositoryTweetList
		long tweetRepositoryTweetListCount = tweetRepositoryTweetList.stream().count();
		long sumOfAllTopicOccurrences = topicService.sumOfAllTopicOccurrences(topicDtoToTopicList);
		Assertions.assertEquals(tweetRepositoryTweetListCount, sumOfAllTopicOccurrences);
	}

	@Test
	public void testNewFreemiumWorkflow() throws IOException {
		List<TopicDto> topicDtoList = freemiumService.freemiumWorkflow(hashScraperJsonFile);
		topicDtoList.forEach(topicDto -> {
			System.out.println(topicDto.getTopic_id() + ": " + topicDto.getNum_of_occurrence() + " " + topicDto.getPast_topic_count());
		});
	}
}
