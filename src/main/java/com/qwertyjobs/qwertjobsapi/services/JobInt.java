package com.qwertyjobs.qwertjobsapi.services;

import com.qwertyjobs.qwertjobsapi.model.entities.Feedback;
import com.qwertyjobs.qwertjobsapi.model.entities.Job;

import java.util.List;

public interface JobInt {
    Job saveJob(Job job);
    List<Job> loadJob();
    void deleteJob(Long id);
}
