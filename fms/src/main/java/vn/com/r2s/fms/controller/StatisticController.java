package vn.com.r2s.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.com.r2s.fms.entity.Class;
import vn.com.r2s.fms.entity.Feedback;
import vn.com.r2s.fms.entity.Module;
import vn.com.r2s.fms.entity.Question;
import vn.com.r2s.fms.entity.Topic;
import vn.com.r2s.fms.service.ClassService;
import vn.com.r2s.fms.service.FeedbackQuesionService;
import vn.com.r2s.fms.service.FeedbackService;
import vn.com.r2s.fms.service.ModuleService;
import vn.com.r2s.fms.service.QuestionService;
import vn.com.r2s.fms.service.StatisticService;
import vn.com.r2s.fms.service.TopicService;

@Controller
public class StatisticController {
	@Autowired
	ClassService classService;
	
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	TopicService topicService;
	
	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	FeedbackQuesionService feedbackQuesionService;
	
	@Autowired
	StatisticService statisticService;
	
	@RequestMapping("/admin/result")
	public String resurt(Model model, String Percentage) {
		List<Class> classs = classService.findAll();
		List<Module> modules = moduleService.findAll();
		List<Question> questions = questionService.findAll();
		List<Topic> topics = topicService.findAll();
		List<Feedback> feedbacks = feedbackService.findAll();
		List<Object[]> percentages = statisticService.findAllByPercentage(Percentage);

		model.addAttribute("classs", classs);
		model.addAttribute("modules", modules);
		model.addAttribute("questions", questions);
		model.addAttribute("topics", topics);
		model.addAttribute("feedbacks", feedbacks);
		model.addAttribute("percentages", percentages);
		return "statictis/result";
	}
	
	//trainer
	
	@RequestMapping("/trainer/result")
	public String resurtTrainer(Model model, String Percentage) {
		List<Class> classs = classService.findAll();
		List<Module> modules = moduleService.findAll();
		List<Question> questions = questionService.findAll();
		List<Topic> topics = topicService.findAll();
		List<Feedback> feedbacks = feedbackService.findAll();
		List<Object[]> percentages = statisticService.findAllByPercentage(Percentage);

		model.addAttribute("classs", classs);
		model.addAttribute("modules", modules);
		model.addAttribute("questions", questions);
		model.addAttribute("topics", topics);
		model.addAttribute("feedbacks", feedbacks);
		model.addAttribute("percentages", percentages);
		return "statictis/trainer-result";
	}
	
}
