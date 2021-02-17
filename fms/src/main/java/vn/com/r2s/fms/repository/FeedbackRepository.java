package vn.com.r2s.fms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.r2s.fms.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

	 public Feedback findOneByFeedbackID(Integer feedbackID);
	
	  public List<Feedback> findAllByIsDeleted(Boolean isDeleted);
	  
	  public Page<Feedback> findAllByIsDeleted(Boolean isDeleted, Pageable pageable);
}

