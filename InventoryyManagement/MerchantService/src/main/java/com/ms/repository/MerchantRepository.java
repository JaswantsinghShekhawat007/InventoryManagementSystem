package com.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.entity.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {

}
