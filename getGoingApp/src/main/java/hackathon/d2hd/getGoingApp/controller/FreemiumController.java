package hackathon.d2hd.getGoingApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.FreemiumService;
import hackathon.d2hd.getGoingApp.service.HashtagService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/freemium")
public class FreemiumController {

    @Value("${api_key}")
    private String api_key;

    @Value("${keyword}")
    private String keyword;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private HashtagService hashtagService;

    @GetMapping()
    public String hashscraperCall() {
        WebClient client = WebClient.create("https://www.hashscraper.com/api/twitter/");
        String response = client.post()
                .uri("?apikey=" + api_key + "&keyword=" + keyword)
                .header("Content-Type", "application/json version=2")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

    @GetMapping("/getTweetDtoList")
    public List<TweetDto> getTweetDtoList() throws JsonProcessingException {
        String hashscraperResponseBody = hashscraperCall();
        return tweetService.JsonToTweetDeserializer(hashscraperResponseBody);
    }

}

