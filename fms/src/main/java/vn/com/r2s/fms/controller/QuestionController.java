package vn.com.r2s.fms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.com.r2s.fms.entity.Question;
import vn.com.r2s.fms.entity.Topic;
import vn.com.r2s.fms.repository.QuestionRepository;
import vn.com.r2s.fms.service.FeedbackQuesionService;
import vn.com.r2s.fms.service.QuestionService;
import vn.com.r2s.fms.service.TopicService;

@Controller
@RequestMapping("/admin/")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	TopicService topicService;
	
	@Autowired
	FeedbackQuesionService feedbackQuesionService;

	@RequestMapping("/question")
	public String index(Model model, HttpServletRequest request) {

		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}
		return findPaginated(1, model);
	}

	@RequestMapping(value = "addQuestion")
	public String addQuestion(Model model, HttpServletRequest request) {
		List<Topic> topic = topicService.findAll();
		model.addAttribute("topics", topic);
		model.addAttribute("question", new Question());
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}
		return "question/add";
	}

	@RequestMapping(value = "saveQuestion", method = RequestMethod.POST)
	public String save(@Valid Question question, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<Topic> topic = topicService.findAll();
			model.addAttribute("topics", topic);
			return "question/add";
		} else {
			// question.setQuestionFeedback(questionFeedback);
			question.setIsDeleted(false);
			questionService.saveQuestion(question);
			return "redirect:/admin/addQuestion?message=success";
		}
	}

	@RequestMapping(value = "/editQuestion", method = RequestMethod.GET)
	public String editQuestion(@RequestParam("questionID") Integer questionID, Model model,
			HttpServletRequest request) {
		List<Topic> topic = topicService.findAll();
		model.addAttribute("topics", topic);
		Optional<Question> quesitonedit = questionService.findQuestionById(questionID);
		quesitonedit.ifPresent(question -> model.addAttribute("question", question));
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("successok", true);
			request.setAttribute("message", message);
		}
		return "question/edit";
	}

	@RequestMapping(value = "updateQuestion", method = RequestMethod.POST)
	public String update(@Valid Question question, BindingResult bindingResult, RedirectAttributes model) {

		if (bindingResult.hasErrors()) {
			return "question/edit";
		} else {
			question.setIsDeleted(false);
			questionRepository.save(question);
			return "redirect:/admin/editQuestion?questionID=" + question.getQuestionID() + "&message=success";

		}

	}

	@RequestMapping(value = "deleteQuestion")
	public String delete(@RequestParam("questionID") Integer questionID, RedirectAttributes model, HttpServletRequest request) {
		Question quesitonedit = questionService.findOne(questionID);		
		quesitonedit.setIsDeleted(true);
		questionService.saveQuestion(quesitonedit);
		return "redirect:/admin/question?message=success";	
	}

	@GetMapping("/question/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 3;

		Page<Question> page = questionService.findPaginated(pageNo, pageSize);
		List<Question> questions = page.getContent();
		List<Topic> topic = topicService.findAll();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("questions", questions);
		model.addAttribute("topics", topic);
		return "question/list";
	}
}
