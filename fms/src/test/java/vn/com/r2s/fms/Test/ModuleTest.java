package vn.com.r2s.fms.Test;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.com.r2s.fms.entity.Module;
import vn.com.r2s.fms.repository.AdminRepository;
import vn.com.r2s.fms.repository.FeedbackRepository;
import vn.com.r2s.fms.repository.ModuleRepository;
import vn.com.r2s.fms.service.ModuleService;

@SpringBootTest
public class ModuleTest {
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private ModuleService moduleService;
	
	@Test
	public void TestDelete() {	
		moduleService.deleteModule(1);
	}
	
	@Test
	public void TestInsert() {
		Module module = new Module();
	
		module.setEndTime(LocalDate.parse("2018-12-27"));
		module.setIsDeleted(false);
		module.setModuleName("a");
		module.setStartTime(LocalDate.parse("2018-12-27"));
		module.setAdmin(adminRepository.getOne("1"));
		module.setFeedback(feedbackRepository.getOne(1));
		moduleService.saveModule(module);
	}
	
	@Test
	public void TestUpdate() {
		Module moduleEdit = new Module();
					
		moduleEdit.setModuleID(9);
		moduleEdit.setEndTime(LocalDate.parse("2018-12-27"));
		moduleEdit.setIsDeleted(false);
		moduleEdit.setModuleName("abc");
		moduleEdit.setStartTime(LocalDate.parse("2018-12-27"));
		moduleEdit.setAdmin(adminRepository.getOne("1"));
		moduleEdit.setFeedback(feedbackRepository.getOne(1));
		moduleService.saveModule(moduleEdit);
	}
}
