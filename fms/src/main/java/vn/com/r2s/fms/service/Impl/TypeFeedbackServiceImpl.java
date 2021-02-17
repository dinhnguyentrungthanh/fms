package vn.com.r2s.fms.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.TypeFeedback;
import vn.com.r2s.fms.repository.TypeFeedbackRepository;
import vn.com.r2s.fms.service.TypeFeedbackService;

@Service
public class TypeFeedbackServiceImpl implements TypeFeedbackService {

	@Autowired
	private TypeFeedbackRepository typeFeedbackRepository;

	@Override
	public TypeFeedback save(TypeFeedback entity) {
		return typeFeedbackRepository.save(entity);
	}

	@Override
	public Optional<TypeFeedback> findOne(Example<TypeFeedback> example) {
		return typeFeedbackRepository.findOne(example);
	}

	@Override
	public Page<TypeFeedback> findAll(Pageable pageable) {
		return typeFeedbackRepository.findAll(pageable);
	}

	@Override
	public List<TypeFeedback> findAll() {
		return typeFeedbackRepository.findAll();
	}

	@Override
	public List<TypeFeedback> findAll(Sort sort) {
		return typeFeedbackRepository.findAll(sort);
	}

	@Override
	public List<TypeFeedback> findAllById(Iterable<Integer> ids) {
		return typeFeedbackRepository.findAllById(ids);
	}

	@Override
	public List<TypeFeedback> saveAll(Iterable<TypeFeedback> entities) {
		return typeFeedbackRepository.saveAll(entities);
	}

	@Override
	public Optional<TypeFeedback> findById(Integer id) {
		return typeFeedbackRepository.findById(id);
	}

	@Override
	public void flush() {
		typeFeedbackRepository.flush();
	}

	@Override
	public TypeFeedback saveAndFlush(TypeFeedback entity) {
		return typeFeedbackRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return typeFeedbackRepository.existsById(id);
	}

	@Override
	public void deleteInBatch(Iterable<TypeFeedback> entities) {
		typeFeedbackRepository.deleteInBatch(entities);
	}

	@Override
	public Page<TypeFeedback> findAll(Example<TypeFeedback> example, Pageable pageable) {
		return typeFeedbackRepository.findAll(example, pageable);
	}

	@Override
	public void deleteAllInBatch() {
		typeFeedbackRepository.deleteAllInBatch();
	}

	@Override
	public TypeFeedback getOne(Integer id) {
		return typeFeedbackRepository.getOne(id);
	}

	@Override
	public List<TypeFeedback> findAll(Example<TypeFeedback> example) {
		return typeFeedbackRepository.findAll(example);
	}

	@Override
	public long count() {
		return typeFeedbackRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		typeFeedbackRepository.deleteById(id);
	}

	@Override
	public List<TypeFeedback> findAll(Example<TypeFeedback> example, Sort sort) {
		return typeFeedbackRepository.findAll(example, sort);
	}

	@Override
	public void delete(TypeFeedback entity) {
		typeFeedbackRepository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends TypeFeedback> entities) {
		typeFeedbackRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		typeFeedbackRepository.deleteAll();
	}
	
	@Override
	public List<TypeFeedback> getAllTypeFeedback() {
		return typeFeedbackRepository.findAll();
	}

}