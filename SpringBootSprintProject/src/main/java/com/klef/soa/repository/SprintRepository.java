package com.klef.soa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.entity.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>
{
    List<Sprint> findByName(String name);

    List<Sprint> findByType(String type);
}