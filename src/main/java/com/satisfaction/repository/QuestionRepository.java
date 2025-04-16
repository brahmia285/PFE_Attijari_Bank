package com.satisfaction.repository;

import com.satisfaction.entity.Form;
import com.satisfaction.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByFormId(Long formId);
    List<Question> findByFormIdOrderByOrderIndexAsc(Long formId);
    List<Question> findByForm(Form form);

}
