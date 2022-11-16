package hackathon.d2hd.getGoingApp.repository;

import hackathon.d2hd.getGoingApp.dataModel.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, String> {
    List<Hashtag> findAllByTimestampBetweenOrderByTimestampAsc (LocalDate startDate, LocalDate endDate);
    List<Hashtag> findAllByTimestampBetween (LocalDate startDate, LocalDate endDate);
    List<Hashtag> findAllByTimestampIs(LocalDate currentDateTime);
}
