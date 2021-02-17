package vn.com.r2s.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.com.r2s.fms.entity.Module;

public interface ModuleService {
	List<Module> getAllModule();

	void saveModule(Module module);

	Optional<Module> findModuleById(Integer ModuleID);

	void deleteModule(Integer ModuleID);

	Page<Module> findPaginated(int pageNo, int pageSize);
	
	Page<Module> findAllByTrainer(String username, int pageNo, int pageSize);
	
	Page<Module> findAllByTrainee(String username, int pageNo, int pageSize);
	
	public List<Module> findAll();
	
	public List<Module> findAllByModuleName(String moduleName);

	public Module findOne(Integer moduleID);
	
	
	List<Object[]> getAll();
}
