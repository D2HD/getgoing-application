package hackathon.d2hd.getGoingApp.repository;

import hackathon.d2hd.getGoingApp.dataModel.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, String> {
}

