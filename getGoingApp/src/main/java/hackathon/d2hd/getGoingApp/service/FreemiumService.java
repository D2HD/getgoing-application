package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import hackathon.d2hd.getGoingApp.dataTransferObject.TopicDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FreemiumService {
    List<TopicDto> freemiumWorkflow(File file) throws IOException;
}
