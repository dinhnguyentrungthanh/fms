package vn.com.r2s.fms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.r2s.fms.entity.Topic;

public interface TopicRepoisitory  extends JpaRepository<Topic, Integer> {
	
	public Topic findOneByTopicName(String topicName);
	
	public Topic findOneByTopicID(Integer topicID);
	
	public List<Topic> findAllByTopicName(String topicName);
	
	public Page<Topic> findAllByTopicName(String topicName, Pageable pageable);
}
