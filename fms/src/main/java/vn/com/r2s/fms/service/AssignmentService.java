package vn.com.r2s.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.com.r2s.fms.entity.Assignment;

public interface AssignmentService {
	List<Assignment> getAllAss();

	void deleteAss(Integer ModuleID, Integer ClassID, String Username);

	Page<Assignment> getAll(int pageNo, int pageSize);

	void saveAss(Assignment ass);

	Optional<Assignment> findAssById(Integer ModuleID, Integer ClassID, String Username);

//
	Page<Assignment> findPaginated(int pageNo, int pageSize);

//
	Page<Assignment> findPaginated1(String username, int pageNo, int pageSize);

//
	Page<Assignment> findPaginatedUsername(String username, int pageNo, int pageSize);

//
	Page<Assignment> findPaginatedFindUsername(String username, String fin, int pageNo, int pageSize);
//
//	public List<Assignment> getAssUsername(String username);
//
//	
	 Assignment findOne(Integer ModuleID, Integer ClassID, String Username);
//
//	 Page<Object[]>  findAss(String username, int pageNo, int pageSize);

	 List<Assignment> findMoIDcLIDtRID(Integer ClassID,Integer ModuleID,String TrainerID);
	 
	 public Assignment findByRegistrationCode(String RegistrationCode);
}
