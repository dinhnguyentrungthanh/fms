package vn.com.r2s.fms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.FeedbackQuestion;
import vn.com.r2s.fms.entity.FeedbackQuestionID;
@Repository
public interface FeedbackQuestionIDRepository extends JpaRepository<FeedbackQuestion,FeedbackQuestionID>{

	Optional<FeedbackQuestion> findById(int id);
	
}
