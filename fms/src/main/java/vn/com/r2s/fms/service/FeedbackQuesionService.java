package vn.com.r2s.fms.service;

import java.util.List;
import java.util.Optional;

import vn.com.r2s.fms.entity.FeedbackQuestion;
import vn.com.r2s.fms.service.Impl.ServiceResult;

public interface FeedbackQuesionService {

	FeedbackQuestion saveFQ(FeedbackQuestion feedbackQuestion);

	List<FeedbackQuestion> getAllFeedbackQuestion();

	ServiceResult findAll();

	ServiceResult create(FeedbackQuestion feedbackQuestion);

	ServiceResult update(FeedbackQuestion feedbackQuestion);

	ServiceResult findById(int id);

	ServiceResult delete(int id);

	Optional<FeedbackQuestion> findFeedbackQuesionByFeedbackID(Integer feedbackID, Integer questionID);

	List<FeedbackQuestion> findAllFbqs();

    void deleteFQ(FeedbackQuestion FeedbackID);
	
}

