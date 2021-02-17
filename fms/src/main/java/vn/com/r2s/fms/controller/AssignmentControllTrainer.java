package vn.com.r2s.fms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.com.r2s.fms.entity.Assignment;
import vn.com.r2s.fms.model.UserModel;
import vn.com.r2s.fms.service.AssignmentService;

@Controller
@RequestMapping("/trainer/Assignment")
public class AssignmentControllTrainer {
	
	@Autowired
	private AssignmentService assignmentService;

	@GetMapping("/indexAssignment/page/{pageNo}")
	public String findPaginatedUsername(@PathVariable(value = "pageNo") int pageNo, Model model,
			HttpServletRequest request) {
		int pageSize = 3;
		HttpSession httpSession = request.getSession();
		UserModel user = (UserModel) httpSession.getAttribute("USER");

		Page<Assignment> page = assignmentService.findPaginatedUsername(user.getUsername(), pageNo, pageSize);
		List<Assignment> ass = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("ass", ass);
		return "Admin/Assignment/TrainerAssignment";
	}

	@RequestMapping("/indexAssignment/")
	public String indextrainer(Model model, HttpServletRequest request) {

		return findPaginatedUsername(1, model, request);

	}

	@GetMapping("/indexAssignment/findid/page/{pageNo}")
	public String findPaginatedFindUsername(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("keyword") String keyword, Model model, HttpServletRequest request) {

		try {
			int pageSize = 3;
			HttpSession httpSession = request.getSession();
			UserModel user = (UserModel) httpSession.getAttribute("USER");

			if (keyword == "") {
				return findPaginatedUsername(1, model, request);
			}
			Page<Assignment> page = assignmentService.findPaginatedFindUsername(user.getUsername(), keyword, pageNo,
					pageSize);
			List<Assignment> ass = page.getContent();

			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			model.addAttribute("ass", ass);
			return "Admin/Assignment/TrainerAssignment";
		} catch (Exception e) {
			return "Admin/Assignment/TrainerAssignment";
		}

	}
}
