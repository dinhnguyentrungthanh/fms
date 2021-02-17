package vn.com.r2s.fms.service;

import vn.com.r2s.fms.entity.Trainee;

public interface TraineeService {

	public Trainee checkLogin(String username, String password);
	Trainee findOne(String traineeID);
}
