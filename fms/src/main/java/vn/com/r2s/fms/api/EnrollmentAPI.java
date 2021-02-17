package vn.com.r2s.fms.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;


import vn.com.r2s.fms.entity.Assignment;
import vn.com.r2s.fms.entity.Class;
import vn.com.r2s.fms.entity.Enrollment;
import vn.com.r2s.fms.entity.EnrollmentID;
import vn.com.r2s.fms.service.AssignmentService;
import vn.com.r2s.fms.service.ClassService;
import vn.com.r2s.fms.service.EnrollmentService;

@CrossOrigin
@RestController
@RequestMapping("api/")
public class EnrollmentAPI {

	@Autowired
	private AssignmentService assignmentService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@GetMapping("enrollment/{registration_code}")
	public ResponseEntity<Assignment> checkExist(@PathVariable(value="registration_code") String RegistrationCode) {
		Assignment assignment= assignmentService.findByRegistrationCode(RegistrationCode);
		return ResponseEntity.ok().body(assignment);  
	}
	
	@GetMapping("enrollment/check_joined")
	public ResponseEntity<Class> checkJoined(@RequestParam("registration_code") String registrationCode, @RequestParam("traineeID") String traineeID) {
		
		return ResponseEntity.ok().body(classService.checkJoined(registrationCode, traineeID));
	}
	
	@PostMapping("enrollment/join")
	public boolean join(@RequestParam("classID") int classID, @RequestParam("traineeID") String traineeID) {
		EnrollmentID id = new EnrollmentID();
		id.setClassID(classID);
		id.setTraineeID(traineeID);
		
		Enrollment enrollment = new Enrollment();
		enrollment.setEnrollmentID(id);
		
		enrollment.setClassID(classService.findOne(classID));
		
		try {
			enrollmentService.saveEnrollment(enrollment);

			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// Enrollment
	@GetMapping(value = "enrollment/index/search")
	public List<Enrollment> findClassNamePaginated(@RequestParam(value ="classID", required = false) Integer classID){
		
		
		return enrollmentService.findOneClassID(classID);
		
	}
}
