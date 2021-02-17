package vn.com.r2s.fms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import vn.com.r2s.fms.entity.Feedback;
import vn.com.r2s.fms.service.Impl.ServiceResult;

public interface FeedbackService {
	List<Feedback> getAllFeedback();

	void saveFeedback(Feedback feedback);

	void deleteFeedback(Integer FeedbackID);

	Optional<Feedback> findFeedbackByFeedbackID(Integer FeedbackID);

	Page<Feedback> findPaginated2(int pageNo2, int pageSize);

	//List<Feedback> listAll(String keyword);

	Feedback findOne(Integer feedbackID);

	Feedback save(Feedback feedback);

	ServiceResult findById(int feedbackID);

	ServiceResult create(Feedback feedback);

	ServiceResult delete(int feedbackID);

	ServiceResult update(Feedback feedback);

	List<Feedback> findAll();

	ServiceResult findAlll();
	
//	List<Object[]> getAll();
}

