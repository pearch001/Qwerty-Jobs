package com.qwertyjobs.qwertjobsapi.api;

import com.qwertyjobs.qwertjobsapi.model.entities.Feedback;
import com.qwertyjobs.qwertjobsapi.services.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/feedback")
@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping(value = "/add")
    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback){

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/feedback/add").toUriString());
        return ResponseEntity.created(uri).body(feedbackService.saveFeedback(feedback));
    }
    @GetMapping(value = "/admin/getFeedback")
    public List<Feedback> getFeedback(){
        return feedbackService.loadFeedback();
    }
    @DeleteMapping(value = "/admin/deleteFeedback/{Id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable("Id") Long id){
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok().build();
    }
}
