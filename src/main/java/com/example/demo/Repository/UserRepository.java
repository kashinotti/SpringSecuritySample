package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.LoginUser;

@Repository
public interface UserRepository extends JpaRepository<LoginUser, Integer> {
	@Query("select t from LoginUser t WHERE t.username = ?1")
	public LoginUser findByUsername(String username);
}
