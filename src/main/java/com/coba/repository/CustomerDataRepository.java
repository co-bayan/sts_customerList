package com.coba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coba.CustomerData;

public interface CustomerDataRepository extends JpaRepository<CustomerData, Long> {

	public Optional<CustomerData> findById(long id);
}
