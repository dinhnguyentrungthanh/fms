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

import vn.com.r2s.fms.entity.FeedbackQuestion;
import vn.com.r2s.fms.service.FeedbackQuesionService;
import vn.com.r2s.fms.service.Impl.ServiceResult;

@RestController
@RequestMapping("/api/v1")
public class FeedbackQuestionAPI {

	@Autowired
	private FeedbackQuesionService feedbackQuesionService;
	
	/* ---------------- GET ALL FEEDBACKS QS ------------------------ */	
	@GetMapping("/fb_question")
	public ResponseEntity<ServiceResult> findAllFeedbackQuestion() {
		return new ResponseEntity<ServiceResult>(feedbackQuesionService.findAll(), HttpStatus.OK);
	}
	
	/* ---------------- GET FEEDBACK QS BY ID ------------------------ */
	@GetMapping("/fb_question/{id}")
	public ResponseEntity<ServiceResult> findById(@PathVariable int id) {
		return new ResponseEntity<ServiceResult>(feedbackQuesionService.findById(id), HttpStatus.OK);
	}
	
	/* ---------------- CREATE NEW FEEDBACK QS ------------------------ */
	@PostMapping(value="/fb_question",consumes={"application/json"})
	public ResponseEntity<ServiceResult> create(@RequestBody FeedbackQuestion feedbackQuestion) {
		return new ResponseEntity<ServiceResult>(feedbackQuesionService.create(feedbackQuestion), HttpStatus.OK);
	}
	
	/* ---------------- UPDATE FEEDBACK QS ------------------------ */
	@PutMapping("/fb_question")
	public ResponseEntity<ServiceResult> update(@RequestBody FeedbackQuestion feedbackQuestion) {
		return new ResponseEntity<ServiceResult>(feedbackQuesionService.update(feedbackQuestion), HttpStatus.OK);
	}
	
	/* ---------------- DELETE FEEDBACK QS ------------------------ */
	@DeleteMapping("/fb_question")
	public ResponseEntity<ServiceResult> delete(@RequestBody DeleteFbQuestionRequest request) {
		return new ResponseEntity<ServiceResult>(feedbackQuesionService.delete(request.getFbQuestionID()), HttpStatus.OK);
	}
}
