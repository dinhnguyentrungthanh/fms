package vn.com.r2s.fms.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.service.Impl.ServiceResult.Status;
import vn.com.r2s.fms.entity.Feedback;
import vn.com.r2s.fms.repository.FeedbackRepository;
import vn.com.r2s.fms.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Override
	public ServiceResult findAlll() {
		ServiceResult result = new ServiceResult();
		result.setData(feedbackRepository.findAll());
		return result;
	}
	
	@Override
	public List<Feedback> findAll() {
		return feedbackRepository.findAll();
	}
	
	@Override
	public ServiceResult findById(int feedbackID) {
		ServiceResult result = new ServiceResult();
		Feedback feedback = feedbackRepository.findById(feedbackID).orElse(null);
		result.setData(feedback);
		return result;
	}
	
	@Override
	public ServiceResult create(Feedback feedback) {
		ServiceResult result = new ServiceResult();
		result.setData(feedbackRepository.save(feedback));
		return result;
	}
	
	@Override
	public ServiceResult update(Feedback feedback) {
		ServiceResult result = new ServiceResult();

		if (!feedbackRepository.findById(feedback.getFeedbackID()).isPresent()) {
			result.setStatus(Status.FAILED);
			result.setMessage("Customer Not Found");
		} else {
			result.setData(feedbackRepository.save(feedback));
		}
		return result;
	}

	@Override
	public ServiceResult delete(int feedbackID) {
		ServiceResult result = new ServiceResult();

		Feedback feedback = feedbackRepository.findById(feedbackID).orElse(null);
		if (feedback == null) {
			result.setStatus(Status.FAILED);
			result.setMessage("Customer Not Found");
		} else {
			feedbackRepository.delete(feedback);
			result.setMessage("success");
		}
		return result;
	}
	
//	@Override
//	public List<Object[]> getAll() {
//		return feedbackRepository.getAll();
//	}

	@Override
	public List<Feedback> getAllFeedback() {
		return (List<Feedback>) feedbackRepository.findAll();
	}

	@Override
	public void saveFeedback(Feedback feedback) {
		feedbackRepository.save(feedback);
	}
	
	@Override
	public void deleteFeedback(Integer FeedbackID) {
		feedbackRepository.deleteById(FeedbackID);
	}

	@Override
	public Optional<Feedback> findFeedbackByFeedbackID(Integer feedbackID) {
		return feedbackRepository.findById(feedbackID);
	}
	
	@Override
	public Feedback findOne(Integer feedbackID) {
		return feedbackRepository.findOneByFeedbackID(feedbackID);
	}

	@Override
	public Page<Feedback> findPaginated2(int pageNo2, int pageSize) {

		PageRequest pageable2 = PageRequest.of(pageNo2 - 1, pageSize);
		@SuppressWarnings("unused")
		Feedback feedback = null;
		boolean isDeleted = false;
		return this.feedbackRepository.findAllByIsDeleted(isDeleted, pageable2);
	}

//	@Override
//	public List<Feedback> listAll(String keyword) {
//		return feedbackRepository.search(keyword);
//	}

	@Override
	public Feedback save(Feedback feedback) {
		return feedbackRepository.save(feedback);
		
	}

}