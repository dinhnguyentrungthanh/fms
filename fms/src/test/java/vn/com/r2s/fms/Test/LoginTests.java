package vn.com.r2s.fms.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.com.r2s.fms.entity.Admin;
import vn.com.r2s.fms.entity.Trainee;
import vn.com.r2s.fms.entity.Trainer;
import vn.com.r2s.fms.service.AdminService;
import vn.com.r2s.fms.service.TraineeService;
import vn.com.r2s.fms.service.TrainerService;

@SpringBootTest
public class LoginTests {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private TraineeService traineeService;

	@Autowired
	private TrainerService trainerService;
	
	@Test
	void failLoginAdmin() throws Exception {
		String username = "admin";
		String password = "123123";
		
		Admin failLogin = adminService.checkLogin(username, password);
		assertNotNull(failLogin);
	}
	
	@Test
	void successLoginAdmin() throws Exception {
		String username = "admin";
		String password = "admin";
		
		Admin successLogin = adminService.checkLogin(username, password);
		assertNotNull(successLogin);
	}
	
	@Test
	void failLoginTrainee() throws Exception {
		String username = "trainee";
		String password = "123123";
		
		Trainee failLogin = traineeService.checkLogin(username, password);
		assertNotNull(failLogin);
	}
	
	@Test
	void successLoginTrainee() throws Exception {
		String username = "trainee";
		String password = "trainee";
		
		Trainee successLogin = traineeService.checkLogin(username, password);
		assertNotNull(successLogin);
	}
	
	@Test
	void failLoginTrainer() throws Exception {
		String username = "trainer";
		String password = "123123";
		
		Trainer failLogin = trainerService.checkLogin(username, password);
		assertNotNull(failLogin);
	}
	
	@Test
	void successLoginTrainer() throws Exception {
		String username = "trainer";
		String password = "trainer";
		
		Trainer successLogin = trainerService.checkLogin(username, password);
		assertNotNull(successLogin);
	}
	
}
