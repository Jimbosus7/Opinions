package website.opinion.opinion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import website.opinion.opinion.model.Opinion;
import website.opinion.opinion.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpinionDao extends JpaRepository<Opinion, Long> {
    List<Opinion> findAllByUser(User user);
    Optional<Opinion> findByTopic(String topic);
    @Query("SELECT o FROM Opinion o WHERE CONCAT(o.title, o.topic, o.details) LIKE %?1%")
    List<Opinion> findAllByTitleContainsOrTopicContainsOrDetailsContains(String search);
}
