package vn.com.r2s.fms.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import vn.com.r2s.fms.entity.Enrollment;
import vn.com.r2s.fms.entity.EnrollmentID;
import vn.com.r2s.fms.entity.Trainee;
import vn.com.r2s.fms.repository.ClassRepository;
import vn.com.r2s.fms.repository.EnrollmentRepository;
import vn.com.r2s.fms.repository.TraineeRepository;
import vn.com.r2s.fms.service.ClassService;
import vn.com.r2s.fms.service.EnrollmentService;

@Controller
@RequestMapping("/admin/")
public class EnrollmentController {
	@Autowired
	EnrollmentService enrollmentService;
	@Autowired
	ClassService classService;
	@Autowired
	EnrollmentRepository enrollmentRepository;
	@Autowired
	ClassRepository classRepository;

	@Autowired
	TraineeRepository traineeRepository;

	@RequestMapping("/enrollment")
	public String index(Model model, HttpServletRequest request) {

		String message = request.getParameter("message");
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}
		return findPaginated(1, model);
	}

	@RequestMapping(value = "cancel")
	public String cancelUser(Model model) {
		model.addAttribute("enrollment", new Trainee());
		return "redirect:/admin/enrollment";
	}

	@RequestMapping(value = "/seeDetails", method = RequestMethod.GET)
	public String detailsEnrollment(@RequestParam("traineeID") String traineeID,
			@RequestParam("classID") Integer classID, Model model) {
		Optional<Enrollment> enrollmentSee = enrollmentService.findEnrollmentByTraineeID(traineeID, classID);
		enrollmentSee.ifPresent(enrollment -> model.addAttribute("enrollment", enrollment));
		return "enrollment/seeDetailsEnrollment";
	}

	@RequestMapping(value = "/edit")
	public String editEnrollment(@RequestParam("traineeID") String traineeID, @RequestParam("classID") Integer classID,
			Model model, HttpServletRequest request) {

		model.addAttribute("classes", classRepository.findAll());
		Optional<Enrollment> enrollmentSee = enrollmentService.findEnrollmentByTraineeID(traineeID, classID);
		enrollmentSee.ifPresent(enrollment -> model.addAttribute("enrollment", enrollment));
		
		String message = request.getParameter("message");
		String message1 = request.getParameter("message1");
		if (message1 != null) {
			request.setAttribute("message1", message1);
			request.setAttribute("successfail", true);
		}if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("successok", true);
		}
		return "enrollment/editEnrollment";
	}

	@RequestMapping(value = "saveUpdate", method = RequestMethod.POST)
	public String saveUpdate(@RequestParam("username") String traineeID, @RequestParam("newClassID") Integer classID,
			Model model, HttpServletRequest request, Enrollment enrollment, BindingResult bindingResult) {
		int newClassID = Integer.parseInt(request.getParameter("newClassID"));
		int oldClassID = Integer.parseInt(request.getParameter("oldClassID"));
		String username = request.getParameter("username");
		List<Enrollment> erm = enrollmentRepository.findAll();

		EnrollmentID oldEnrollmentID = new EnrollmentID();
		oldEnrollmentID.setTraineeID(username);
		oldEnrollmentID.setClassID(oldClassID);

		EnrollmentID newEnrollmentID = new EnrollmentID();
		newEnrollmentID.setTraineeID(username);
		newEnrollmentID.setClassID(newClassID);
		enrollment.setEnrollmentID(newEnrollmentID);

		enrollment.setClassID(classService.findOne(newClassID));
		enrollment.setTraineeID(traineeRepository.getOne(username));
		String url="/admin/edit?enrollmentID&classID="+ oldEnrollmentID.getClassID() +"&traineeID="+oldEnrollmentID.getTraineeID();
		model.addAttribute("url",url );
		for (Enrollment e : erm) {
			if (newClassID == oldClassID) {
				return "redirect:/admin/edit?enrollmentID&classID=" + (enrollment.getEnrollmentID().getClassID())
						+ "&traineeID=" + (enrollment.getEnrollmentID().getTraineeID()) + "&message=successok";

			} else if (e.getTraineeID().getUsername().equals(newEnrollmentID.getTraineeID()) && e.getClassID().getClassID() == newClassID) {
				return "redirect:/admin/edit?enrollmentID&classID=" + (enrollment.getEnrollmentID().getClassID())
						+ "&traineeID=" + (enrollment.getEnrollmentID().getTraineeID()) + "&message1=successfail";
				

			}
		}
		// Câu lệnh update
		enrollmentRepository.deleteById(oldEnrollmentID);
		enrollmentService.saveUpdate(enrollment);
		
		return "redirect:/admin/edit?enrollmentID&classID=" + (enrollment.getEnrollmentID().getClassID())
				+ "&traineeID=" + (enrollment.getEnrollmentID().getTraineeID()) + "&message=success";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deleteEnrollment(@RequestParam("traineeID") String traineeID,
			@RequestParam("classID") Integer classID, RedirectAttributes model) {
		enrollmentService.deleteEnrollment(traineeID, classID);
		return "redirect:/admin/enrollment?message=success";

	}

	@GetMapping("/enrollment/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 8;

		LocalDate ht = LocalDate.now();
		model.addAttribute("ht", ht);
		
		Page<Enrollment> page = enrollmentService.findPaginated(pageNo, pageSize);
		List<Enrollment> enrollments = page.getContent();
		List<Class> classes = classService.getAllClass();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("enrollments", enrollments);
		model.addAttribute("classes", classes);
		
		return "enrollment/index";
	}
}

