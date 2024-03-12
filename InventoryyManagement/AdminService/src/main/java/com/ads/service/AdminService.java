package com.ads.service;

import java.util.List;

import com.ads.dto.AdminDTO;

public interface AdminService {
	
	AdminDTO addAdmin(AdminDTO adminDTO);
	
	AdminDTO getAdminById(String id);
	
	List<AdminDTO> getAllAdmin();
	
	AdminDTO updateAdmin(String id, AdminDTO AdminDTO);
	
	AdminDTO deleteAdmin(String id);	
	
}
