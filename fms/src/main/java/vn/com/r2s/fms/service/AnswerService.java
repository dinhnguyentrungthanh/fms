package vn.com.r2s.fms.service;

import java.util.List;
import java.util.Optional;

import vn.com.r2s.fms.entity.Answer;

public interface AnswerService {
	Answer saveAS(Answer answerID);

	Optional<Answer> findAnswerByModuleID(Integer moduleID, Integer classID);

	List<Answer> findAll();
}

