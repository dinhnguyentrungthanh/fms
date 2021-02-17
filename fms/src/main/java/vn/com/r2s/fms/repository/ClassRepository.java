package vn.com.r2s.fms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
	@Query(value = "SELECT * FROM Class p WHERE IsDeleted = 0", nativeQuery = true)
	public List<Class> getAllClass();

	// sua
	@Query(value = "SELECT * FROM Assignment a join class c on  a.ClassID = c.ClassID join enrollment e on c.ClassID = e.ClassID where a.RegistrationCode = ?1 and e.TraineeID = ?2", nativeQuery = true)
	public Class checkJoined(String registrationCode, String traineeID);

	@Query(value = "SELECT * FROM class p WHERE IsDeleted = 0", nativeQuery = true)
	public Page<Class> findAllByIsDeleted(Pageable pageable);

//	@Query(value = "select c.ClassID, c.ClassName, count(e.ClassID ) "
//			+ "from class c join Assignment a on c.ClassID = a.ClassID join Enrollment e on c.ClassID = e.ClassID "
//			+ "where a.TrainerID = ?1 and c.IsDeleted = 0 " 
//			+ "group by c.ClassID, c.ClassName ", nativeQuery = true)
	
	@Query(value = "select c.ClassID, c.ClassName, count(e.ClassID) "
			+ "from class c join assignment a on c.ClassID = a.ClassID join enrollment e on c.ClassID = e.ClassID "
			+ "where a.TrainerID = ?1 and c.IsDeleted = 0 "
			+ "group by c.ClassID, c.ClassName ", nativeQuery = true)
	public Page<Object[]> findAllByTrainer(String username, Pageable pageable);

	@Query(value = "select class.ClassID, enrollment.TraineeID, trainee.Name " + "from class, enrollment, trainee "
			+ "where class.ClassID = enrollment.ClassID and trainee.UserName = enrollment.TraineeID and class.ClassID = ? and class.IsDeleted = 0", nativeQuery = true)
	public Page<Object[]> findAllByClass(Integer ClassID, Pageable pageable);

	@Query(value = "select c.ClassID, c.ClassName, count(e.ClassID) "
			+ "from class c join enrollment e on c.ClassID = e.ClassID "
			+ "where e.TraineeID = ?  and c.IsDeleted = 0 " + "group by c.ClassID, c.ClassName ", nativeQuery = true)
	public Page<Object[]> findAllByTrainee(String username, Pageable pageable);


	@Query(value = "select class.ClassID, enrollment.TraineeID, trainee.Name " + "from class, enrollment, trainee "
			+ "where class.ClassID = enrollment.ClassID and trainee.UserName = enrollment.TraineeID and class.ClassID = ? and class.IsDeleted = 0", nativeQuery = true)
	public Page<Object[]> findAllByClassList(Integer classid, Pageable pageable);
	
	public List<Class> findAllByClassName(String ClassName);

	public Class findOneByClassID(Integer classID);
}
