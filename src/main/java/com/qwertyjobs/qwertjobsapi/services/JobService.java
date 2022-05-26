package com.qwertyjobs.qwertjobsapi.services;

import com.qwertyjobs.qwertjobsapi.dao.JobDao;
import com.qwertyjobs.qwertjobsapi.model.entities.Feedback;
import com.qwertyjobs.qwertjobsapi.model.entities.Job;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class JobService implements JobInt{
    private final JobDao jobDao;


    @Override
    public Job saveJob(Job job) {
        log.info("Saving Job");
        return jobDao.save(job);
    }

    @Override
    public List<Job> loadJob(Long offset) {
        return (List<Job>) jobDao.selectByOffset(offset);
    }

    @Override
    public List<Job> searchJobs(String jobtitle, String city, Long salaryBound, int offset) {
        return null;
    }



    @Override
    public void deleteJob(Long id) {
        Boolean value = jobDao.findByID(id);
        Job job = jobDao.selectByID(id);
        if (value) {
            jobDao.delete(job);
        } else {
            throw new IllegalStateException("Job not found");
        }
    }
}
