package vn.com.r2s.fms.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import vn.com.r2s.fms.entity.Enrollment;
import vn.com.r2s.fms.entity.EnrollmentID;


public interface EnrollmentService {

	List<Enrollment> getAllUser();
	
	Page<Enrollment> findPaginated(int pageNo, int pageSize);
	
	Optional<Enrollment> findEnrollmentByTraineeID(String traineeID, Integer classID);

	void deleteEnrollment(String traineeID, Integer classID);
	
	void saveEnrollment(Enrollment enrollment);
	
	Enrollment save(Enrollment enrollment);
	
	Enrollment saveUpdate(Enrollment enrollment);
	
	List<Enrollment> listAll(String keyword);

	List<Enrollment> findOneClassID(Integer classID);

}

