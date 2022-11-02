package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataModel.Topic;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FreemiumService {
    List<Topic> freemiumWorkflow(File file) throws IOException;
}
