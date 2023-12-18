package com.example.nasapicturesstealer.service.impl;

import com.example.nasapicturesstealer.dto.NasaRequestDto;
import com.example.nasapicturesstealer.dto.PhotosDto;
import com.example.nasapicturesstealer.mapper.PictureMapper;
import com.example.nasapicturesstealer.model.Picture;
import com.example.nasapicturesstealer.repository.CameraRepository;
import com.example.nasapicturesstealer.service.NasaService;
import com.example.nasapicturesstealer.utils.NasaParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NasaServiceImpl implements NasaService {

    @Value("${nasa.url}")
    private String url;

    @Value("${nasa.apiKey}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final PictureMapper pictureMapper;
    private final CameraRepository cameraRepository;

    @Override
    public void stealPictures(NasaRequestDto nasaRequestDto) {
        var uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(NasaParameter.PARAM_SOL, nasaRequestDto.getSol())
                .queryParam(NasaParameter.PARAM_API_KEY, apiKey)
                .build().toUri();

        var photosDto = restTemplate.getForObject(uri, PhotosDto.class);

        var pictures = photosDto.getPhotos().stream()
                .map(pictureMapper::map)
                .collect(Collectors.groupingBy(Picture::getCamera, Collectors.toList()));

        var cameras = pictures.entrySet().stream()
                .peek(cameraListEntry -> {
                    cameraListEntry.getKey().setPictures(cameraListEntry.getValue());
                    cameraListEntry.getValue().forEach(picture -> picture.setCamera(cameraListEntry.getKey()));
                })
                .map(Map.Entry::getKey)
                .toList();

        cameraRepository.saveAll(cameras);
    }
}
