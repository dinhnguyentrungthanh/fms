package vn.com.r2s.fms.service.Impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.r2s.fms.entity.Admin;
import vn.com.r2s.fms.repository.AdminRepository;
import vn.com.r2s.fms.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public Admin checkLogin(String username, String password) {
		// String digest = DigestUtils.md5Hex(password).toUpperCase();
		// return adminRepository.findOneByUsernameAndPassword(username, digest);
		
		return adminRepository.findOneByUsernameAndPassword(username, password);
	}

	@Override
	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}

	@Override
	public Admin findOneByUsername(String username) {
		return adminRepository.findOneByUsername(username);
	}

}
