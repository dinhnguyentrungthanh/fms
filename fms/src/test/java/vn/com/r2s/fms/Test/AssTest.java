package vn.com.r2s.fms.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.com.r2s.fms.entity.Assignment;
import vn.com.r2s.fms.entity.AssignmentID;
import vn.com.r2s.fms.repository.ClassRepository;
import vn.com.r2s.fms.repository.ModuleRepository;
import vn.com.r2s.fms.service.AssignmentService;
import vn.com.r2s.fms.service.TrainerService;


@SpringBootTest
public class AssTest {
	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	private TrainerService trainerService;

	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private ModuleRepository moduleRepository;

	
	@Test
	public void TestDelete() {	
		
		assignmentService.deleteAss(1, 1, "nam");
		
	}
	@Test
	public void TestInsert() {	
		
		DateFormat dateFormat = new SimpleDateFormat("ddMMHHmmss");
		Calendar cal = Calendar.getInstance(); 
		
		Assignment ass= new Assignment();
		AssignmentID assignmentID =new AssignmentID();
		assignmentID.setModuleID(1);
		assignmentID.setClassID(1);
		assignmentID.setTrainerID("nam");
		
		ass.setAssignmentID(assignmentID);
	
		ass.setClassID(classRepository.getOne(1));
		ass.setModuleID(moduleRepository.getOne(1));
		ass.setTrainerID(trainerService.findOne("nam"));
		
		
		String recode = "CL" + ass.getClassID().getClassID() + "M" + ass.getModuleID().getModuleID() + "T"
				+ dateFormat.format(cal.getTime());
		ass.setRegistrationCode(recode);
		 assignmentService.saveAss(ass);
		
	}
	
	
	@Test
	public void TestUpdate() {

		Assignment ass = new Assignment();
		Optional<Assignment> optional = assignmentService.findAssById(1, 1, "nam");

		if(optional.isPresent()) {
			assignmentService.deleteAss(1, 1, "nam");			
		}
		
		Optional<Assignment> optional1 = assignmentService.findAssById(1, 1, "quan");
		if(!optional1.isPresent()) {
			
			AssignmentID assignmentID = new AssignmentID();
		assignmentID.setModuleID(1);
		assignmentID.setClassID(1);
		assignmentID.setTrainerID("quan");

		ass.setAssignmentID(assignmentID);
		ass.setClassID(optional.get().getClassID());
		ass.setModuleID(optional.get().getModuleID());
		ass.setTrainerID(trainerService.findOne("quan"));
		ass.setRegistrationCode(optional.get().getRegistrationCode());

		assignmentService.saveAss(ass);
			
		}
		
	}
	
}
