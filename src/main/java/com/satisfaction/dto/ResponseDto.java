package com.satisfaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private Long id;
    private Long formId; // Assurez-vous que ce champ existe
    private LocalDateTime submittedAt;
    private Map<String, String> answers; // Assurez-vous que c'est bien un Map
    private String clientEmail;
    private String clientName;
}