package vn.com.r2s.fms.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.Assignment;
import vn.com.r2s.fms.entity.AssignmentID;
import vn.com.r2s.fms.repository.AssignmentRepository;
import vn.com.r2s.fms.service.AssignmentService;

@Service
public class AssignmentServiceImpl implements AssignmentService {

	@Autowired
	private AssignmentRepository assignmentRepository;

	@Override
	public List<Assignment> getAllAss() {
		return (List<Assignment>) assignmentRepository.findAll();

	}

	@Override
	public void saveAss(Assignment ass) {
		assignmentRepository.save(ass);

	}

	@Override
	public Optional<Assignment> findAssById(Integer ModuleID, Integer ClassID, String TrainerID) {

		AssignmentID assignmentID = new AssignmentID();
		assignmentID.setModuleID(ModuleID);
		assignmentID.setClassID(ClassID);
		assignmentID.setTrainerID(TrainerID);

		return assignmentRepository.findById(assignmentID);
	}

	@Override
	public void deleteAss(Integer ModuleID, Integer ClassID, String TrainerID) {

		AssignmentID assignmentID = new AssignmentID();
		assignmentID.setModuleID(ModuleID);
		assignmentID.setClassID(ClassID);
		assignmentID.setTrainerID(TrainerID);
		assignmentRepository.deleteById(assignmentID);

	}

//	@Override
//	public void saveAss(Assignment ass) {
//		assignmentRepository.save(ass);
//
//	}
//
//	@Override
//	public Optional<Assignment> findAssById(Integer id) {
//
//		return assignmentRepository.findById(id);
//	}
//
//	@Override
//	public void deleteAssID(Integer id) {
//		assignmentRepository.deleteAssID(id);
//	}
//
	@Override
	public Page<Assignment> findPaginated(int pageNo, int pageSize) {

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.assignmentRepository.findAll(pageable);
	}

//
//	@Override
//	public List<Assignment> getAssUsername(String username) {
//
//		return null;
//	}
//
	@Override
	public Page<Assignment> findPaginated1(String username, int pageNo, int pageSize) {

		return this.assignmentRepository.search(username, PageRequest.of(pageNo - 1, pageSize));
	}

//
	@Override
	public Page<Assignment> findPaginatedUsername(String username, int pageNo, int pageSize) {

		return this.assignmentRepository.getAssUsername(username, PageRequest.of(pageNo - 1, pageSize));
	}

//
	@Override
	public Page<Assignment> findPaginatedFindUsername(String username, String fin, int pageNo, int pageSize) {

		return this.assignmentRepository.getAssUsernameFind(username, fin, PageRequest.of(pageNo - 1, pageSize));
	}
//
//	@Override
//	public Assignment findOne(Integer id) {
//
//		return assignmentRepository.getOne(id);
//	}
//
//	@Override
//	public Page<Object[]> findAss(String username, int pageNo, int pageSize) {
//		
//		return this.assignmentRepository.findAss(username, PageRequest.of(pageNo - 1, pageSize));
//	}

	@Override
	public Assignment findOne(Integer ModuleID, Integer ClassID, String Username) {

		AssignmentID assignmentID = new AssignmentID();
		assignmentID.setModuleID(ModuleID);
		assignmentID.setClassID(ClassID);
		assignmentID.setTrainerID(Username);
		return assignmentRepository.getOne(assignmentID);
	}

	@Override
	public List<Assignment> findMoIDcLIDtRID(Integer ClassID, Integer ModuleID, String TrainerID) {

		return assignmentRepository.findMoIDcLIDtRID(ClassID, ModuleID, TrainerID);
	}

	@Override
	public Page<Assignment> getAll(int pageNo, int pageSize) {

		return assignmentRepository.getAll(PageRequest.of(pageNo - 1, pageSize));
	}

	@Override
	public Assignment findByRegistrationCode(String RegistrationCode) {
		try {
			return assignmentRepository.findOneByRegistrationCode(RegistrationCode);
		} catch (Exception e) {
			return null;
		}
	}

}
