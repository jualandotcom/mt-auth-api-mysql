package com.mt.auth.api.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mt.auth.api.db.entity.TbAuth;

public interface TbAuthRepository extends JpaRepository<TbAuth, Integer> {
	
}