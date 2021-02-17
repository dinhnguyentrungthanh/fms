package vn.com.r2s.fms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.r2s.fms.entity.Question;
import vn.com.r2s.fms.service.QuestionService;

@RestController
public class QuestionAPI {

	@Autowired
	private QuestionService questionService;

	@GetMapping(value = "/api/question/list/search")
	public List<Question> findTopicNamePaginated(@RequestParam(value ="topicID", required = false) Integer topicID){
		return questionService.findOneTopicID(topicID);
	}
}
