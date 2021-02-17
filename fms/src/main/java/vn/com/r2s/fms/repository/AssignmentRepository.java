package vn.com.r2s.fms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.Assignment;
import vn.com.r2s.fms.entity.AssignmentID;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,AssignmentID> {


	@Query(value = "SELECT * FROM assignment"
			+ " LEFT JOIN class  ON assignment.ClassID=class.ClassID"
			+ " LEFT JOIN trainer  ON assignment.TrainerID=trainer.Username"
			+ " LEFT JOIN module  ON assignment.ModuleID=module.ModuleID", 
			nativeQuery = true)
	public Page<Assignment> getAll(Pageable pageable);

	@Modifying
	@Transactional
	@Query(value = "delete from assignment where assignment.id = ?1", nativeQuery = true)
	void deleteAssID(Integer id);

	@Query(value = "SELECT *  FROM assignment"
			+ " LEFT JOIN class  ON assignment.ClassID=class.ClassID "
			+ " LEFT JOIN trainer  ON assignment.TrainerID=trainer.Username "
			+ " LEFT JOIN module  ON assignment.ModuleID=module.ModuleID "
			+ " WHERE trainer.Username = ?1", nativeQuery = true)
	public Page<Assignment> getAssUsername(String username, Pageable pageable);
	
	
	
	@Query(value = "SELECT * FROM assignment"
			+ " LEFT JOIN class  ON assignment.ClassID=class.ClassID "
			+ " LEFT JOIN trainer  ON assignment.TrainerID=trainer.Username"
			+ " LEFT JOIN module  ON assignment.ModuleID=module.ModuleID"
			+ " WHERE assignment.TrainerID =?1"
			+ " AND (CONCAT(module.ModuleName,class.ClassName,assignment.RegistrationCode,trainer.Name) LIKE %?2%)", nativeQuery = true)
	public Page<Assignment> getAssUsernameFind(String username, String fin, Pageable pageable);


	@Query(value = "SELECT *FROM assignment"
			+ " LEFT JOIN class  ON assignment.ClassID=class.ClassID "
			+ " LEFT JOIN trainer  ON assignment.TrainerID=trainer.Username"
			+ " LEFT JOIN module  ON assignment.ModuleID=module.ModuleID"
			+ " WHERE CONCAT(module.ModuleName, ' ', class.ClassName, ' ', trainer.Name , ' ', assignment.RegistrationCode) LIKE %?1%", nativeQuery = true)
		
	public Page<Assignment> search(String username, Pageable pageable);



	@Query(value = "SELECT * FROM assignment WHERE ClassID=?1 AND ModuleID=?2 AND TrainerID=?3", nativeQuery = true)
	public List<Assignment> findMoIDcLIDtRID(Integer ClassID,Integer ModuleID,String TrainerID);

	
		@Query(value = "SELECT * FROM assignment where assignment.registrationCode = ?1", nativeQuery = true)
	public Assignment findOneByRegistrationCode(String RegistrationCode);
	
}
