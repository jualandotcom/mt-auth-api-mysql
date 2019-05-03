package com.mt.member.api.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mt.member.api.db.entity.TbUser;

public interface TbUserRepository extends JpaRepository<TbUser, Integer> {
	
}