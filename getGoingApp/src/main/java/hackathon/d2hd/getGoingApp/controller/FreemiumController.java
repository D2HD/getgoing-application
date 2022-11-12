package hackathon.d2hd.getGoingApp.controller;

import hackathon.d2hd.getGoingApp.dataTransferObject.TopicDto;
import hackathon.d2hd.getGoingApp.service.FreemiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/freemium")
public class FreemiumController {
    @Autowired
    private FreemiumService freemiumService;

    @GetMapping
    public List<TopicDto> top5TopicList() throws IOException {
        File file = new File("/Users/seanmarinas/appetizer/getGoingApp/src/test/java/hackathon/d2hd/getGoingApp/testData/HashscraperTestData.json");
        return freemiumService.freemiumWorkflow(file);
    }


}

