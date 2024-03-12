package com.ads.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.dto.AdminDTO;
import com.ads.entity.Admin;
import com.ads.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	private AdminRepository adminRepository;
	
	@Autowired
	public void setAdminRepository(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public AdminDTO addAdmin(AdminDTO adminDTO) {
		
		Admin admin = new Admin();
		BeanUtils.copyProperties(adminDTO, admin);
		
		adminRepository.save(admin);
		
		return adminDTO;
	}

	@Override
	public AdminDTO getAdminById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminDTO> getAllAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminDTO updateAdmin(String id, AdminDTO AdminDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminDTO deleteAdmin(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
