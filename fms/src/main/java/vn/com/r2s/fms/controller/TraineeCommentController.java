package vn.com.r2s.fms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.com.r2s.fms.entity.Class;
import vn.com.r2s.fms.entity.Module;
import vn.com.r2s.fms.entity.TraineeComment;
import vn.com.r2s.fms.service.ClassService;
import vn.com.r2s.fms.service.ModuleService;
import vn.com.r2s.fms.service.TraineeCommentService;

@Controller
public class TraineeCommentController {

	@Autowired
	TraineeCommentService cmService;
	
	@Autowired
	ClassService classService;
	
	@Autowired
	ModuleService moduleService;
	
    @RequestMapping("/admin/Comment")
	public String index(Model model) {
		return findPaginated(1, model);
	}
	
	@GetMapping("/admin/Comment/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;

		Page<TraineeComment> page = cmService.findPaginated(pageNo, pageSize);
		List<TraineeComment> traineeComments = page.getContent();
		
		List<Class> classs = classService.findAll();
		List<Module> modules = moduleService.findAll();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("traineeComments", traineeComments);
		model.addAttribute("classs", classs);
		model.addAttribute("modules", modules);
		return "statictis/comment";
	}
	
	@RequestMapping("/trainer/Comment")
	public String indexTrainer(Model model) {
		return findPaginated(1, model);
	}
	
	@GetMapping("/trainer/Comment/page/{pageNo}")
	public String findPaginatedTrainer(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;

		Page<TraineeComment> page = cmService.findPaginated(pageNo, pageSize);
		List<TraineeComment> traineeComments = page.getContent();
		List<Class> classs = classService.findAll();
		List<Module> modules = moduleService.findAll();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("traineeComments", traineeComments);
		model.addAttribute("classs", classs);
		model.addAttribute("modules", modules);
		return "statictis/comment";
	}


}
