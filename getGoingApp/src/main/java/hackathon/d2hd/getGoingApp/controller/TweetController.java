package hackathon.d2hd.getGoingApp.controller;

import hackathon.d2hd.getGoingApp.service.TopicService;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TweetController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private TweetService tweetService;

    @GetMapping("")
}

