package vn.com.r2s.fms.api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.com.r2s.fms.entity.Topic;
import vn.com.r2s.fms.service.TopicService;

@RestController
public class TopicAPI {

	@Autowired
	private TopicService topicService;
	
	@GetMapping(value = "/api/topic/list")
	public List<Topic> findAll(){
		return topicService.findAll();
	}
	
	@GetMapping(value="/api/topic/list/search/{topicName}")
	public List<Topic> findAllTopicName(@PathVariable("topicName") String topicName){
		return topicService.findAllByTopicName(topicName);
	}
	@GetMapping(value="/api/topic/list/topicID/{topicID}")
	public Topic findOne(@PathVariable("topicID") Integer topicID){
		return  topicService.findOne(topicID);
	}
}
