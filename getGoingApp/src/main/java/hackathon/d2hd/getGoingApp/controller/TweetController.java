package hackathon.d2hd.getGoingApp.controller;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDto;
import hackathon.d2hd.getGoingApp.service.TopicService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TweetController {
    File testFile = new File("/Users/seanmarinas/appetizer/getGoingApp/src/main/resources/HashscraperTestData.json");
    @Autowired
    private TopicService topicService;

    @Autowired
    private TweetService tweetService;


}

