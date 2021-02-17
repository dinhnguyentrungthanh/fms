package vn.com.r2s.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.com.r2s.fms.entity.TraineeComment;


public interface TraineeCommentService {
	List<TraineeComment> getAllTraineeComment();
	
	List<TraineeComment> listAll(String keyword);

	Page<TraineeComment> findPaginated(int pageNo, int pageSize);

	List<TraineeComment> findOneClassID(Integer classID);

	List<TraineeComment> findOneModuleID(Integer moduleID);

	Optional<TraineeComment> findTraineeCommentById(Integer TraineeCommentID);
	
	List<TraineeComment> findAll();
}
