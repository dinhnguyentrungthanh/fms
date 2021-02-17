package vn.com.r2s.fms.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.com.r2s.fms.entity.Admin;
import vn.com.r2s.fms.entity.Feedback;
import vn.com.r2s.fms.entity.FeedbackQuestion;
import vn.com.r2s.fms.entity.FeedbackQuestionID;
import vn.com.r2s.fms.entity.Question;
import vn.com.r2s.fms.entity.Topic;
import vn.com.r2s.fms.entity.TypeFeedback;
import vn.com.r2s.fms.model.UserModel;
import vn.com.r2s.fms.repository.FeedbackQuestionIDRepository;
import vn.com.r2s.fms.repository.FeedbackRepository;
import vn.com.r2s.fms.repository.QuestionRepository;
import vn.com.r2s.fms.service.AdminService;
import vn.com.r2s.fms.service.FeedbackQuesionService;
import vn.com.r2s.fms.service.FeedbackService;
import vn.com.r2s.fms.service.QuestionService;
import vn.com.r2s.fms.service.TopicService;
import vn.com.r2s.fms.service.TraineeCommentService;
import vn.com.r2s.fms.service.TypeFeedbackService;

@Controller
public class FeedbackController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private TypeFeedbackService typefeedbackService;

	@Autowired
	QuestionService questionService;

	@Autowired
	FeedbackQuesionService feedbackQuesionService;

	@Autowired
	FeedbackQuestionIDRepository feedbackQuestionIDRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	TopicService topicService;

	@Autowired
	TraineeCommentService traineeCommentService;

	private Set<FeedbackQuestion> feedback_Question;

	@RequestMapping("admin/feedbacks")
	public String feedback(Model model, HttpServletRequest request) {
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}
		return findPaginated2(1, model);
	}

	@RequestMapping(value = "/add")
	public String addFeedback(Model model, HttpServletRequest request) {
		List<TypeFeedback> typeFeedback = typefeedbackService.findAll();
		List<Question> question = questionService.findAll();
		List<Topic> topic = topicService.findAll();
		model.addAttribute("typeFeedbacks", typeFeedback);
		model.addAttribute("questions", question);
		model.addAttribute("topics", topic);
		model.addAttribute("feedback", new Feedback());

		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}

		return "feedbacks/add-feedback";
	}

	@RequestMapping(value = "/review-add-feedback", method = RequestMethod.POST)
	public String reviewaddfeedback(@Valid Feedback feedback, BindingResult bindingResult, ModelMap modelMap) {

		if (bindingResult.hasErrors()) {
			List<Question> question = questionService.findAll();
			List<Topic> topic = topicService.findAll();
			modelMap.addAttribute("feedback", feedback);
			modelMap.addAttribute("questions", question);
			modelMap.addAttribute("topics", topic);
			modelMap.addAttribute("typeFeedbacks", typefeedbackService.findAll());
			return "feedbacks/add-feedback";

		} else {
			List<Topic> topic = topicService.findAll();
			modelMap.addAttribute("topics", topic);
			return "feedbacks/review-add-feedback";
		}
	}

	@RequestMapping(value = "/back-add-feedback")
	public String backaddfeedback(Model model, HttpServletRequest request) {
		List<TypeFeedback> typeFeedback = typefeedbackService.findAll();
		List<Question> question = questionService.findAll();
		List<Topic> topic = topicService.findAll();
		model.addAttribute("typeFeedbacks", typeFeedback);
		model.addAttribute("questions", question);
		model.addAttribute("topics", topic);
		model.addAttribute("feedback", new Feedback());
		return "feedbacks/add-feedback";
	}

	@RequestMapping(value = "/back-feedback")
	public String backfeedback(Model model) {
		return "feedbacks/list";
	}

	@RequestMapping(value = "/edit-fb")
	public String editfb(@RequestParam("id") Integer FeedbackID, Model model, HttpServletRequest request) {

		List<TypeFeedback> typeFeedback1 = typefeedbackService.findAll();
		List<Question> question1 = questionService.findAll();
		List<Topic> topic1 = topicService.findAll();
		model.addAttribute("typeFeedbacks", typeFeedback1);
		model.addAttribute("questions", question1);
		model.addAttribute("topics", topic1);

		List<Topic> topic = topicService.findAll();
		List<TypeFeedback> typeFeedback = typefeedbackService.getAllTypeFeedback();
		List<FeedbackQuestion> feedbackQuestion = feedbackQuesionService.getAllFeedbackQuestion();
		List<Question> question = questionService.findAll();

		model.addAttribute("topics", topic);
		model.addAttribute("typeFeedbacks", typeFeedback);
		model.addAttribute("feedbackQuestions", feedbackQuestion);
		model.addAttribute("question", question);

		Optional<Feedback> feedbackEdit = feedbackService.findFeedbackByFeedbackID(FeedbackID);
		feedbackEdit.ifPresent(Feedback -> model.addAttribute("feedback", Feedback));

		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}
		return "feedbacks/edit-feedback";
	}

	@RequestMapping(value = "/rv-add-fb")
	public String rvaddfb(Model model) {
		return "feedbacks/review-add-feedback";
	}

	@RequestMapping(value = "/rv-fb", method = RequestMethod.GET)
	public String rvfb(@RequestParam("id") Integer FeedbackID, Model model, HttpServletRequest request) {

		List<Topic> topic = topicService.findAll();
		List<TypeFeedback> typeFeedback = typefeedbackService.getAllTypeFeedback();
		List<FeedbackQuestion> feedbackQuestion = feedbackQuesionService.getAllFeedbackQuestion();
		List<Question> question = questionService.findAll();

		model.addAttribute("topics", topic);
		model.addAttribute("typeFeedbacks", typeFeedback);
		model.addAttribute("feedbackQuestions", feedbackQuestion);
		model.addAttribute("question", question);

		Optional<Feedback> feedbackEdit = feedbackService.findFeedbackByFeedbackID(FeedbackID);
		feedbackEdit.ifPresent(Feedback -> model.addAttribute("feedback", Feedback));

		return "feedbacks/review-feedback";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String editUser(@RequestParam("id") Integer FeedbackID, Model model) {
		Optional<Feedback> feedbackEdit = feedbackService.findFeedbackByFeedbackID(FeedbackID);
		feedbackEdit.ifPresent(Feedback -> model.addAttribute("feedback", Feedback));
		return "feedbacks/edit-feedback";
	}

	@RequestMapping(value = "saveFeedback", method = RequestMethod.POST)
	public String saveFeedback(@Valid Feedback feedback, BindingResult bindingResult, RedirectAttributes model,
			ModelMap modelMap, HttpServletRequest request, HttpSession httpSession) {

		UserModel user = (UserModel) httpSession.getAttribute("USER");
		if (bindingResult.hasErrors()) {
			List<Question> question = questionService.findAll();
			List<Topic> topic = topicService.findAll();
			List<TypeFeedback> typefeedback = typefeedbackService.findAll();
			modelMap.addAttribute("feedback", feedback);
			modelMap.addAttribute("questions", question);
			modelMap.addAttribute("topics", topic);
			modelMap.addAttribute("typeFeedbacks", typefeedbackService.findAll());
			modelMap.addAttribute("typefeedbacks", typefeedback);
			return "feedbacks/add-feedback";
		} else {
			List<Topic> topic = topicService.findAll();
			modelMap.addAttribute("topics", topic);
			Admin admin = adminService.findOneByUsername(user.getUsername());
			feedback.setAdmins(admin);

			int typeFeedback = Integer.parseInt(request.getParameter("typeFeedback"));
			feedback.setTypeFeedback(typefeedbackService.getOne(typeFeedback));
			feedback.setDeleted(false);
			// feedback.setFeedback_Question(feedback_Question);
			feedbackService.saveFeedback(feedback);
			return "feedbacks/review-add-feedback";
		}
	}
	
	@RequestMapping(value = "saveUpdate", method = RequestMethod.POST)
	public String saveUpdate(@Valid Feedback feedback, BindingResult bindingResult, RedirectAttributes model,
			HttpSession httpSession, ModelMap modelMap, HttpServletRequest request) {
		UserModel user = (UserModel) httpSession.getAttribute("USER");
		if (bindingResult.hasErrors()) {
			List<Question> question = questionService.findAll();
			List<Topic> topic = topicService.findAll();
			List<TypeFeedback> typefeedback = typefeedbackService.findAll();
			List<FeedbackQuestion> feedbackQuestions = feedbackQuesionService.findAllFbqs();
			modelMap.addAttribute("feedback", feedback);
			modelMap.addAttribute("questions", question);
			modelMap.addAttribute("topics", topic);
			modelMap.addAttribute("typeFeedbacks", typefeedbackService.findAll());
			modelMap.addAttribute("typefeedbacks", typefeedback);
			modelMap.addAttribute("feedbackQuestions", feedbackQuestions);
			return "feedbacks/edit-feedback";
		} else {
			List<Topic> topic = topicService.findAll();
			modelMap.addAttribute("topics", topic);
			Admin admin = adminService.findOneByUsername(user.getUsername());
			feedback.setAdmins(admin);
			feedback.setFeedback_Question(feedback_Question);
			feedback.setDeleted(false);
			feedbackRepository.save(feedback);
			// feedbackService.saveFeedback(feedback);
			return "feedbacks/review-edit-feedback";
		}
	}

	@RequestMapping(value = "/editFb_Question")
	public String editEnrollment(@RequestParam("feedbackID") Integer feedbackID,
			@RequestParam("questionID") Integer questionID, Model model, HttpServletRequest request) {

		model.addAttribute("questions", questionRepository.findAll());
		Optional<FeedbackQuestion> feedbackQuestionSee = feedbackQuesionService
				.findFeedbackQuesionByFeedbackID(feedbackID, questionID);
		feedbackQuestionSee.ifPresent(feedbackQuestion -> model.addAttribute("feedbackQuestion", feedbackQuestion));

		String message = request.getParameter("message");
		String message1 = request.getParameter("message1");
		if (message1 != null) {
			request.setAttribute("message1", message1);
			request.setAttribute("successfail", true);
		}
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}
		return "feedbacks/review-add-feedback";
	}

	@RequestMapping(value = "/saveFb_Question", method = RequestMethod.POST)
	public String saveFb_Question(Feedback feedback, Model model, ModelMap modelMap, HttpServletRequest request,
			FeedbackQuestion feedbackQuestion) {
		List<Question> question = questionService.findAll();
		List<Topic> topic = topicService.findAll();
		List<TypeFeedback> typefeedback = typefeedbackService.findAll();
		modelMap.addAttribute("feedback", feedback);
		modelMap.addAttribute("questions", question);
		modelMap.addAttribute("topics", topic);
		modelMap.addAttribute("typeFeedbacks", typefeedbackService.findAll());
		modelMap.addAttribute("typefeedbacks", typefeedback);

		for (Question question2 : question) {

			try {
				
				int newQuestionID = Integer.parseInt(request.getParameter("newQuestionID" + question2.getQuestionID()));
				int newfeedbackID = Integer.parseInt(request.getParameter("feedbackID"));

				FeedbackQuestionID newFeedbackQuestionID = new FeedbackQuestionID();
				newFeedbackQuestionID.setFeedbackID(newfeedbackID);
				newFeedbackQuestionID.setQuestionID(newQuestionID);

				feedbackQuestion.setId(newFeedbackQuestionID);
				feedbackQuestion.setQuestionID(questionRepository.getOne(newQuestionID));
				feedbackQuesionService.saveFQ(feedbackQuestion);

			} catch (Exception e) {
				
			}

		}
	return"redirect:admin/feedbacks";

	}
	
	@RequestMapping(value = "/saveUFb_Question", method = RequestMethod.POST)
	public String saveUFb_Question(Feedback feedback, FeedbackQuestion feedbackID, Model model, ModelMap modelMap, HttpServletRequest request,
			FeedbackQuestion feedbackQuestion) {
		List<FeedbackQuestion> feedbackQuestions = feedbackQuesionService.findAllFbqs();
		List<Question> question = questionService.findAll();
		List<Topic> topic = topicService.findAll();
		List<TypeFeedback> typefeedback = typefeedbackService.findAll();
		modelMap.addAttribute("feedback", feedback);
		modelMap.addAttribute("questions", question);
		modelMap.addAttribute("topics", topic);
		modelMap.addAttribute("typeFeedbacks", typefeedbackService.findAll());
		modelMap.addAttribute("typefeedbacks", typefeedback);
		modelMap.addAttribute("feedbackQuestions", feedbackQuestions);
		
		feedbackQuesionService.deleteFQ(feedbackID);
		for (Question question2 : question) {

			try {
				
				int newQuestionID = Integer.parseInt(request.getParameter("newQuestionID" + question2.getQuestionID()));
				int newfeedbackID = Integer.parseInt(request.getParameter("feedbackID"));

				FeedbackQuestionID newFeedbackQuestionID = new FeedbackQuestionID();
				newFeedbackQuestionID.setFeedbackID(newfeedbackID);
				newFeedbackQuestionID.setQuestionID(newQuestionID);

				feedbackQuestion.setId(newFeedbackQuestionID);
				feedbackQuestion.setQuestionID(questionRepository.getOne(newQuestionID));
				feedbackQuesionService.saveFQ(feedbackQuestion);

			} catch (Exception e) {
				
			}

		}
	return"redirect:admin/feedbacks";

	}

	@RequestMapping(value = "/delete")
	public String deleteFeedback(@RequestParam("id") Integer feedbackID, RedirectAttributes model,
			HttpServletRequest request) {
		Feedback feedbackeditFeedback = feedbackService.findOne(feedbackID);
		feedbackeditFeedback.setDeleted(true);
		feedbackService.saveFeedback(feedbackeditFeedback);
		//feedbackService.deleteFeedback(FeedbackID);
		return "redirect:admin/feedbacks?message=success";
	}
	
	@RequestMapping(value = "/delete2")
	public String deleteFeedback2(@RequestParam("id") Integer feedbackID, RedirectAttributes model,
			HttpServletRequest request) {
		Feedback feedbackeditFeedback = feedbackService.findOne(feedbackID);
		feedbackeditFeedback.setDeleted(true);
		//feedbackService.saveFeedback(feedbackeditFeedback);
		feedbackService.deleteFeedback(feedbackID);
		return "redirect:admin/feedbacks";
	}

	@GetMapping("/page2/{pageNo2}")
	public String findPaginated2(@PathVariable(value = "pageNo2") int pageNo2, Model model) {
		int pageSize = 7;

		Page<Feedback> page2 = feedbackService.findPaginated2(pageNo2, pageSize);
		List<Feedback> feedbacks = page2.getContent();
		List<TypeFeedback> typeFeedbacks = typefeedbackService.findAll();
		List<Question> questions = questionService.findAll();
		List<FeedbackQuestion> feedbackQuestions = feedbackQuesionService.findAllFbqs();

		model.addAttribute("currentPage2", pageNo2);
		model.addAttribute("totalPages2", page2.getTotalPages());
		model.addAttribute("totalItems2", page2.getTotalElements());
		model.addAttribute("feedbacks", feedbacks);
		model.addAttribute("typeFeedbacks", typeFeedbacks);
		model.addAttribute("questions", questions);
		model.addAttribute("feedbackQuestions", feedbackQuestions);
		return "feedbacks/list";
	}

}