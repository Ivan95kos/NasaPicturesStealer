package com.example.nasapicturesstealer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class CameraDto {
    private Long id;
    private String name;
}
