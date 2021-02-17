package vn.com.r2s.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.com.r2s.fms.entity.Class;

public interface ClassService {
	
	List<Class> getAllClass();
	
	void saveClass(Class class1);
		
	void deleteClass(Integer classID);
		
	Page<Class> findPaginated(int pageNo, int pageSize);

	Optional<Class> findClassById(Integer classID);
	
	Class checkJoined(String registrationCode, String traineeID);

	public Page<Object[]> findAllByTrainer(String username, int pageNo, int pageSize);
		
	public Page<Object[]> findAllByClass(Integer classID, int pageNo, int pageSize);
	
	public Page<Object[]> findAllByTrainee(String username, int pageNo, int pageSize);
	
	public Page<Object[]> findAllByClassList(Integer classID, int pageNo, int pageSize);
	
	List<Class> findAll();
	
	List<Class> findAllByClassName(String className);

    Class findOne(Integer classID);	
}

