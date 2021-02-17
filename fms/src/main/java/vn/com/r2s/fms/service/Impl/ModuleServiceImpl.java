package vn.com.r2s.fms.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.Module;
import vn.com.r2s.fms.repository.ModuleRepository;
import vn.com.r2s.fms.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	ModuleRepository moduleRepository;

	@Override
	public List<Module> getAllModule() {
		return moduleRepository.getAllModule();
	}

	@Override
	public void saveModule(Module module) {
		moduleRepository.save(module);
	}

	@Override
	public Optional<Module> findModuleById(Integer ModuleID) {
		return moduleRepository.findById(ModuleID);
	}

	@Override
	public void deleteModule(Integer ModuleID) {
		moduleRepository.deleteModule(ModuleID);

	}

	@Override
	public Page<Module> findPaginated(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		return moduleRepository.findAllByIsDeleted(pageable);
	}

	@Override
	public Page<Module> findAllByTrainer(String username, int pageNo, int pageSize) {
		Page<Module> page = moduleRepository.findAllByTrainer(username, PageRequest.of(pageNo - 1, pageSize));
		return page;

	}

	@Override
	public Page<Module> findAllByTrainee(String username, int pageNo, int pageSize) {
		Page<Module> page = moduleRepository.findAllByTrainee(username, PageRequest.of(pageNo - 1, pageSize));
		return page;
	}
	
	@Override
	public List<Module> findAll() {
		return moduleRepository.findAll();
	}
	
	@Override
	public List<Module> findAllByModuleName(String moduleName) {
		return moduleRepository.findAllByModuleName(moduleName);
	}

	@Override
	public Module findOne(Integer moduleID) {
		return moduleRepository.findOneByModuleID(moduleID);
	}
	
	
	@Override
	public List<Object[]> getAll() {
		return moduleRepository.getAll();
	}

}
