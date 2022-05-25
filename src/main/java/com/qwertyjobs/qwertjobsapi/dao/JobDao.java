package com.qwertyjobs.qwertjobsapi.dao;

import com.qwertyjobs.qwertjobsapi.model.entities.Feedback;
import com.qwertyjobs.qwertjobsapi.model.entities.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobDao extends CrudRepository<Job,Long> {
    @Query("SELECT CASE WHEN COUNT(j) > 0 THEN TRUE ELSE FALSE END FROM "
            + "job j WHERE j.id = ?1")
    Boolean findByID(Long id);
    @Query("SELECT j FROM "
            + "job j WHERE j.id = ?1")
    Job selectByID(Long id);
    @Query(value = "SELECT * FROM job LIMIT 10 OFFSET ?1", nativeQuery = true)
    List<Job> selectByOffset(Long id);
}
