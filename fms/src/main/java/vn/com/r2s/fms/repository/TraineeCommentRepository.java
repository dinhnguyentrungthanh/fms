package vn.com.r2s.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.Class;
import vn.com.r2s.fms.entity.Module;
import vn.com.r2s.fms.entity.TraineeComment;


@Repository
public interface TraineeCommentRepository extends JpaRepository<TraineeComment, Integer> {

	public List<TraineeComment> findAllByClassID(Class clas);

	@Query(value = "SELECT p.TraineeID, p.Comment FROM comment p WHERE CONCAT(p.TraineeID, ' ',p.Comment) LIKE %?1%", nativeQuery = true)
	public List<TraineeComment> getAllTraineeComment();

	public List<TraineeComment> findAllByModuleID(Module module);
	
}
