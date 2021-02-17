package vn.com.r2s.fms.service;

import java.util.List;

public interface StatisticService {
	/* detail */
		/* topic */

	List<Object[]> findAllQandOneTByPercentage(Integer topic);
	
		/* question */

	List<Object[]> findAllByPercentage(String Percentage);
	
	List<Object[]> findOneQandTByPercentageA(Integer questionID);
	
	/* overview */

	List<Object[]> findTopic1ByAnswer(Integer counts);

	List<Object[]> findAllQuestionByAnswer(Integer counts);

	List<Object[]> getStatisticDetailAllByClassAndModule(Integer classID, Integer moduleID);

	List<Object[]> getStatisticDetailTopicByClassAndModule(Integer topicID, Integer classID, Integer moduleID);

	List<Object[]> getStatisticDetailQByClassAndModule(Integer questionID, Integer classID, Integer moduleID);

	List<Object[]> getStatisticOverviewAllByClassAndModule(Integer classID, Integer moduleID);

	List<Object[]> getStatisticOverviewTopicByClassAndModule(Integer topicID, Integer classID, Integer moduleID);

	List<Object[]> getQuestion();
	
}
