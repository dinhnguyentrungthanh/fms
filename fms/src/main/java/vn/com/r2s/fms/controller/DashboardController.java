package vn.com.r2s.fms.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.r2s.fms.service.ModuleService;

@Controller
public class DashboardController {
	
	@Autowired
	private ModuleService moduleService;

	@RequestMapping(value = "/admin/dashboard")
	public String showAdminDashboardPage() {
		return "dashboard/admin-dashboard";
	}
	
	@RequestMapping(value = "/trainer/dashboard")
	public String showTrainerDashboardPage() {
		return "dashboard/trainer-dashboard";
	}
	
	@RequestMapping(value = "/trainee/dashboard")
	public String showTraineeDashboardPage(Model model, HttpServletRequest request) {
		List<Object[]> modules = moduleService.getAll();
		
		model.addAttribute("modules", modules);
		return "dashboard/trainee-dashboard";
	}
	
}
