package vn.com.r2s.fms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.r2s.fms.entity.TraineeComment;
import vn.com.r2s.fms.service.TraineeCommentService;

@RestController
public class TraineeCommentAPI {
	@Autowired
	private TraineeCommentService traineeCommentService;

	@GetMapping(value = "/api/comment/search")
	public List<TraineeComment> findClassIDPaginated(@RequestParam(value ="classID", required = false) Integer classID){
		return traineeCommentService.findOneClassID(classID);
	}
	
	@GetMapping(value = "/api/comment/search1")
	public List<TraineeComment> findModuleIDPaginated(@RequestParam(value ="moduleID", required = false) Integer moduleID){
		return traineeCommentService.findOneModuleID(moduleID);
	}
}
