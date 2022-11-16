package hackathon.d2hd.getGoingApp.repository;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, String> {
    List<Hashtag> findAllByTimestampBetweenOrderByTimestampAsc (LocalDateTime startDate, LocalDateTime endDate);
    List<Hashtag> findAllByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Hashtag> findAllByTimestampContaining(LocalDateTime currentDateTime);
}
