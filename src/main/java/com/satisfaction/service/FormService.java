package com.satisfaction.service;

import com.satisfaction.dto.FormDto;
import com.satisfaction.entity.Form;
import com.satisfaction.entity.User;
import com.satisfaction.exception.ResourceNotFoundException;
import com.satisfaction.repository.FormRepository;
import com.satisfaction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormService {

    public final FormRepository formRepository;
    public final UserRepository userRepository;
    public final QuestionService questionService;

    @Autowired
    public FormService(FormRepository formRepository, UserRepository userRepository, QuestionService questionService) {
        this.formRepository = formRepository;
        this.userRepository = userRepository;
        this.questionService = questionService;
    }

    public List<FormDto> getAllForms() {
        return formRepository.findAll().stream()
                .map(this::convertToDtoWithoutQuestions)
                .collect(Collectors.toList());
    }

    public List<FormDto> getActiveFormsForClients() {
        return formRepository.findByActive(true).stream()
                .map(this::convertToDtoWithoutQuestions)
                .collect(Collectors.toList());
    }

    public List<FormDto> getFormsByCreator(Long creatorId) {
        User creator = userRepository.findById(creatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Creator not found with id: " + creatorId));

        return formRepository.findByCreator(creator).stream()
                .map(this::convertToDtoWithoutQuestions)
                .collect(Collectors.toList());
    }

    public FormDto getFormById(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + id));
        return convertToDtoWithoutQuestions(form);
    }

    public FormDto createForm(FormDto formDto) {
        User creator = userRepository.findById(formDto.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException("Creator not found with id: " + formDto.getCreatorId()));

        Form form = new Form();
        form.setTitle(formDto.getTitle());
        form.setDescription(formDto.getDescription());
        form.setCreatedAt(LocalDateTime.now());
        form.setExpiresAt(formDto.getExpiresAt());
        form.setActive(formDto.isActive());
        form.setCreator(creator);

        Form savedForm = formRepository.save(form);
        return convertToDtoWithoutQuestions(savedForm);
    }

    public FormDto updateForm(Long id, FormDto formDto) {
        Form existingForm = formRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + id));

        existingForm.setTitle(formDto.getTitle());
        existingForm.setDescription(formDto.getDescription());
        existingForm.setExpiresAt(formDto.getExpiresAt());
        existingForm.setActive(formDto.isActive());

        Form updatedForm = formRepository.save(existingForm);
        return convertToDtoWithoutQuestions(updatedForm);
    }

    @Transactional
    public void deleteForm(Long id) {
        if (!formRepository.existsById(id)) {
            throw new ResourceNotFoundException("Form not found with id: " + id);
        }
        formRepository.deleteById(id);
    }

    private FormDto convertToDtoWithoutQuestions(Form form) {
        FormDto dto = new FormDto();
        dto.setId(form.getId());
        dto.setTitle(form.getTitle());
        dto.setDescription(form.getDescription());
        dto.setCreatedAt(form.getCreatedAt());
        dto.setExpiresAt(form.getExpiresAt());
        dto.setActive(form.isActive());
        dto.setCreatorId(form.getCreator().getId());
        return dto;
    }
}
