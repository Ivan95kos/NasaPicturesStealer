package com.example.nasapicturesstealer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class PictureDto {
    private Long id;
    @JsonProperty("img_src")
    private String imgSrc;
    private CameraDto camera;
}
