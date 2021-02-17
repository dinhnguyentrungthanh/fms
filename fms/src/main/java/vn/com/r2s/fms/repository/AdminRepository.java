package vn.com.r2s.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

	public Admin findOneByUsernameAndPassword(String username, String password);
	
	public Admin findOneByUsername(String username);
	
}
