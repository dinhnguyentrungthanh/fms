package vn.com.r2s.fms.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.Topic;
import vn.com.r2s.fms.repository.TopicRepoisitory;
import vn.com.r2s.fms.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService{

	@Autowired
	private TopicRepoisitory topicRepoisitory;
	
	@Override
	public List<Topic> findAll() {
		return topicRepoisitory.findAll();
	}

	@Override
	public List<Topic> findAllByTopicName(String topicName) {
		return topicRepoisitory.findAllByTopicName(topicName);
	}

	@Override
	public Integer getTotalPagesByTopicName(String topicName, Integer page, Integer limit) {
		return topicRepoisitory.findAllByTopicName(topicName,PageRequest.of(page, limit)).getTotalPages();
	}

	@Override
	public List<Topic> findAllByTopicName(String topicName, Integer page, Integer limit) {
		return topicRepoisitory.findAllByTopicName(topicName,PageRequest.of(page, limit)).getContent();
	}

	@Override
	public Topic findOne(Integer topicID) {
		return topicRepoisitory.findOneByTopicID(topicID);
	}
	@Override
	public Topic findOne(String topicName) {
		return topicRepoisitory.findOneByTopicName(topicName);
	}	
}
