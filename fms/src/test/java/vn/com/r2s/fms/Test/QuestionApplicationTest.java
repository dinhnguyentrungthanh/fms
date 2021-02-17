package vn.com.r2s.fms.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import vn.com.r2s.fms.entity.Question;
import vn.com.r2s.fms.entity.Topic;
import vn.com.r2s.fms.service.QuestionService;
import vn.com.r2s.fms.service.TopicService;

@SpringBootTest
public class QuestionApplicationTest {

	@Autowired QuestionService questionService;
	
	@Autowired TopicService topicService;
	
	@Test
	void questionLoads() {
		int pageNo = 1;
		int pageSize = 1;
		Page<Question> question = questionService.findPaginated(pageNo, pageSize);
		assertNotNull(question);
	}
	
	
	  @Test void questionInsert() {
	  Topic topic = topicService.findOne(1); Question
	  questiondata = new Question(); questiondata.setQuestionContent("a");
	  questiondata.setIsDeleted(false); questiondata.setTopic(topic);
	  questionService.saveQuestion(questiondata); 
	  assertNotNull(questiondata); }
	 
	
	  @Test 
	  void questionUpdate() { 		  
		
		 Optional<Question> optional = questionService.findQuestionById(6);
		 Topic topic = topicService.findOne(1);
		 if(!optional.isPresent()) {
			 Question questionupdate = new Question();
			 questionupdate.setQuestionID(6);
			 questionupdate.setIsDeleted(false);
			 questionupdate.setQuestionContent(optional.get().getQuestionContent());
			 questionupdate.setTopic(topic);
			 questionService.updateQuestion(questionupdate);
			 assertEquals(questionupdate, optional);
		 }
	  }
	  
}
