package vn.com.r2s.fms.service;

import java.util.List;

import vn.com.r2s.fms.entity.Topic;

public interface TopicService {
	public List<Topic> findAll();
	
	public List<Topic> findAllByTopicName(String topicName);
	
	public Integer getTotalPagesByTopicName(String topicName, Integer page, Integer limit); 

	public List<Topic> findAllByTopicName(String topicName, Integer page, Integer limit);
	
	public Topic findOne(Integer topicID);
	
	public Topic findOne(String topicName);
	
}

