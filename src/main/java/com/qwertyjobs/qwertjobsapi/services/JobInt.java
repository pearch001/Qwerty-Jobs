package com.qwertyjobs.qwertjobsapi.services;

import com.qwertyjobs.qwertjobsapi.model.entities.Feedback;
import com.qwertyjobs.qwertjobsapi.model.entities.Job;

import java.util.List;

public interface JobInt {
    Job saveJob(Job job);
    List<Job> loadJob(int offset);
    List<Job> searchJobs(String jobTitle, String city, Long salaryBound, int offset);
    int allSearchedJobs(String jobTitle, String city, Long salaryBound);
    List<Job> loadAllJob();
    void deleteJob(Long id);
}
