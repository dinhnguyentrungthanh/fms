package vn.com.r2s.fms.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.Class;
import vn.com.r2s.fms.repository.ClassRepository;
import vn.com.r2s.fms.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService{
	
	@Autowired ClassRepository classRepository;
	
	@Override
	public Optional<Class> findClassById(Integer classID) {
		return classRepository.findById(classID);
	}
	
	@Override
	public Page<Class> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		return classRepository.findAllByIsDeleted(pageable);
	}

	@Override
	public List<Class> getAllClass() {
		return classRepository.getAllClass();
		
	}

	@Override
	public void saveClass(Class class1) {
		classRepository.save(class1);
	}

	@Override
	public void deleteClass(Integer classID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Object[]> findAllByTrainer(String username, int pageNo, int pageSize) {
		Page<Object[]> page = classRepository.findAllByTrainer(username, PageRequest.of(pageNo-1, pageSize));		
		 return page;
	}

	@Override
	public Page<Object[]> findAllByClass(Integer classID, int pageNo, int pageSize) {
		Page<Object[]> page = classRepository.findAllByClass(classID, PageRequest.of(pageNo-1, pageSize));		
		 return page;
	}
	
	@Override
	public Page<Object[]> findAllByTrainee(String username, int pageNo, int pageSize) {
		Page<Object[]> page = classRepository.findAllByTrainee(username, PageRequest.of(pageNo-1, pageSize));		
		 return page;
	}

	@Override
	public Page<Object[]> findAllByClassList(Integer classID, int pageNo, int pageSize) {
		Page<Object[]> page = classRepository.findAllByClassList(classID, PageRequest.of(pageNo-1, pageSize));		
		 return page;
	}
	
	@Override
	public Class checkJoined(String registrationCode, String traineeID) {
		return classRepository.checkJoined(registrationCode, traineeID);
	}
	
	@Override
	public List<Class> findAll(){
		return  classRepository.findAll();
	}
	
	@Override
	public List<Class> findAllByClassName(String className) {
		return classRepository.findAllByClassName(className);
	}

	@Override
	public Class findOne(Integer classID) {
		return classRepository.getOne(classID);
	}

}
