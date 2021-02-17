package vn.com.r2s.fms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.Class;
import vn.com.r2s.fms.entity.Enrollment;
import vn.com.r2s.fms.entity.EnrollmentID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentID> {
	
	  @Query(value = "SELECT * FROM enrollment q left join class t  on q.ClassID=t.ClassID where concat(t.ClassName,'') LIKE %?1%", nativeQuery = true)
		 public List<Enrollment> search(String pageable);


	public Enrollment findOneByEnrollmentID(EnrollmentID enrollmentID);

	public List<Enrollment> findAllByClassID(Class classes);


}
