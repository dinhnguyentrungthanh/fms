package vn.com.r2s.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, String> {

	public Trainee findOneByUsernameAndPassword(String username, String password);
	
}
