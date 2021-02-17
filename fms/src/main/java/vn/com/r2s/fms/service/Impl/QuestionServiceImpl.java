package vn.com.r2s.fms.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.Question;
import vn.com.r2s.fms.entity.Topic;
import vn.com.r2s.fms.repository.QuestionRepository;
import vn.com.r2s.fms.repository.TopicRepoisitory;
import vn.com.r2s.fms.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired TopicRepoisitory topicRepoisitory;

	@Override
	public Question getAllQuestion() {
			return (Question) questionRepository.findAll();

	}

	@Override
	public Question saveQuestion(Question question) {
		
	return	questionRepository.save(question);
		
	}

	@Override
	public void deleteQuestion(Integer questionID) {
		questionRepository.deleteById(questionID);
		
	}

	@Override
	public Optional<Question> findQuestionById(Integer questionID) {
		return questionRepository.findById(questionID);
	}

	@Override
	public Page<Question> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		@SuppressWarnings("unused")
		Question question = null;
		boolean isDeleted = false;
		return this.questionRepository.findAllByIsDeleted(isDeleted, pageable);

	}
	
	@Override
	public List<Question> findAll() {
		return questionRepository.findAll();
	}
	

	@Override
	public List<Question> listAll(String keyword) {
		return null;
	}

	@Override
	public Question updateQuestion(Question question) {
		return question;	
		
	}

	@Override
	public Question findOne(Integer questionID) {
		return questionRepository.findOneByQuestionID(questionID);
	}

	@SuppressWarnings("unused")
	@Override
	public List<Question> findOneTopicID(Integer topicID) {
		Topic topic = topicRepoisitory.findOneByTopicID(topicID);
		Question question = null;
		boolean isDeleted = false;
		return this.questionRepository.findAllByIsDeletedAndTopic(isDeleted,topic);
	}
}
