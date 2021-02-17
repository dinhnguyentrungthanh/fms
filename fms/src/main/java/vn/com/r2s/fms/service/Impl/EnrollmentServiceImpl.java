package vn.com.r2s.fms.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.Class;
import vn.com.r2s.fms.entity.Trainee;
import vn.com.r2s.fms.entity.Enrollment;
import vn.com.r2s.fms.entity.EnrollmentID;
import vn.com.r2s.fms.repository.ClassRepository;
import vn.com.r2s.fms.repository.EnrollmentRepository;
import vn.com.r2s.fms.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;
	@Autowired
	private ClassRepository classRepository;
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Override
	public List<Enrollment> getAllUser() {
		return (List<Enrollment>) enrollmentRepository.findAll();
	}

	@Override
	public Page<Enrollment> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("classID"));
		@SuppressWarnings("unused")
		Enrollment enrollment = null;
		return this.enrollmentRepository.findAll(pageable);
	}

	@Override
	public void saveEnrollment(Enrollment enrollment) {
		enrollmentRepository.save(enrollment);
	}


    @Override
    public void deleteEnrollment (String traineeID, Integer classID) {
	    EnrollmentID enrollmentID = new EnrollmentID();
	    enrollmentID.setClassID(classID);
	    enrollmentID.setTraineeID(traineeID);
	     enrollmentRepository.deleteById(enrollmentID);
	    return;
    }

	@Override
	public Optional<Enrollment> findEnrollmentByTraineeID(String traineeID, Integer classID) {
	    EnrollmentID enrollmentID = new EnrollmentID();
	    enrollmentID.setClassID(classID);
	    enrollmentID.setTraineeID(traineeID);
	    
		return enrollmentRepository.findById(enrollmentID);
	}
	
	@Override
	public Enrollment save(Enrollment enrollment) {
		
	     return enrollmentRepository.save(enrollment);
	}


	@Override
	public Enrollment saveUpdate(Enrollment enrollment) {
		
	     return enrollmentRepository.save(enrollment);
	}


	@Override
	public List<Enrollment> listAll(String keyword) {
		return null;
	}
	
	
	@SuppressWarnings("unused")
	@Override
	public List<Enrollment> findOneClassID(Integer classID) {
		Class classes =	classRepository.findOneByClassID(classID);
		Trainee trainee = null;
		
		return this.enrollmentRepository.findAllByClassID(classes); 
	}
}
