package com.qwertyjobs.qwertjobsapi.api;

import com.qwertyjobs.qwertjobsapi.model.entities.Feedback;
import com.qwertyjobs.qwertjobsapi.model.entities.Job;
import com.qwertyjobs.qwertjobsapi.services.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/job")
@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping(value = "/admin/add")
    public ResponseEntity<Job> addJob(@RequestBody Job job){

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/job/add").toUriString());
        return ResponseEntity.created(uri).body(jobService.saveJob(job));
    }

    @GetMapping(value = "/getJobs/{offset}")
    public List<Job> getJobs(@PathVariable Long offset){
        return jobService.loadJob(offset);
    }
    @GetMapping(value = "/getJobs/{jobTitle}")
    public List<Job> getFeedback(){
        return jobService.loadJob();
    }
    @DeleteMapping(value = "/admin/deleteJob/{Id}")
    public ResponseEntity<?> deleteJob(@PathVariable("Id") Long id){
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }
}
