package vn.com.r2s.fms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.r2s.fms.service.StatisticService;

@RestController
public class StatisticAPI {
	@Autowired
	private StatisticService statisticService;

	@GetMapping(value = "/api/statistic/statisticDetail")
	public List<Object[]> getStatisticDetailAllByClassAndModule(@RequestParam("classID") Integer classID, @RequestParam("moduleID") Integer moduleID) {
		return statisticService.getStatisticDetailAllByClassAndModule(classID, moduleID);
	}
	
	@GetMapping(value = "/api/statistic/statisticDetail1")
	public List<Object[]> getStatisticDetailTopicByClassAndModule(@RequestParam("topicID") Integer topicID, @RequestParam("classID") Integer classID, @RequestParam("moduleID") Integer moduleID) {
		return statisticService.getStatisticDetailTopicByClassAndModule(topicID, classID, moduleID);
	}
	
	@GetMapping(value = "/api/statistic/statisticDetail2")
	public List<Object[]> getStatisticDetailwQByClassAndModule(@RequestParam("questionID") Integer questionID, @RequestParam("classID") Integer classID, @RequestParam("moduleID") Integer moduleID) {
		return statisticService.getStatisticDetailQByClassAndModule(questionID ,classID, moduleID);
	}
	
//	overview
	
	@GetMapping(value = "/api/statistic/statisticOverview")
	public List<Object[]> getStatisticOverviewAllByClassAndModule(@RequestParam("classID") Integer classID, @RequestParam("moduleID") Integer moduleID) {
		return statisticService.getStatisticOverviewAllByClassAndModule(classID, moduleID);
	}
	
	@GetMapping(value = "/api/statistic/statisticOverview1")
	public List<Object[]> getStatisticOverviewTopicByClassAndModule(@RequestParam("topicID") Integer topicID, @RequestParam("classID") Integer classID, @RequestParam("moduleID") Integer moduleID) {
		return statisticService.getStatisticOverviewTopicByClassAndModule(topicID, classID, moduleID);
	}
	
// API TOPIC AND QUESTION
	@GetMapping(value = "/api/statistic/question")
	public List<Object[]> getQuestion() {
		return statisticService.getQuestion();
	}
	
}
