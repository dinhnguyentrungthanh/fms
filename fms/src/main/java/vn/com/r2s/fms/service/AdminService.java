package vn.com.r2s.fms.service;

import java.util.List;

import vn.com.r2s.fms.entity.Admin;

public interface AdminService {

	List<Admin> getAllAdmin();

	public Admin checkLogin(String username, String password);
	
	public Admin findOneByUsername(String username);
	
}
