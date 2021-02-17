package vn.com.r2s.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	 @Query(value = "SELECT * FROM user p WHERE CONCAT(p.id, ' ',p.name, ' ', p.email, ' ', p.phone) LIKE %?1%", nativeQuery = true)
	 public List<User> search(String pageable);

	 
	
	 
}
