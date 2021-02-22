package com.system.bibliotec.repository;

import com.system.bibliotec.model.SystemError;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SystemRepository
 */
@Repository
public interface SystemRepository extends JpaRepository<SystemError, Long> {


}