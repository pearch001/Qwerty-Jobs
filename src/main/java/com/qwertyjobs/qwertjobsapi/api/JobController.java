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

    @GetMapping(value = "/jobs/{offset}")
    public List<Job> getJobs(@PathVariable Long offset){
        return jobService.loadJob(offset);
    }

    @GetMapping(value = "/jobs/count")
    public int countJobs(){ return jobService.loadAllJob().size();}

    @GetMapping(value = "/jobs/count/{jobTitle}")
    public int countSearchedJobs(@PathVariable("jobTitle") String jobTitle,
                                 @RequestParam(required = false) String city,
                                 @RequestParam(required = false) Long salaryBound,
                                 @RequestParam(required = false) int offset){
        if (offset == 0){
            offset = 1;
        }
        return jobService.allSearchedJobs(jobTitle,city,salaryBound);
    }

    @GetMapping(value = "/jobs/{jobTitle}")
    public List<Job> getJobs(@PathVariable("jobTitle") String jobTitle,
                                 @RequestParam(required = false) String city,
                                 @RequestParam(required = false) Long salaryBound,
                                 @RequestParam(required = false) int offset){
        if (offset == 0){
            offset = 1;
        }
        return jobService.searchJobs(jobTitle,city,salaryBound,offset);
    }

    @DeleteMapping(value = "/admin/job/{Id}")
    public ResponseEntity<?> deleteJob(@PathVariable("Id") Long id){
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }
}
