package com.satisfaction.repository;

import com.satisfaction.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByFormId(Long formId);
    long countByFormId(Long formId);
}