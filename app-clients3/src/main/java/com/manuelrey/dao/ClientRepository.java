package com.manuelrey.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manuelrey.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	public List<Client> findByEmail(String n);
	public Page<Client> findByEmail(String n, Pageable pageable);
	@Query("select c from Client c where c.email like:x")
	public Page<Client> SearchClients(@Param("x")String se, Pageable pageable);
	

}
