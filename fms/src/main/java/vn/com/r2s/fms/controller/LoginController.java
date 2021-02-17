package vn.com.r2s.fms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.com.r2s.fms.entity.Admin;
import vn.com.r2s.fms.entity.Trainee;
import vn.com.r2s.fms.entity.Trainer;
import vn.com.r2s.fms.model.UserModel;
import vn.com.r2s.fms.service.AdminService;
import vn.com.r2s.fms.service.TraineeService;
import vn.com.r2s.fms.service.TrainerService;

@Controller
public class LoginController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private TraineeService traineeService;

	@Autowired
	private TrainerService trainerService;

	private void addSession(String username, String password, String role, HttpServletRequest request) {
		String isRemember = request.getParameter("isRemember");

		UserModel user = new UserModel();

		user.setRole(role);
		user.setUsername(username);
		user.setPassword(password);

		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("USER", user);

		if (isRemember == null) {
			httpSession.setMaxInactiveInterval(1800);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(HttpServletRequest request) {
		String message = request.getParameter("message");

		if (message != null) {
			request.setAttribute("failLogin", true);
		}

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String checkLogin(HttpServletRequest request, RedirectAttributes model) {
		String url = "";

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");

		if (role.equals("admin")) {
			Admin admin = adminService.checkLogin(username, password);

			if (admin != null) {
				url = "admin/Assignment/";

				addSession(username, password, role, request);
			} else {
				url = "/login?message=invalid";
			}
		} else if (role.equals("trainee")) {
			Trainee trainee = traineeService.checkLogin(username, password);

			if (trainee != null) {
				url = "/trainee/dashboard";

				addSession(username, password, role, request);
			} else {
				url = "/login?message=invalid";
			}
		} else if (role.equals("trainer")) {
			Trainer trainer = trainerService.checkLogin(username, password);

			if (trainer != null) {
				url = "/trainer/Assignment/indexAssignment/";

				addSession(username, password, role, request);
			} else {
				url = "/login?message=invalid";
			}
		}

		return "redirect:" + url;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("USER");

		return "redirect:/login";
	}

}
