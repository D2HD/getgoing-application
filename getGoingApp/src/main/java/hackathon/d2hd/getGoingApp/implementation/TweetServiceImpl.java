package hackathon.d2hd.getGoingApp.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.dataTransferObject.TweetDTO;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    private ObjectMapper objectMapper = new ObjectMapper();

    // TODO: 31/10/22 Sixth Change
    /*
    - this part might be a little confusing so you can refer to this for the documentation: https://www.baeldung.com/jackson-object-mapper-tutorial specifically section 3.4
    - Hashscraper returns a JSON object in the following format:
        {
            "result": "Success",
            "version": "v2",
            "next_token": "(string of next token)"
            "data": [array of the data of each tweet]
        }
    - everything in the branches represents the rootNode, so a root node has child nodes of "result", "version", "next_token" and "data
    - ObjectMapper is a class that reads in JSON and creates a JSONNode object, think of the above line but in a tree format
    - the tweet data we want is in the "data" key so we can access it by calling rootNode.get("data") this returns the values from the "data" child node in the form of a JsonNode
    - finally, objectMapper.readValue is a function that reads the node and returns it in the form of the class specified
    - you can change a single node into a single class, but since we have an array of objects to change, we used the following method to convert it, this part i copied from the documentation
    - look for Seventh Change to see my Unit tests
     */
    @Override
    public List<Tweet> JsonToTweetDeserializer(File jsonFile) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonFile);
        JsonNode dataNode = rootNode.get("data");
        return  objectMapper.readValue(dataNode.toString(), new TypeReference<List<Tweet>>() {});
    }

    // TODO: 30/10/22 Fourth Change
    /*
    - pretty self explanatory, I just parsed the attribute data from Tweet to match that of TweetDTO
    - look for the Fifth Change to see the implementation for converting a String to a LocalDateTime
     */
    @Override
    public TweetDTO tweetToTweetDTO(Tweet tweet) {
        return new TweetDTO(
                tweet.getValue1(),
                tweet.getValue2(),
                tweet.getValue3(),
                Long.parseLong(tweet.getValue4()),
                tweet.getValue5(),
                tweet.getValue6(),
                tweet.getValue7(),
                Long.parseLong(tweet.getValue8()),
                Long.parseLong(tweet.getValue9()),
                Long.parseLong(tweet.getValue10()),
                Long.parseLong(tweet.getValue11()),
                tweet.getValue12(),
                tweet.getValue13(),
                stringToLocalDateTime(tweet.getValue14()),
                tweet.getValue15()
        );
    }

    // TODO: 30/10/22  Fifth Change
    /*
    - so the first question you may ask is probably "why LocalDateTime?"
    - the format of the String from date is as such "2022-10-30 16:37:42 +9000"
    - i went online to search which time object matches this closely and LocalDateTime was the closest
    - psql also recognises it so no issue
    - so i started off by formatting the string
    - i had to remove the +9000 at the end of the string so i used a substring method to do it
    - next i used a DateTimeFormatter to format the date with the pattern below, I copied this part off StackOverflow
    - finally i returned a LocalDatetime object that accepts the string that i formatted as well as the format as parameters under its inbuilt parse function
    - look for the Sixth Change to see how i deserialized the JSON object from Hashscraper to a useable to a List of Tweets
     */
    @Override
    public LocalDateTime stringToLocalDateTime(String localDateTimeString) {
        String formattedString = localDateTimeString.substring(0, localDateTimeString.length()-6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(formattedString, formatter);
    }
}
