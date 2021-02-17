package vn.com.r2s.fms.controller;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.com.r2s.fms.entity.Assignment;
import vn.com.r2s.fms.entity.AssignmentID;
import vn.com.r2s.fms.entity.Module;
import vn.com.r2s.fms.entity.Trainer;
import vn.com.r2s.fms.repository.AssignmentRepository;
import vn.com.r2s.fms.repository.ClassRepository;
import vn.com.r2s.fms.repository.ModuleRepository;
import vn.com.r2s.fms.service.AssignmentService;
import vn.com.r2s.fms.service.ClassService;
import vn.com.r2s.fms.service.ModuleService;
import vn.com.r2s.fms.service.TrainerService;

@Controller
@RequestMapping("admin/Assignment")
public class AssignmentController {
	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	private AssignmentRepository assignmentRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private ClassService classService;

	@Autowired
	private TrainerService trainerService;

	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request) {

		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}
		return findPaginated(1, model);
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 3;

		LocalDate ht = LocalDate.now();
		model.addAttribute("ht", ht);
		Page<Assignment> page = assignmentService.getAll(pageNo, pageSize);
		List<Assignment> ass = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("ass", ass);
		return "Admin/Assignment/index";
	}

	@RequestMapping(value = "add")
	public String addUser(Model model, HttpServletRequest request) {
		model.addAttribute("ass", new Assignment());

		List<Module> module = moduleRepository.findAll();
		model.addAttribute("module", module);

		List<vn.com.r2s.fms.entity.Class> classs = classRepository.findAll();
		model.addAttribute("classs", classs);

		List<Trainer> trainer = trainerService.getAllTrainer();
		model.addAttribute("trainer", trainer);

		String message1 = request.getParameter("message1");
		if (message1 != null) {
			request.setAttribute("message1", message1);
			request.setAttribute("successfail", true);
		}
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}

		return "Admin/Assignment/add";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Assignment ass, HttpServletRequest request) {
		DateFormat dateFormat = new SimpleDateFormat("ddMMHHmmss");
		Calendar cal = Calendar.getInstance();
		int moduleID = Integer.parseInt(request.getParameter("moduleID"));
		int classID = Integer.parseInt(request.getParameter("classID"));
		String trainerID = request.getParameter("trainerID");

	
		try {

			List<Assignment> assignment = assignmentService.findMoIDcLIDtRID(classID, moduleID, trainerID);

			if (assignment.isEmpty()) {
				AssignmentID assignmentID = new AssignmentID();
				assignmentID.setModuleID(moduleID);
				assignmentID.setClassID(classID);
				assignmentID.setTrainerID(trainerID);
				ass.setAssignmentID(assignmentID);
				
				String recode = "CL" + ass.getClassID().getClassID() + "M" + ass.getModuleID().getModuleID() + "T"
						+ dateFormat.format(cal.getTime());
			
			
				ass.setRegistrationCode(recode);
				assignmentService.saveAss(ass);
				return "redirect:/admin/Assignment/add?message=successok";
			} else {
				return "redirect:/admin/Assignment/add?message1=successfail";
			}
		} catch (Exception e) {

		}
		return "redirect:/admin/Assignment/add?message1=successfail";

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editAss(@RequestParam("ModuleID") Integer ModuleID, @RequestParam("ClassID") Integer ClassID,
			@RequestParam("TrainerID") String TrainerID, Model model, HttpServletRequest request) {
		Optional<Assignment> AssEdit = assignmentService.findAssById(ModuleID, ClassID, TrainerID);
		AssEdit.ifPresent(ass -> model.addAttribute("ass", ass));

		List<Trainer> trainer = trainerService.getAllTrainer();
		model.addAttribute("trainer", trainer);
		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("successok", true);
			request.setAttribute("message", message);
		}
		String message1 = request.getParameter("message1");
		if (message1 != null) {
			request.setAttribute("successfail", true);
			request.setAttribute("message1", message1);
		}
		return "Admin/Assignment/editAss";
	}

	@RequestMapping(value = "saveupdate", method = RequestMethod.POST)
	public String saveupdate(Assignment ass, HttpServletRequest request ,Model model) {

		int moduleID = Integer.parseInt(request.getParameter("ModuleID.ModuleID"));
		int classID = Integer.parseInt(request.getParameter("ClassID.ClassID"));
		String trainerIDNew = request.getParameter("trainerIDNew");
		String trainerID = request.getParameter("trainerIDol");
		
		List<Assignment> assignment = assignmentService.findMoIDcLIDtRID(classID, moduleID, trainerIDNew);
		
//		String RegistrationCode = request.getParameter("RegistrationCode");
		if (trainerIDNew.equals(trainerID)) {
			return "redirect:/admin/Assignment/edit?&ModuleID=" + ass.getModuleID().getModuleID() + "&ClassID="
					+ ass.getClassID().getClassID() + "&TrainerID=" + ass.getTrainerID().getUsername()
					+ "&message=successok";

		}
		if (!trainerIDNew.equals(trainerID)&& !assignment.isEmpty()) {
			
			return "redirect:/admin/Assignment/edit?&ModuleID=" + ass.getModuleID().getModuleID() + "&ClassID="
					+ ass.getClassID().getClassID() + "&TrainerID=" + trainerID
					+ "&message1=successfail";
		}else {
			AssignmentID assignmentIDol = new AssignmentID();
			assignmentIDol.setModuleID(moduleID);
			assignmentIDol.setClassID(classID);
			assignmentIDol.setTrainerID(trainerID);
			assignmentRepository.deleteById(assignmentIDol);

			AssignmentID assignmentInew = new AssignmentID();
			assignmentInew.setModuleID(moduleID);
			assignmentInew.setClassID(classID);
			assignmentInew.setTrainerID(trainerIDNew);
			ass.setAssignmentID(assignmentInew);

			ass.setTrainerID(trainerService.findOne(trainerIDNew));

			ass.setModuleID(moduleRepository.getOne(moduleID));
			ass.setClassID(classRepository.getOne(classID));

			assignmentService.saveAss(ass);
		}
	
		return "redirect:/admin/Assignment/edit?&ModuleID=" + ass.getModuleID().getModuleID() + "&ClassID="
				+ ass.getClassID().getClassID() + "&TrainerID=" + ass.getTrainerID().getUsername()
				+ "&message=successok";

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteAss(@RequestParam("ModuleID") Integer ModuleID, @RequestParam("ClassID") Integer ClassID,
			@RequestParam("TrainerID") String TrainerID, Model model, HttpServletRequest request) {

		assignmentService.deleteAss(ModuleID, ClassID, TrainerID);
		return "redirect:/admin/Assignment/?message=success";

	}

	@GetMapping("/findid/page/{pageNo}")
	public String findPaginateAss(@Valid @PathVariable(value = "pageNo") int pageNo, @Param("keyword") String keyword,
			Model model) {

		int pageSize = 10;
		if (keyword == "") {
			return findPaginated(1, model);
		}
		LocalDate ht = LocalDate.now();
		model.addAttribute("ht", ht);
		Page<Assignment> page = assignmentService.findPaginated1(keyword, pageNo, pageSize);

		List<Assignment> ass = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("ass", ass);
		return "Admin/Assignment/index";

	}

	
}
