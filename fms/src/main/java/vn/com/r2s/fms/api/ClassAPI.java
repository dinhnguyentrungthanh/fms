package vn.com.r2s.fms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.com.r2s.fms.entity.Class;
import vn.com.r2s.fms.service.ClassService;

@RestController
public class ClassAPI {
	@Autowired
	private ClassService ClassService;
	
	@GetMapping(value = "/api/class/comment")
	public List<Class> findAll(){
		return ClassService.findAll();
	}
	
	@GetMapping(value="/api/class/comment/search/{className}")
	public List<Class> findAllClassName(@PathVariable("className") String className){
		return ClassService.findAllByClassName(className);
	}
	@GetMapping(value="/api/class/comment/ClassID/{classID}")
	public Class findOne(@PathVariable("classID") Integer classID){
		return  ClassService.findOne(classID);
	}
}
