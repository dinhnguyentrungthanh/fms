package vn.com.r2s.fms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import vn.com.r2s.fms.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
	@Query(value = "SELECT * FROM module p WHERE IsDeleted = 0", nativeQuery = true)
	public List<Module> getAllModule();

	@Modifying
	@Transactional
	@Query(value = "UPDATE module SET IsDeleted = 1 WHERE ModuleID = ?1", nativeQuery = true)
	void deleteModule(Integer ModuleID);

	@Query(value = "SELECT * FROM module p WHERE IsDeleted = 0", nativeQuery = true)
	public Page<Module> findAllByIsDeleted(Pageable pageable);
	
	@Query(value = "SELECT * "
			+ " FROM module "
			+ " JOIN feedback ON module.FeedbackID = feedback.FeedbackID "
			+ " JOIN assignment ON module.ModuleID = assignment.ModuleID "
			+ " JOIN trainer ON assignment.TrainerID = trainer.Username "
			+ " WHERE trainer.Username=?1 AND module.IsDeleted = 0", nativeQuery = true)
	public Page<Module> findAllByTrainer(String username, Pageable pageable);
	
	@Query(value = "SELECT * "
			+ " FROM module " 
			+ "	JOIN feedback ON module.FeedbackID = feedback.FeedbackID " 
			+ " JOIN assignment ON module.ModuleID = assignment.ModuleID " 
			+ " JOIN class ON assignment.ClassID =  class.ClassID " 
			+ " JOIN enrollment ON class.ClassID = enrollment.ClassID " 
			+ " JOIN trainee ON enrollment.TraineeID = trainee.Username " 
			+ " WHERE trainee.Username=?1 AND module.IsDeleted = 0 AND class.IsDeleted = 0", nativeQuery = true)
	public Page<Module> findAllByTrainee(String username, Pageable pageable);
    
    public List<Module> findAllByModuleName(String moduleName);

	public Module findOneByModuleID(Integer moduleID);
	
	@Query(value = "select  f.FeedbackID, f.Title, a.classID, c.ClassName, m.ModuleID, m.ModuleName, m.FeedbackEndTime "
			+ "from module as m "
			+ "inner join feedback as f on f.FeedbackID = m.FeedbackID "
			+ "inner join assignment as a on  m.ModuleID = a.ModuleID "
			+ "inner join class as c on a.classID = c.ClassID "
			+ "inner join enrollment as e on e.ClassID = c.ClassID",nativeQuery = true)
	public List<Object[]> getAll();
}
