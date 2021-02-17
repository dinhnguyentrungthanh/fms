package vn.com.r2s.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, String> {

	public Trainer findOneByUsernameAndPassword(String username, String password);
	
}
