package com.ms.service;

import java.util.List;

import com.ms.dto.MerchantDTO;
import com.ms.entity.Merchant;

public interface MerchantService {
	
	MerchantDTO registerUser(MerchantDTO merchantDTO);
	
	MerchantDTO getUserById(String userId);
	
	List<Merchant> getAllUser();
	
	MerchantDTO updateUser(String userId, MerchantDTO merchantDTO);
	
	MerchantDTO deleteUser(String userId);
	
}
