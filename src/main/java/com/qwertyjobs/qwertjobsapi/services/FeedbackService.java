package com.qwertyjobs.qwertjobsapi.services;

import com.qwertyjobs.qwertjobsapi.dao.FeedbackDao;
import com.qwertyjobs.qwertjobsapi.model.entities.Feedback;
import com.qwertyjobs.qwertjobsapi.model.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FeedbackService implements FeedbackInt{
    private final FeedbackDao feedbackDao;
    @Override
    public Feedback saveFeedback(Feedback feedback) {
        log.info("Saving Feedback");
        return feedbackDao.save(feedback);
    }

    @Override
    public List<Feedback> loadFeedbacks(Long id) {
        return (List<Feedback>) feedbackDao.selectByOffset(id);
    }

    @Override
    public int countFeedbacks() {
        List<Feedback> feedbacks = (List<Feedback>) feedbackDao.findAll();
        return feedbacks.size();
    }

    @Override
    public void deleteFeedback(Long id) throws IllegalStateException{
        Boolean value = feedbackDao.findByID(id);
        Feedback feedback = feedbackDao.selectByID(id);
        if (value) {
            feedbackDao.delete(feedback);
        } else {
            throw new IllegalStateException("Feedback not found");
        }

    }
}
