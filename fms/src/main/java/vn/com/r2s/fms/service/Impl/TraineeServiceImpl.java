package vn.com.r2s.fms.service.Impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.Trainee;
import vn.com.r2s.fms.repository.TraineeRepository;
import vn.com.r2s.fms.service.TraineeService;

@Service
public class TraineeServiceImpl implements TraineeService {

	@Autowired
	private TraineeRepository traineeRepository;
	
	@Override
	public Trainee checkLogin(String username, String password) {
		// String digest = DigestUtils.md5Hex(password).toUpperCase();
		// return traineeRepository.findOneByUsernameAndPassword(username, digest);
		
		return traineeRepository.findOneByUsernameAndPassword(username, password);
	}
	@Override
	public Trainee findOne(String traineeID) {
		return traineeRepository.getOne(traineeID);
	}

}
