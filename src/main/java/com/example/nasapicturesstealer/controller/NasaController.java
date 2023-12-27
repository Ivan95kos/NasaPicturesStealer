package com.example.nasapicturesstealer.controller;

import com.example.nasapicturesstealer.dto.NasaRequestDto;
import com.example.nasapicturesstealer.service.NasaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NasaController {

    private final NasaService nasaService;

    @PostMapping("/pictures/steal")
    public void picturesSteal(@RequestBody @Valid NasaRequestDto nasaRequestDto) {
        nasaService.stealPictures(nasaRequestDto);
    }
}
