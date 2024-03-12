package com.ms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.dto.MerchantDTO;
import com.ms.entity.Merchant;
import com.ms.externalservice.ProductClient;
import com.ms.repository.MerchantRepository;

@Service
public class MerchantServiceImpl implements MerchantService {
	
	private MerchantRepository merchantRepository;
	
	private ProductClient productClient;
	
	@Autowired
	public void setMerchantRepository(MerchantRepository merchantRepository) {
		this.merchantRepository = merchantRepository;
	}

	@Autowired
	public void setProductClient(ProductClient productClient) {
		this.productClient = productClient;
	}

	@Override
	public MerchantDTO registerUser(MerchantDTO merchantDTO) {
		if( merchantRepository.existsById(merchantDTO.getMerchantId()) ) {
			throw new RuntimeException("Merchant with Id "+merchantDTO.getMerchantId()+" already exist");
		}
		
		Merchant merchant = new Merchant();
		
		BeanUtils.copyProperties(merchantDTO, merchant);
		
		merchantRepository.save(merchant);
		
		return merchantDTO;
	}



	@Override
	public MerchantDTO getUserById(String userId) {
		
		MerchantDTO merchantDTO = new MerchantDTO();
		
		Merchant merchant = merchantRepository.findById(userId).orElseThrow( () -> new RuntimeException("User with "+userId+" not found"));
		
		merchant.setProducts(productClient.getProductsOfUser(merchant.getMerchantId()));
		
		BeanUtils.copyProperties(merchant, merchantDTO);
		
		return merchantDTO;
	}

	@Override
	public List<Merchant> getAllUser() {
		
		List<Merchant> merchants = merchantRepository.findAll();
		
//		List<MerchantDTO> merchantDTOs = new ArrayList<>();

		List<Merchant> newMerchants = merchants.stream().map( (merchant) -> {
			merchant.setProducts(productClient.getProductsOfUser(merchant.getMerchantId()));
			return merchant;
		}).collect(Collectors.toList());
		
//		for(Merchant merchant: newMerchants) {
//			MerchantDTO merchantDTO = new MerchantDTO();
//			BeanUtils.copyProperties(merchant, merchantDTO);
//			merchantDTOs.add(merchantDTO);
//		}
		
		return newMerchants;
	}

	@Override
	public MerchantDTO updateUser(String userId, MerchantDTO merchantDTO) {
		
		Merchant merchant = merchantRepository.findById(userId).orElseThrow( () -> new RuntimeException("User with "+userId+" not found"));

		merchant.setName(merchantDTO.getName());
		merchant.setContactNo(merchantDTO.getContactNo());
		merchant.setAddress(merchantDTO.getAddress());
		
		merchantRepository.save(merchant);
		
		return merchantDTO;
	}

	@Override
	public MerchantDTO deleteUser(String userId) {
		
		Merchant merchant = merchantRepository.findById(userId).orElseThrow( () -> new RuntimeException("User with "+userId+" not found"));
		
		MerchantDTO merchantDTO = new MerchantDTO();
		
		BeanUtils.copyProperties(merchant, merchantDTO);
		
		merchantRepository.delete(merchant);
		
		return merchantDTO;
	}

}
