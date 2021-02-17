package vn.com.r2s.fms.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.Class;
import vn.com.r2s.fms.entity.Module;
import vn.com.r2s.fms.entity.TraineeComment;
import vn.com.r2s.fms.repository.ClassRepository;
import vn.com.r2s.fms.repository.ModuleRepository;
import vn.com.r2s.fms.repository.TraineeCommentRepository;
import vn.com.r2s.fms.service.TraineeCommentService;


@Service
public class TraineeCommentServiceImpl implements TraineeCommentService{
	@Autowired
	private TraineeCommentRepository cmRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	////////////////////////////////////////////////////////////////////////	
	@Override
	public List<TraineeComment> findAll() {
		return cmRepository.findAll();
	}
/////////////////////////////////////////////////////////////////////////

	@Override
	public List<TraineeComment> getAllTraineeComment() {
		return (List<TraineeComment>) cmRepository.findAll();
	}

	@Override
	public Optional<TraineeComment> findTraineeCommentById(Integer TraineeCommentID) {
		return cmRepository.findById(TraineeCommentID);
	}

	@Override
	public List<TraineeComment> listAll(String keyword) {
		return null;
	}

	@Override
	public Page<TraineeComment> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		@SuppressWarnings("unused")
		TraineeComment traineeComment = null;
		return this.cmRepository.findAll(pageable);
	}
	
	@SuppressWarnings("unused")
	@Override
	public List<TraineeComment> findOneClassID(Integer classID) {
		Class clas = classRepository.findOneByClassID(classID);
		TraineeComment traineeComment = null;
		return this.cmRepository.findAllByClassID(clas);
	}

	@SuppressWarnings("unused")
	@Override
	public List<TraineeComment> findOneModuleID(Integer moduleID) {
		Module module = moduleRepository.findOneByModuleID(moduleID);
		TraineeComment traineeComment1 = null;
		return this.cmRepository.findAllByModuleID(module);
	}


}
