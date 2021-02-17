package vn.com.r2s.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.r2s.fms.entity.Answer;
import vn.com.r2s.fms.entity.AnswerID;

@Repository
public interface AnswerIDRepository extends JpaRepository<Answer,AnswerID>{

}
