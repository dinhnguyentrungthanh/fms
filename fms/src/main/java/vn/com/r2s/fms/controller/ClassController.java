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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.com.r2s.fms.entity.Class;
import vn.com.r2s.fms.model.UserModel;
import vn.com.r2s.fms.repository.ClassRepository;
import vn.com.r2s.fms.service.ClassService;

@Controller
public class ClassController {
	@Autowired
	private ClassService classService;
	
	@Autowired ClassRepository classRepository;
	
	@RequestMapping("/admin/class_index")
	public String index(Model model, HttpServletRequest request) {
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}

		return findPaginated(1, model);
	}
	
	@GetMapping("/admin/class_index/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 6;

		LocalDate ht = LocalDate.now();
		model.addAttribute("ht", ht);
		
		Page<Class> page = classService.findPaginated(pageNo, pageSize);
		List<Class> classs = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("classs", classs);
		
		return "Class/class_index";
	}
////////////////////////////Trainer class ////////////////////////////////////	
	@GetMapping("/trainer/trainer_class/page/{pageNo}")
	public String findAllByTrainer(@PathVariable(value = "pageNo") int pageNo, Model model, HttpServletRequest request) {
		int pageSize = 6;
		
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("USER");
//		user.getUsername()
		
		Page<Object[]> page = classService.findAllByTrainer(user.getUsername() , pageNo, pageSize);
		List<Object[]> classs = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("classs", classs);
		
		return "Class/trainer_class";
	}
////////////////////////////Trainer class list////////////////////////////////////	
	@GetMapping("/trainer/trainer_class_list/page/{pageNo}")
	public String findAllByClass(@PathVariable(value = "pageNo") int pageNo, Model model, HttpServletRequest request) {
		int pageSize = 6;
		int id = Integer.parseInt(request.getParameter("id"));
		Page<Object[]> page = classService.findAllByClass(id , pageNo, pageSize);
		List<Object[]> classs = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("classs", classs);
		model.addAttribute("class", classService.findOne(id));	
		return "Class/trainer_class_list";
	}
////////////////////////// Trainee Class////////////////////////////////////////////
	@GetMapping("/trainee/trainee_class/page/{pageNo}")
	public String findAllByTrainee(@PathVariable(value = "pageNo") int pageNo, Model model, HttpServletRequest request) {
		int pageSize = 6;
		
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("USER");
		
		Page<Object[]> page = classService.findAllByTrainee(user.getUsername() , pageNo, pageSize);
		List<Object[]> classs = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("classs", classs);
		
		return "Class/trainee_class";
	}
//////////////////////////// Trainee List////////////////////////////////////////////	
	@GetMapping("/trainee/trainee_class_list/page/{pageNo}")
	public String findAllByClassList(@PathVariable(value = "pageNo") int pageNo, Model model, HttpServletRequest request) {
		int pageSize = 6;

		int id = Integer.parseInt(request.getParameter("id"));

		Page<Object[]> page = classService.findAllByClassList(id , pageNo, pageSize);
		List<Object[]> classs = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("classs", classs);
		model.addAttribute("class", classService.findOne(id));
		
		return "Class/trainee_class_list";
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/admin/class_add")
	public String addClass(Model model, HttpServletRequest request) {
		List<Class> classs = classService.getAllClass();
		model.addAttribute("class", new Class());		
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}
		return "Class/class_add";
	}

	@RequestMapping(value = "/admin/class_save", method = RequestMethod.POST)
	public String save(@Valid Class class1, BindingResult bindingResult, RedirectAttributes model) {
		if (bindingResult.hasErrors()) {
			return "Class/class_add";
		} else {
			classService.saveClass(class1);
			return "redirect:/admin/class_add?message=success";


		}
	}

	@RequestMapping(value = "/admin/class_edit", method = RequestMethod.GET)
	public String editClass(@RequestParam("id") Integer classID, Model model, HttpServletRequest request) {
		List<Class> classs = classService.getAllClass();
		model.addAttribute("classs", classs);
		Optional<Class> classEdit = classService.findClassById(classID);
		classEdit.ifPresent(class1 -> model.addAttribute("class", class1));
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("successok", true);
			request.setAttribute("message", message);
		}

		return "Class/class_edit";
	}

	@RequestMapping(value = "/admin/class_saveUpdate", method = RequestMethod.POST)
	public String saveUpdate(@Valid Class class1, BindingResult bindingResult, RedirectAttributes model) {
		if (bindingResult.hasErrors()) {
			return "Class/class_add";
		} else {
			classService.saveClass(class1);
			return "redirect:/admin/class_edit?id=" + class1.getClassID() + "&message=success";
		}
	}

	@RequestMapping(value = "/admin/class_delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") Integer classID, RedirectAttributes model) {
//		classService.deleteClass(classID);
		Class classEdit = classService.findOne(classID);
		classEdit.setIsDeleted(true);
		classService.saveClass(classEdit);
//		String message = request.getParameter("message");
//		if (message != null) {
//			request.setAttribute("message", message);
//			request.setAttribute("successok", true);
//		}
		return "redirect:/admin/class_index?message=success";
	}
	
//	@RequestMapping("/")
//	public String index(Model model, HttpServletRequest request) {
//		List<Class> classs = classService.getAllClass();
//		model.addAttribute("classs", classs);
//		String message = request.getParameter("message");
//		if (message != null) {
//			request.setAttribute("message", message);
//			request.setAttribute("successok", true);
//		}
//		return "Class/class_index";
//	}

//	@GetMapping("/page/{pageNo}")
//	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
//		int pageSize = 3;
//
//		Page<Class> page = classService.findPaginated(pageNo, pageSize);
//		List<Class> classs = page.getContent();
//
//		model.addAttribute("currentPage", pageNo);
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("totalItems", page.getTotalElements());
//		model.addAttribute("classs", classs);
//		return "Class/class_index";
//	}
	

}
