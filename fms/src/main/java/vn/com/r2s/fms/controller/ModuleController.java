package vn.com.r2s.fms.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import vn.com.r2s.fms.entity.Admin;
import vn.com.r2s.fms.entity.Assignment;
import vn.com.r2s.fms.entity.Feedback;
import vn.com.r2s.fms.entity.Module;
import vn.com.r2s.fms.model.UserModel;
import vn.com.r2s.fms.service.AdminService;
import vn.com.r2s.fms.service.AssignmentService;
import vn.com.r2s.fms.service.FeedbackService;
import vn.com.r2s.fms.service.ModuleService;

@Controller
public class ModuleController {
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AssignmentService assignmentService;
	
	@RequestMapping("/admin/module_index")
	public String index(Model model, HttpServletRequest request) {
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}

		return findPaginated(1, model);
	}
	
	@GetMapping("/admin/module_index/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 2;

		Page<Module> page = moduleService.findPaginated(pageNo, pageSize);
		List<Module> modules = page.getContent();
		
		List<Feedback> feedbacks = feedbackService.getAllFeedback();
		List<Admin> admins = adminService.getAllAdmin();
		
		LocalDate ht = LocalDate.now();
		model.addAttribute("ht", ht);
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("modules", modules);
		
		model.addAttribute("feedbacks", feedbacks);
		model.addAttribute("admins", admins);
		
		return "module/module_index";
	}

	
	@RequestMapping(value = "/admin/module_add")
	public String addModule(Model model, HttpServletRequest request) {
		List<Admin> admins = adminService.getAllAdmin();
		model.addAttribute("admins", admins);
		
		List<Feedback> feedbacks = feedbackService.getAllFeedback();
		model.addAttribute("feedbacks", feedbacks);
		
		model.addAttribute("module", new Module());
		
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}

		return "module/module_add";
	}
	
	@RequestMapping(value = "/admin/module_save", method = RequestMethod.POST)
	public String save(@Valid Module module, BindingResult bindingResult, Model model, HttpServletRequest request) {
		
		List<Admin> admins = adminService.getAllAdmin();
		model.addAttribute("admins", admins);
		
		List<Feedback> feedbacks = feedbackService.getAllFeedback();
		model.addAttribute("feedbacks", feedbacks);
		
		HttpSession httpSession = request.getSession();
		UserModel user = (UserModel) httpSession.getAttribute("USER");
		
		if (bindingResult.hasErrors()) {
			return "module/module_add";
		}else {
			
			if(module.getAdmin().getUsername().trim().length() == 0) {
				Admin admin = adminService.findOneByUsername(user.getUsername());
				
				module.setAdmin(admin);
			}
			
			if(module.getFeedback().getFeedbackID() == null) {	
				module.setFeedback(feedbacks.get(0));
			}
			
			moduleService.saveModule(module);
			return "redirect:/admin/module_add?message=success";
		}
		
	}
	
	@RequestMapping(value = "/admin/module_edit", method = RequestMethod.GET)
	public String editModule(@RequestParam("id") Integer ModuleID, Model model, HttpServletRequest request) {
		List<Admin> admins = adminService.getAllAdmin();
		model.addAttribute("admins", admins);
		
		List<Feedback> feedbacks = feedbackService.getAllFeedback();
		model.addAttribute("feedbacks", feedbacks);

		Optional<Module> moduleEdit = moduleService.findModuleById(ModuleID);
		moduleEdit.ifPresent(module -> model.addAttribute("module", module));
		
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("successok", true);
			request.setAttribute("message", message);
		}

		return "module/module_edit";
	}
	
	@RequestMapping(value = "admin/module_saveUpdate", method = RequestMethod.POST)
	public String saveUpdate(@Valid Module module, BindingResult bindingResult, Model model) {
		List<Admin> admins = adminService.getAllAdmin();
		model.addAttribute("admins", admins);
		
		List<Feedback> feedbacks = feedbackService.getAllFeedback();
		model.addAttribute("feedbacks", feedbacks);
		
		if (bindingResult.hasErrors()) {
			return "module/module_edit";
		} else {
			moduleService.saveModule(module);
			return "redirect:/admin/module_edit?id=" + module.getModuleID() + "&message=success";
		}
	}
	
	@RequestMapping(value = "/admin/module_delete", method = RequestMethod.GET)
	public String deleteModule(@RequestParam("id") Integer ModuleID, Model model) {
		
		moduleService.deleteModule(ModuleID);
		return "redirect:/admin/module_index?message=success";
	}
	
	//Trainer
	@RequestMapping("/trainer/module_index")
	public String indexTrainer(Model model, HttpServletRequest request) {
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}

		return findPaginatedTrainer(1, model, request);
	}
	
	@GetMapping("/trainer/module_index/page/{pageNo}")
	public String findPaginatedTrainer(@PathVariable(value = "pageNo") int pageNo, Model model, HttpServletRequest request) {
		int pageSize = 2;
		
		HttpSession httpSession = request.getSession();
		UserModel user = (UserModel) httpSession.getAttribute("USER");

		Page<Module> page = moduleService.findAllByTrainer(user.getUsername(), pageNo, pageSize);
		List<Module> modules = page.getContent();
		
		List<Feedback> feedbacks = feedbackService.getAllFeedback();
		List<Admin> admins = adminService.getAllAdmin();
		
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("modules", modules);
		
		model.addAttribute("feedbacks", feedbacks);
		model.addAttribute("admins", admins);
		
		return "module/trainer_module_index";
	}
	
	//Trainee
	@RequestMapping("/trainee/module_index")
	public String indexTrainee(Model model, HttpServletRequest request) {
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}

		return findPaginatedTrainee(1, model, request);
	}
	
	@GetMapping("/trainee/module_index/page/{pageNo}")
	public String findPaginatedTrainee(@PathVariable(value = "pageNo") int pageNo, Model model, HttpServletRequest request) {
		int pageSize = 2;
		
		HttpSession httpSession = request.getSession();
		UserModel user = (UserModel) httpSession.getAttribute("USER");

		Page<Module> page = moduleService.findAllByTrainee(user.getUsername(), pageNo, pageSize);
		List<Module> modules = page.getContent();
		
		List<Feedback> feedbacks = feedbackService.getAllFeedback();
		List<Admin> admins = adminService.getAllAdmin();
		
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("modules", modules);
		
		model.addAttribute("feedbacks", feedbacks);
		model.addAttribute("admins", admins);
		
		return "module/trainee_module_index";
	}
}
