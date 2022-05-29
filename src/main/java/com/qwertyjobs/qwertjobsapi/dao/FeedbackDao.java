package com.qwertyjobs.qwertjobsapi.dao;

import com.qwertyjobs.qwertjobsapi.model.entities.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackDao extends CrudRepository<Feedback, Long> {
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END FROM "
            + "feedback f WHERE f.id = ?1")
    Boolean findByID(Long id);
    @Query("SELECT f FROM "
            + "feedback f WHERE f.id = ?1")
    Feedback selectByID(Long id);
    @Query(value = "SELECT * FROM feedback ORDER BY id OFFSET ?1 ROWS FETCH NEXT 10 ROWS ONLY", nativeQuery = true)
    List<Feedback> selectByOffset(Long id);

}
