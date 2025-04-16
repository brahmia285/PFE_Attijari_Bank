package com.satisfaction.service;

import com.satisfaction.dto.QuestionDto;
import com.satisfaction.entity.Form;
import com.satisfaction.entity.Question;
import com.satisfaction.entity.QuestionType;
import com.satisfaction.exception.ResourceNotFoundException;
import com.satisfaction.repository.FormRepository;
import com.satisfaction.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final FormRepository formRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, FormRepository formRepository) {
        this.questionRepository = questionRepository;
        this.formRepository = formRepository;
    }

    public List<QuestionDto> getQuestionsByFormId(Long formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        return questionRepository.findByForm(form).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public QuestionDto getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));
        return convertToDto(question);
    }

    public QuestionDto createQuestion(QuestionDto questionDto) {
        Form form = formRepository.findById(questionDto.getFormId())
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + questionDto.getFormId()));

        Question question = new Question();
        question.setText(questionDto.getText());
        question.setType(QuestionType.valueOf(questionDto.getType().toUpperCase()));
        question.setRequired(questionDto.isRequired());
        question.setOptions(String.join(",", questionDto.getOptions())); // List -> String
        question.setOrderIndex(questionDto.getOrderIndex());
        question.setForm(form);

        Question savedQuestion = questionRepository.save(question);
        return convertToDto(savedQuestion);
    }

    public QuestionDto updateQuestion(Long id, QuestionDto questionDto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));

        question.setText(questionDto.getText());
        question.setType(QuestionType.valueOf(questionDto.getType().toUpperCase()));
        question.setRequired(questionDto.isRequired());
        question.setOptions(String.join(",", questionDto.getOptions())); // List -> String
        question.setOrderIndex(questionDto.getOrderIndex());

        Question updatedQuestion = questionRepository.save(question);
        return convertToDto(updatedQuestion);
    }

    @Transactional
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id: " + id);
        }
        questionRepository.deleteById(id);
    }

    private QuestionDto convertToDto(Question question) {
        QuestionDto dto = new QuestionDto();
        dto.setId(question.getId());
        dto.setText(question.getText());
        dto.setType(question.getType().name());
        dto.setRequired(question.isRequired());
        dto.setOptions(question.getOptions() != null ?
                Arrays.asList(question.getOptions().split(",")) :
                List.of()); // String -> List
        dto.setOrderIndex(question.getOrderIndex());
        dto.setFormId(question.getForm().getId());
        return dto;
    }
}
