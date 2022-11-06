package hackathon.d2hd.getGoingApp.repository;

import hackathon.d2hd.getGoingApp.dataModel.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
    List<Topic> findAllByTimestampBetweenOrderByTimestampAsc (LocalDateTime startDate, LocalDateTime endDate);
}
