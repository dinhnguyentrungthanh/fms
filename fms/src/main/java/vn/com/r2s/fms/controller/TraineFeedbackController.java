package vn.com.r2s.fms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.com.r2s.fms.entity.Module;
import vn.com.r2s.fms.entity.Question;
import vn.com.r2s.fms.entity.Topic;
import vn.com.r2s.fms.entity.Trainee;
import vn.com.r2s.fms.entity.Answer;
import vn.com.r2s.fms.entity.AnswerID;
import vn.com.r2s.fms.entity.Class;
import vn.com.r2s.fms.entity.Feedback;
import vn.com.r2s.fms.entity.FeedbackQuestion;
import vn.com.r2s.fms.entity.FeedbackQuestionID;
import vn.com.r2s.fms.entity.TraineeComment;
import vn.com.r2s.fms.entity.TypeFeedback;
import vn.com.r2s.fms.repository.AnswerIDRepository;
import vn.com.r2s.fms.service.AnswerService;
import vn.com.r2s.fms.service.AssignmentService;
import vn.com.r2s.fms.service.ClassService;
import vn.com.r2s.fms.service.EnrollmentService;
import vn.com.r2s.fms.service.FeedbackService;
import vn.com.r2s.fms.service.ModuleService;
import vn.com.r2s.fms.service.QuestionService;
import vn.com.r2s.fms.service.TopicService;
import vn.com.r2s.fms.service.TraineeCommentService;
import vn.com.r2s.fms.service.TraineeService;
import vn.com.r2s.fms.service.TypeFeedbackService;
import vn.com.r2s.fms.service.FeedbackQuesionService;

@Controller
public class TraineFeedbackController {

	@Autowired
	TraineeCommentService traineeCommentService;
	
	@Autowired
	TraineeService traineeService;
	
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	ClassService classService;
	
	@Autowired
	TopicService topicService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	AssignmentService assignmentService;
	
	@Autowired
	EnrollmentService enrollmentService;
	
	@Autowired
	TypeFeedbackService typefeedbackService;
	
	@Autowired
	FeedbackQuesionService feedbackQuesionService;
	
	@Autowired
	AnswerIDRepository answerIDRepository;
	
	@RequestMapping(value = "/traineefb")
	public String traineeFeedback(Module module, @RequestParam("id") Integer FeedbackID, Model model, HttpServletRequest request) {
		List<Question> question = questionService.findAll();
		List<Topic> topic = topicService.findAll();
		List<TraineeComment> traineeComment = traineeCommentService.findAll();
		List<Class> classes = classService.findAll();
		List<FeedbackQuestion> feedbackQuestion = feedbackQuesionService.getAllFeedbackQuestion();
		List<Object[]> modules = moduleService.getAll();
		
		model.addAttribute("questions", question);
		model.addAttribute("topics", topic);
		model.addAttribute("traineeComments", traineeComment);
		model.addAttribute("modules", modules);
		model.addAttribute("modules", module);
		model.addAttribute("classes", classes);
		model.addAttribute("feedbackQuestions", feedbackQuestion);
		
		List<TypeFeedback> typeFeedback = typefeedbackService.getAllTypeFeedback();

		model.addAttribute("typeFeedbacks", typeFeedback);
		model.addAttribute("question", question);

		Optional<Feedback> feedbackEdit = feedbackService.findFeedbackByFeedbackID(FeedbackID);
		feedbackEdit.ifPresent(Feedback -> model.addAttribute("feedback", Feedback));
		
		return "feedbacks/trainee-feedback";
	}
	
	@RequestMapping(value = "/submit_FB", method = RequestMethod.POST)
	public String submit_FB(Feedback feedback, Model model, ModelMap modelMap, HttpServletRequest request,
			Answer answer) {
		List<Answer> aswers = answerService.findAll();
		List<Question> question = questionService.findAll();
		List<Topic> topic = topicService.findAll();
		List<TypeFeedback> typefeedback = typefeedbackService.findAll();
		modelMap.addAttribute("feedback", feedback);
		modelMap.addAttribute("questions", question);
		modelMap.addAttribute("topics", topic);
		modelMap.addAttribute("typeFeedbacks", typefeedbackService.findAll());
		modelMap.addAttribute("typefeedbacks", typefeedback);

		
		for (Answer aswer2 : aswers) {

			try {
				
				int newValue = Integer.parseInt(request.getParameter("traloi" + aswer2.getValue()));
				int newclassID = Integer.parseInt(request.getParameter("classID"));
				int newmoduleID = Integer.parseInt(request.getParameter("moduleID"));
				int newQuestionID = Integer.parseInt(request.getParameter("questionID"));
				String newTraineeID = request.getParameter("traineeID");
				

				AnswerID newAnswerID = new AnswerID();
				newAnswerID.setClassID(newclassID);
				newAnswerID.setModuleID(newmoduleID);
				newAnswerID.setQuestionID(newQuestionID);
				newAnswerID.setTraineeID(newTraineeID);

				answer.setId(newAnswerID);
				answer.setValue(newValue);
				answerService.saveAS(answer);

			} catch (Exception e) {
				
			}
		}
	return"redirect:/trainee/dashboard?message=successok";

	}
	
}

