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

    @Query(value = "SELECT * FROM job " +
            "WHERE title LIKE '%?1%' AND" +
            "WHERE city LIKE '%?2%' AND" +
            "WHERE salaryUpperBound >= ?3 LIMIT 10 OFFSET ?4", nativeQuery = true)
    List<Job> searchJobs(String jobTitle, String city, Long salaryBound, int offset);

    @Query(value = "SELECT COUNT(id) FROM job " +
            "WHERE title LIKE '%?1%' AND" +
            "WHERE city LIKE '%?2%' AND" +
            "WHERE salaryUpperBound >= ?3 ", nativeQuery = true)
    int allSearchedJobs(String jobTitle, String city, Long salaryBound);
}
