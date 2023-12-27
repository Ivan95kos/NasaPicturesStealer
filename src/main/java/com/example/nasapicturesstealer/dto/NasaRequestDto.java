package com.example.nasapicturesstealer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NasaRequestDto {
    @NotNull
    private Integer sol;
}
