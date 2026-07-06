package com.vaishnavi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vaishnavi.demo.entity.Citizen;
import java.util.List;

public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

    long countByStatus(String status);

    long countByPriority(String priority);
    
    List<Citizen> findByStatus(String status);

    List<Citizen> findByPriority(String priority);

    List<Citizen> findByCategory(String category);
    
    List<Citizen> findByEmail(String email);
    
    
}