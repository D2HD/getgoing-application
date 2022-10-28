package hackathon.d2hd.getGoingApp.implementation;

import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import hackathon.d2hd.getGoingApp.repository.TweetRepository;
import hackathon.d2hd.getGoingApp.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TweetServiceImpl implements TweetService {
    @Autowired
    TweetRepository repo;

    @Override
    public Tweet getTweet(String url) {
        return null;
    }

    @Override
    public List<Tweet> getTweetList() {
        try{
            return repo.findAll();
        }catch (Exception e) {
            throw new IllegalArgumentException("Unable to retrieve tweet list");
        }
    }

    @Override
    public Long countTotalTweets() {
        try{
            return repo.count();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to retrieve total tweet count");
        }
    }

    @Override
    public Long countByContent(String content) {
        return null;
    }

    @Override
    public void addTweet(Tweet tweet) {
        try{
            repo.save(tweet);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to add tweet, error message: " + e);
        }
    }

    @Override
    public void clearDatabase() {
        try{
            repo.deleteAll();
        }catch (Exception e) {
            throw new IllegalArgumentException("Unable to clear database");
        }
    }
}
