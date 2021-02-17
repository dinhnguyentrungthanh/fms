package vn.com.r2s.fms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.r2s.fms.service.Impl.ServiceResult;
import vn.com.r2s.fms.entity.Feedback;
import vn.com.r2s.fms.service.FeedbackService;

@RestController
@RequestMapping("/api/v1")
public class FeedbackAPI {
	
	@Autowired
	private FeedbackService feedbackService;
	
	/* ---------------- GET ALL FEEDBACKS ------------------------ */	
	@GetMapping("/feedbacks")
	public ResponseEntity<ServiceResult> findAllFeedback() {
		return new ResponseEntity<ServiceResult>(feedbackService.findAlll(), HttpStatus.OK);
	}
	
	/* ---------------- GET FEEDBACK BY ID ------------------------ */
	@GetMapping("/feedbacks/{feedbackID}")
	public ResponseEntity<ServiceResult> findById(@PathVariable int feedbackID) {
		return new ResponseEntity<ServiceResult>(feedbackService.findById(feedbackID), HttpStatus.OK);
	}
	
	/* ---------------- CREATE NEW FEEDBACK ------------------------ */
	@PostMapping("/feedbacks")
	public ResponseEntity<ServiceResult> create(@RequestBody Feedback feedback) {
		return new ResponseEntity<ServiceResult>(feedbackService.create(feedback), HttpStatus.OK);
	}
	
	/* ---------------- UPDATE FEEDBACK ------------------------ */
	@PutMapping("/feedbacks")
	public ResponseEntity<ServiceResult> update(@RequestBody Feedback feedback) {
		return new ResponseEntity<ServiceResult>(feedbackService.update(feedback), HttpStatus.OK);
	}
	
	/* ---------------- DELETE FEEDBACK ------------------------ */
	@DeleteMapping("/feedbacks")
	public ResponseEntity<ServiceResult> delete(@RequestBody DeleteFeedbackRequest request) {
		return new ResponseEntity<ServiceResult>(feedbackService.delete(request.getfeedbackID()), HttpStatus.OK);
	}

}
