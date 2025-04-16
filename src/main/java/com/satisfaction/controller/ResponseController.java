package com.satisfaction.controller;

import com.satisfaction.dto.ResponseDto;
import com.satisfaction.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/responses")
@CrossOrigin(origins = "*")
public class ResponseController {

    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping("/form/{formId}")
    public ResponseEntity<List<ResponseDto>> getResponsesByFormId(@PathVariable Long formId) {
        List<ResponseDto> responses = responseService.getResponsesByFormId(formId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getResponseById(@PathVariable Long id) {
        ResponseDto response = responseService.getResponseById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createResponse(@RequestBody ResponseDto responseDto) {
        ResponseDto createdResponse = responseService.createResponse(responseDto);
        return new ResponseEntity<>(createdResponse, HttpStatus.CREATED);
    }

    @GetMapping("/count/form/{formId}")
    public ResponseEntity<Map<String, Long>> getResponseCountByFormId(@PathVariable Long formId) {
        long count = responseService.getResponseCountByFormId(formId);
        return ResponseEntity.ok(Map.of("count", count));
    }
}
