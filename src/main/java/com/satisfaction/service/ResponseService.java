package com.satisfaction.service;

import com.satisfaction.dto.ResponseDto;
import com.satisfaction.entity.Form;
import com.satisfaction.entity.Response;
import com.satisfaction.exception.ResourceNotFoundException;
import com.satisfaction.repository.FormRepository;
import com.satisfaction.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final FormRepository formRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository, FormRepository formRepository) {
        this.responseRepository = responseRepository;
        this.formRepository = formRepository;
    }

    public List<ResponseDto> getResponsesByFormId(Long formId) {
        return responseRepository.findByFormId(formId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ResponseDto getResponseById(Long id) {
        Response response = responseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Response not found with id: " + id));
        return convertToDto(response);
    }

    @Transactional
    public ResponseDto createResponse(ResponseDto responseDto) {
        Form form = formRepository.findById(responseDto.getFormId())
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + responseDto.getFormId()));

        Response response = new Response();
        response.setForm(form);
        response.setSubmittedAt(LocalDateTime.now());
        response.setAnswers(responseDto.getAnswers());
        response.setClientEmail(responseDto.getClientEmail());
        response.setClientName(responseDto.getClientName());

        Response savedResponse = responseRepository.save(response);
        return convertToDto(savedResponse);
    }


    public long getResponseCountByFormId(Long formId) {
        return responseRepository.countByFormId(formId);
    }

    private ResponseDto convertToDto(Response response) {
        ResponseDto dto = new ResponseDto();
        dto.setId(response.getId());
        dto.setFormId(response.getForm().getId());
        dto.setSubmittedAt(response.getSubmittedAt());
        dto.setAnswers(response.getAnswers());
        dto.setClientEmail(response.getClientEmail());
        dto.setClientName(response.getClientName());
        return dto;
    }
}
