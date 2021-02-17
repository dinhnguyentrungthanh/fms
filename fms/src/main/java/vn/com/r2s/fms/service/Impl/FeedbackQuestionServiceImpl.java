package vn.com.r2s.fms.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.FeedbackQuestion;
import vn.com.r2s.fms.entity.FeedbackQuestionID;
import vn.com.r2s.fms.repository.FeedbackQuestionIDRepository;
import vn.com.r2s.fms.service.FeedbackQuesionService;
import vn.com.r2s.fms.service.Impl.ServiceResult.Status;

@Service
public class FeedbackQuestionServiceImpl implements FeedbackQuesionService{

	@Autowired FeedbackQuestionIDRepository feedbackQuestionIDRepository;
	
	@Override
	public Optional<FeedbackQuestion> findFeedbackQuesionByFeedbackID(Integer feedbackID, Integer questionID) {
	    FeedbackQuestionID feedbackQuestionID = new FeedbackQuestionID();
	    feedbackQuestionID.setQuestionID(questionID);
	    feedbackQuestionID.setFeedbackID(feedbackID);
	    
		return feedbackQuestionIDRepository.findById(feedbackQuestionID);
	}
	
	@Override
	public ServiceResult findAll() {
		ServiceResult result = new ServiceResult();
		result.setData(feedbackQuestionIDRepository.findAll());
		return result;
	}
	
	@Override
	public List<FeedbackQuestion> findAllFbqs() {
		return feedbackQuestionIDRepository.findAll();
	}
	
	@Override
	public ServiceResult findById(int id) {
		ServiceResult result = new ServiceResult();
		FeedbackQuestion feedbackQuestion = feedbackQuestionIDRepository.findById(id).orElse(null);
		result.setData(feedbackQuestion);
		return result;
	}
	
	@Override
	public ServiceResult create(FeedbackQuestion feedbackQuestion) {
		ServiceResult result = new ServiceResult();
		result.setData(feedbackQuestionIDRepository.save(feedbackQuestion));
		return result;
	}
	
	@Override
	public ServiceResult update(FeedbackQuestion feedbackQuestion) {
		ServiceResult result = new ServiceResult();

		if (!feedbackQuestionIDRepository.findById(feedbackQuestion.getId()).isPresent()) {
			result.setStatus(Status.FAILED);
			result.setMessage("Customer Not Found");
		} else {
			result.setData(feedbackQuestionIDRepository.save(feedbackQuestion));
		}
		return result;
	}

	@Override
	public ServiceResult delete(int id) {
		ServiceResult result = new ServiceResult();

		FeedbackQuestion feedbackQuestion = feedbackQuestionIDRepository.findById(id).orElse(null);
		if (feedbackQuestion == null) {
			result.setStatus(Status.FAILED);
			result.setMessage("Customer Not Found");
		} else {
			feedbackQuestionIDRepository.delete(feedbackQuestion);
			result.setMessage("success");
		}
		return result;
	}
	
	@Override
	public FeedbackQuestion saveFQ(FeedbackQuestion feedbackQuestionID) {
		return feedbackQuestionIDRepository.save(feedbackQuestionID);
	}
	
	@Override
	public List<FeedbackQuestion> getAllFeedbackQuestion() {
		return feedbackQuestionIDRepository.findAll();
	}
	
    @Override
	public void deleteFQ(FeedbackQuestion FeedbackID) {
		feedbackQuestionIDRepository.delete(FeedbackID);
	}
	
}

