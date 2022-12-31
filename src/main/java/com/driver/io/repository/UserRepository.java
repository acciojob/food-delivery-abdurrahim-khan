package com.driver.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.driver.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findByEmail(String email) throws Exception;
	UserEntity findByUserId(String userId) throws Exception;
	boolean existsByEmail(String email) throws Exception;
	boolean existsByUserId(String userId) throws Exception;

}
