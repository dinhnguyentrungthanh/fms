package vn.com.r2s.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.com.r2s.fms.entity.TypeFeedback;

public interface TypeFeedbackService {

	void deleteAll();

	void deleteAll(Iterable<? extends TypeFeedback> entities);

	void delete(TypeFeedback entity);

	List<TypeFeedback> findAll(Example<TypeFeedback> example, Sort sort);

	void deleteById(Integer id);

	long count();

	List<TypeFeedback> findAll(Example<TypeFeedback> example);

	TypeFeedback getOne(Integer id);

	void deleteAllInBatch();

	Page<TypeFeedback> findAll(Example<TypeFeedback> example, Pageable pageable);

	void deleteInBatch(Iterable<TypeFeedback> entities);

	boolean existsById(Integer id);

	TypeFeedback saveAndFlush(TypeFeedback entity);

	void flush();

	Optional<TypeFeedback> findById(Integer id);

	List<TypeFeedback> saveAll(Iterable<TypeFeedback> entities);

	List<TypeFeedback> findAllById(Iterable<Integer> ids);

	List<TypeFeedback> findAll(Sort sort);

	List<TypeFeedback> findAll();

	Page<TypeFeedback> findAll(Pageable pageable);

	Optional<TypeFeedback> findOne(Example<TypeFeedback> example);

	TypeFeedback save(TypeFeedback entity);

	List<TypeFeedback> getAllTypeFeedback();
	
}

