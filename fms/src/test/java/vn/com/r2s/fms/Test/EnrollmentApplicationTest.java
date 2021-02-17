package vn.com.r2s.fms.Test;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.com.r2s.fms.entity.Enrollment;
import vn.com.r2s.fms.entity.EnrollmentID;
import vn.com.r2s.fms.repository.ClassRepository;
import vn.com.r2s.fms.service.EnrollmentService;
import vn.com.r2s.fms.service.TraineeService;



@SpringBootTest
public class EnrollmentApplicationTest {
	@Autowired
	private EnrollmentService enrollmentService;

	@Autowired
	private TraineeService traineeService;

	@Autowired
	private ClassRepository classRepository;


	
	@Test
	public void TestDelete() {	
		
		enrollmentService.deleteEnrollment("1", 1);
		
	}
	@Test
	public void TestInsert() {	
		 
		
		Enrollment erm= new Enrollment();
		EnrollmentID enrollmentID =new EnrollmentID();
		enrollmentID.setClassID(1);
		enrollmentID.setTraineeID("1");
		
		erm.setEnrollmentID(enrollmentID);
	
		erm.setClassID(classRepository.getOne(1));
		erm.setTraineeID(traineeService.findOne("1"));
		
		

		 enrollmentService.saveUpdate(erm);
		
	}
	
	
	@Test
	public void TestUpdate() {

		Enrollment erm = new Enrollment();
		Optional<Enrollment> optional = enrollmentService.findEnrollmentByTraineeID("1", 1);

		if(optional.isPresent()) {
			enrollmentService.deleteEnrollment("1", 1);			
		}
		
		Optional<Enrollment> optional1 = enrollmentService.findEnrollmentByTraineeID("2", 1);
		if(!optional1.isPresent()) {
			
		EnrollmentID enrollmentID = new EnrollmentID();
		enrollmentID.setClassID(1);
		enrollmentID.setTraineeID("2");

		erm.setEnrollmentID(enrollmentID);
		erm.setClassID(optional.get().getClassID());

		erm.setTraineeID(traineeService.findOne("2"));

		enrollmentService.saveUpdate(erm);
			
		}
		
	}
	
}
