package vn.com.r2s.fms.service.Impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.Trainer;
import vn.com.r2s.fms.repository.TrainerRepository;
import vn.com.r2s.fms.service.TrainerService;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;

	@Override
	public Trainer checkLogin(String username, String password) {
	    // String digest = DigestUtils.md5Hex(password).toUpperCase();
		// return trainerRepository.findOneByUsernameAndPassword(username, digest);
		return trainerRepository.findOneByUsernameAndPassword(username, password);
	}

	@Override
	public List<Trainer> getAllTrainer() {

		return (List<Trainer>) trainerRepository.findAll();
	}
	@Override
	public Trainer findOne(String trainerID) {
		
		return trainerRepository.getOne(trainerID);
	}
}
