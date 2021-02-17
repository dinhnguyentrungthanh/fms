package vn.com.r2s.fms.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.Answer;
import vn.com.r2s.fms.entity.AnswerID;
import vn.com.r2s.fms.repository.AnswerIDRepository;
import vn.com.r2s.fms.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerIDRepository answerIDRepository;

	@Override
	public Answer saveAS(Answer answerID) {
		return answerIDRepository.save(answerID);
	}
	
	@Override
	public Optional<Answer> findAnswerByModuleID(Integer moduleID, Integer classID) {
	    AnswerID answerID = new AnswerID();
	    answerID.setModuleID(moduleID);
	    answerID.setClassID(classID);
	    
		return answerIDRepository.findById(answerID);
	}
	
	@Override
	public List<Answer> findAll() {
		return answerIDRepository.findAll();
	}
}

