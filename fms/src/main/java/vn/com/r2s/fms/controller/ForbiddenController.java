package vn.com.r2s.fms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForbiddenController {

	@RequestMapping(value = "/403")
	public String showForbiddenAdminPage() {
		return "403";
	}
	
}
