package com.project.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientErrorResponse {
    private String message;
    private Long timestamp;
}
