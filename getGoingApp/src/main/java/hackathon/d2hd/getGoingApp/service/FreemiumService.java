package hackathon.d2hd.getGoingApp.service;

import hackathon.d2hd.getGoingApp.dataTransferObject.HashtagDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FreemiumService {
    List<HashtagDto> freemiumWorkflow(File file) throws IOException;
}
