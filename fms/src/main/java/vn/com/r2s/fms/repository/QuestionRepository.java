package vn.com.r2s.fms.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.Question;
import vn.com.r2s.fms.entity.Topic;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	public Question findOneByQuestionID(Integer questionID);
	
	  public List<Question> findAllByIsDeleted(Boolean isDeleted);
	  
	  public Page<Question> findAllByIsDeleted(Boolean isDeleted, Pageable
	  pageable);
	  
	  public List<Question> findAllByTopic(Topic topic);
	  
	  public Page<Question> findAllByTopic(Topic topic, Pageable
	  pageable);
	  
	  public List<Question> findAllByIsDeletedAndTopic(Boolean isDeleted,Topic topic);
	  
	  public List<Question> findAllByIsDeletedAndTopic(Boolean isDeleted,Topic topic, Pageable pageable);

	  @Query(value = "SELECT * FROM question q left join topic t  on q.TopicID=t.TopicID where concat(t.TopicName,'') LIKE %?1%", nativeQuery = true)
		 public List<Question> search(String pageable);

}
