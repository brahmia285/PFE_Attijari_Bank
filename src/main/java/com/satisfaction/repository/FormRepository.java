package com.satisfaction.repository;

import com.satisfaction.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.satisfaction.entity.User;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findByCreatorId(Long creatorId);
    List<Form> findByActive(boolean active);
    List<Form> findByCreator(User creator);
}