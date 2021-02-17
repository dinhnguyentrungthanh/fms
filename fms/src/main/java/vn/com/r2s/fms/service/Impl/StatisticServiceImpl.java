package vn.com.r2s.fms.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.repository.StatisticRepository;
import vn.com.r2s.fms.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService{
	
	@Autowired
	private  StatisticRepository statisticRepository;
	
	/* detail */
	
		/* question */
	
	@Override
	public List<Object[]> findAllByPercentage(String Percentage) {
		return statisticRepository.findAllByPercentage(Percentage);
	}
	
		/* topic */

	@Override
	public List<Object[]> findAllQandOneTByPercentage(Integer topic) {
		return statisticRepository.findAllQandOneTByPercentage(topic);
	}

	public List<Object[]> findOneQandTByPercentageA(Integer questionID) {
		return statisticRepository.findOneQandTByPercentageA(questionID);
	}

	/* overview */
	@Override
	public List<Object[]> findAllQuestionByAnswer(Integer counts) {
		return statisticRepository.findAllQuestionByAnswer(counts);
	}

	@Override
	public List<Object[]> findTopic1ByAnswer(Integer counts) {
		return statisticRepository.findTopic1ByAnswer(counts);
	}
	/* overview/ */
	
	@Override
	public List<Object[]> getStatisticDetailAllByClassAndModule(Integer classID, Integer moduleID) {
		return statisticRepository.getStatisticDetailAllByClassAndModule(classID, moduleID);
	}

	@Override
	public List<Object[]> getStatisticDetailTopicByClassAndModule(Integer topicID, Integer classID, Integer moduleID) {
		return statisticRepository.getStatisticDetailTopicByClassAndModule(topicID, classID, moduleID);
	}

	@Override
	public List<Object[]> getStatisticDetailQByClassAndModule(Integer questionID, Integer classID, Integer moduleID) {
		return statisticRepository.getStatisticDetailQByClassAndModule(questionID ,classID, moduleID);
	}

	@Override
	public List<Object[]> getStatisticOverviewAllByClassAndModule(Integer classID, Integer moduleID) {
		return statisticRepository.getStatisticOverviewAllByClassAndModule(classID, moduleID);
	}

	@Override
	public List<Object[]> getStatisticOverviewTopicByClassAndModule(Integer topicID, Integer classID,
			Integer moduleID) {
		return statisticRepository.getStatisticOverviewTopicByClassAndModule(topicID, classID, moduleID);
	}

	// API TOPIC AND QUESTION
	
	@Override
	public List<Object[]> getQuestion() {
		return statisticRepository.getAll();
	}

	
}
