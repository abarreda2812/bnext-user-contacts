package com.orangebank.delivery.preparator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orangebank.delivery.preparator.entity.CpData;

@Repository
public interface CpRepository extends JpaRepository<CpData, String> {

}
