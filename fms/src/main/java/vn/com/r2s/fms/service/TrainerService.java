package vn.com.r2s.fms.service;

import java.util.List;

import vn.com.r2s.fms.entity.Trainer;

public interface TrainerService {

	public Trainer checkLogin(String username, String password);
	List<Trainer> getAllTrainer();
		Trainer findOne(String trainerID);
}
