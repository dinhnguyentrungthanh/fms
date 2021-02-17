package vn.com.r2s.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.com.r2s.fms.entity.Question;

public interface QuestionService {
    
    List<Question> findAll();

	Question getAllQuestion();

	Question saveQuestion(Question question);
	
	Question updateQuestion(Question question);

	void deleteQuestion(Integer questionID);

	Optional<Question> findQuestionById(Integer questionID);
	
	Question findOne(Integer questionID);

	Page<Question> findPaginated(int pageNo, int pageSize);

	List<Question> listAll(String keyword);

	List<Question> findOneTopicID(Integer topicID);

}
