package com.capstone.request_service.repository;

import com.capstone.request_service.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity,Integer> {
    List<RequestEntity> findByUsername(String username);
    List<RequestEntity> findByCommunityId(int communityId);
    List<RequestEntity> findByStatus(String status);
}
