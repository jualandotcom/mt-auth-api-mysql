package com.mt.member.api.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mt.member.api.db.entity.TbAuth;

public interface TbAuthRepository extends JpaRepository<TbAuth, Integer> {
	
}