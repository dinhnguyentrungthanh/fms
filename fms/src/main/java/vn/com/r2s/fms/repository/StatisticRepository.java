package vn.com.r2s.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.Answer;

@Repository
public interface StatisticRepository extends JpaRepository<Answer, String>{
	
	/* detail */
	
	@Query(value = "SELECT concat(FORMAT(((COUNT(Value) * 100) / NewPeople.iCount),2),'%') AS Percentage "
			+ "FROM answer, (SELECT COUNT(Value) AS iCount FROM answer) as NewPeople "
			+ "group by Value order by Value", nativeQuery = true)
	public List<Object[]> findAllByPercentage(String Percentage);
	
	@Query(value = "select concat(FORMAT(((COUNT(a.Value) * 100) / S.iCount),2),'%') AS Percentage, a.Value, a.ClassID, q.QuestionID, a.ModuleID, q.QuestionContent " 
			+ " from (SELECT COUNT(Value) AS iCount FROM answer, topic, question where answer.QuestionID = question.QuestionID and question.TopicID = topic.TopicID and question.QuestionID = ?1 ) as S, question q " 
			+ " join topic t on t.TopicID = q.TopicID " 
			+ " join answer a on a.QuestionID = q.QuestionID " 
			+ " where q.QuestionID = ?1 " 
			+ " group by a.Value " 	
			+ " order by a.Value", nativeQuery = true)
	public List<Object[]> findOneQandTByPercentageA( Integer questionID);
	
	@Query(value = "select concat(FORMAT(((COUNT(a.Value) * 100) / S.iCount),2),'%') AS Percentage " 
			+ " from (SELECT COUNT(Value) AS iCount FROM answer, topic, question where answer.QuestionID = question.QuestionID and question.TopicID = topic.TopicID and question.TopicID = ?1) as S, question q " 
			+ " join topic t on t.TopicID = q.TopicID " 
			+ " join answer a on a.QuestionID = q.QuestionID " 
			+ " where q.TopicID = ?1 " 
			+ " group by a.Value " 
			+ " order by a.Value", nativeQuery = true)
	public List<Object[]> findAllQandOneTByPercentage(Integer topic);

	/* overview */
	
	@Query(value = "SELECT COUNT(Value) as counts FROM answer group by Value order by Value", nativeQuery = true)
	public List<Object[]> findAllQuestionByAnswer(Integer counts);
	
	@Query(value = "select COUNT(Value) as counts "  
			+ "from question q "  
			+ "join topic t on t.TopicID = q.TopicID "  
			+ "join answer a on a.QuestionID = q.QuestionID "  
			+ "where q.TopicID = ?1 "  
			+ "group by a.Value order by a.Value;", nativeQuery = true)
	public List<Object[]> findTopic1ByAnswer(Integer counts);

//	find
	
	@Query(value = "select concat(FORMAT(((COUNT(a.Value) * 100) / S.iCount),2),'%') AS Percentage, a.ClassID, a.ModuleID " 
			+ " from (SELECT COUNT(Value) AS iCount FROM answer, topic, question where answer.QuestionID = question.QuestionID and question.TopicID = topic.TopicID and answer.ClassID = ?1 and answer.ModuleID = ?2) as S, question q " 
			+ " join topic t on t.TopicID = q.TopicID " 
			+ " join answer a on a.QuestionID = q.QuestionID " 
			+ " where a.ClassID = ?1 and a.ModuleID = ?2 " 
			+ " group by a.Value " 	
			+ " order by a.Value", nativeQuery = true)
	public List<Object[]> getStatisticDetailAllByClassAndModule(Integer classID, Integer moduleID);

	@Query(value = "select concat(FORMAT(((COUNT(a.Value) * 100) / S.iCount),2),'%') AS Percentage, a.ClassID, a.ModuleID " 
			+ " from (SELECT COUNT(Value) AS iCount FROM answer, topic, question where answer.QuestionID = question.QuestionID and question.TopicID = topic.TopicID and question.TopicID = ?1 and answer.ClassID = ?2 and answer.ModuleID = ?3) as S, question q " 
			+ " join topic t on t.TopicID = q.TopicID " 
			+ " join answer a on a.QuestionID = q.QuestionID " 
			+ " where q.TopicID = ?1 and a.ClassID = ?2 and a.ModuleID = ?3 " 
			+ " group by a.Value " 	
			+ " order by a.Value", nativeQuery = true)
	public List<Object[]> getStatisticDetailTopicByClassAndModule(Integer topicID, Integer classID, Integer moduleID);
	
	@Query(value = "select concat(FORMAT(((COUNT(a.Value) * 100) / S.iCount),2),'%') AS Percentage, q.QuestionID, a.ClassID, a.ModuleID " 
			+ " from (SELECT COUNT(Value) AS iCount FROM answer, topic, question where answer.QuestionID = question.QuestionID and question.TopicID = topic.TopicID and question.QuestionID = ?1 and answer.ClassID = ?2 and answer.ModuleID = ?3) as S, question q " 
			+ " join topic t on t.TopicID = q.TopicID " 
			+ " join answer a on a.QuestionID = q.QuestionID " 
			+ " where q.QuestionID = ?1 and a.ClassID = ?2 and a.ModuleID = ?3 " 
			+ " group by a.Value " 	
			+ " order by a.Value", nativeQuery = true)
	public List<Object[]> getStatisticDetailQByClassAndModule(Integer questionID, Integer classID, Integer moduleID);

//	overview
	
	@Query(value = "SELECT COUNT(Value) as counts FROM answer where ClassID = ?1 and ModuleID = ?2 group by Value order by Value", nativeQuery = true)
	public List<Object[]> getStatisticOverviewAllByClassAndModule(Integer classID, Integer moduleID);
	
	@Query(value = "select COUNT(Value) as counts, t.TopicName "  
			+ "from question q "  
			+ "join topic t on t.TopicID = q.TopicID "  
			+ "join answer a on a.QuestionID = q.QuestionID "  
			+ "where q.TopicID = ?1 and a.ClassID = ?2 and a.ModuleID = ?3 "  
			+ "group by a.Value order by a.Value;", nativeQuery = true)
	public List<Object[]> getStatisticOverviewTopicByClassAndModule(Integer topicID, Integer classID, Integer moduleID);

	@Query(value = "SELECT q.QuestionContent, t.TopicName, q.QuestionID FROM question q join topic t on t.TopicID = q.TopicID", nativeQuery = true)
	public List<Object[]> getAll();
	
}
