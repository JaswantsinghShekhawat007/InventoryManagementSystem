package com.ads.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.dto.AdminDTO;
import com.ads.entity.Admin;
import com.ads.exception.AdminNotFoundException;
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
		
		Admin admin = adminRepository.findById(id)
						.orElseThrow( () -> new AdminNotFoundException("Admin With Id "+id+" Does Not Exist") ); 
		
		AdminDTO adminDTO = new AdminDTO();
		
		BeanUtils.copyProperties(admin, adminDTO);
		
		return adminDTO;
	}

	@Override
	public List<AdminDTO> getAllAdmin() {
		
		List<Admin> admins = adminRepository.findAll();
		
		List<AdminDTO> adminDTOs = new ArrayList<>();
		
		for(Admin admin: admins) {
			AdminDTO adminDTO = new AdminDTO();
			BeanUtils.copyProperties(admin, adminDTO);
			adminDTOs.add(adminDTO);
		}
		
		return adminDTOs;
	}

	@Override
	public AdminDTO updateAdmin(String id, AdminDTO adminDTO) {
		Admin admin = adminRepository.findById(id)
				.orElseThrow( () -> new AdminNotFoundException("Admin With Id "+id+" Does Not Exist") ); 

		admin.setName(adminDTO.getName());
		admin.setContactNo(adminDTO.getContactNo());
		
	 	if (adminDTO.getName() != null) {
            admin.setName(adminDTO.getName());
        }
	 	else {
	 		String name = admin.getName();
	 		admin.setName(name);
	 	}
	 	
	 	if (adminDTO.getContactNo() != null) {
            admin.setContactNo(adminDTO.getContactNo());
        }
	 	else {
	 		String no = admin.getContactNo();
	 		admin.setContactNo(no);
	 	}
		
		adminRepository.save(admin);
		
		return adminDTO;
	}

	@Override
	public AdminDTO deleteAdmin(String id) {
		Admin admin = adminRepository.findById(id)
				.orElseThrow( () -> new AdminNotFoundException("Admin With Id "+id+" Does Not Exist") ); 
		
		AdminDTO adminDTO = new AdminDTO();
		
		BeanUtils.copyProperties(admin, adminDTO);
		
		adminRepository.delete(admin);
		
		return adminDTO;
	}

}
