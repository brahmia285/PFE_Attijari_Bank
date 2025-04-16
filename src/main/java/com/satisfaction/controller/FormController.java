package com.satisfaction.controller;

import com.satisfaction.dto.FormDto;
import com.satisfaction.service.FormService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Getter
@Setter
@RestController
@RequestMapping("/api/forms")
@CrossOrigin(origins = "*")
public class FormController {

    public final FormService formService;

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping
    public ResponseEntity<List<FormDto>> getAllForms() {
        List<FormDto> forms = formService.getAllForms();
        return ResponseEntity.ok(forms);
    }

    @GetMapping("/active")
    public ResponseEntity<List<FormDto>> getActiveFormsForClients() {
        List<FormDto> forms = formService.getActiveFormsForClients();
        return ResponseEntity.ok(forms);
    }

    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<FormDto>> getFormsByCreator(@PathVariable Long creatorId) {
        List<FormDto> forms = formService.getFormsByCreator(creatorId);
        return ResponseEntity.ok(forms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormDto> getFormById(@PathVariable Long id) {
        FormDto form = formService.getFormById(id);
        return ResponseEntity.ok(form);
    }

    @PostMapping
    public ResponseEntity<FormDto> createForm(@RequestBody FormDto formDto) {
        FormDto createdForm = formService.createForm(formDto);
        return new ResponseEntity<>(createdForm, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormDto> updateForm(@PathVariable Long id, @RequestBody FormDto formDto) {
        FormDto updatedForm = formService.updateForm(id, formDto);
        return ResponseEntity.ok(updatedForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
        return ResponseEntity.noContent().build();
    }
}