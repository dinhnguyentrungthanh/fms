package vn.com.r2s.fms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.com.r2s.fms.entity.Module;
import vn.com.r2s.fms.service.ModuleService;

@RestController
public class ModuleAPI {
	@Autowired
	private ModuleService moduleService;
	
	@GetMapping(value = "/api/module/comment")
	public List<Module> findAll(){
		return moduleService.findAll();
	}
	
	@GetMapping(value="/api/module/comment/search/{moduleName}")
	public List<Module> findAllModuleName(@PathVariable("moduleName") String moduleName){
		return moduleService.findAllByModuleName(moduleName);
	}
	@GetMapping(value="/api/module/comment/ModuleID/{moduleID}")
	public Module findOne(@PathVariable("moduleID") Integer moduleID){
		return  moduleService.findOne(moduleID);
	}
}
