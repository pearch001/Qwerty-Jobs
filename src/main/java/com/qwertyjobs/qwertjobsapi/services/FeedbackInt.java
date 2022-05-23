package com.qwertyjobs.qwertjobsapi.services;

import com.qwertyjobs.qwertjobsapi.model.entities.Feedback;

import java.util.List;

public interface FeedbackInt {
    Feedback  saveFeedback(Feedback feedback);
    List<Feedback> loadFeedback();
    void deleteFeedback(Long id);
}
