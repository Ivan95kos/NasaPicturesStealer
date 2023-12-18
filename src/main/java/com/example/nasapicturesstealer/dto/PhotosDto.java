package com.example.nasapicturesstealer.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PhotosDto {
    private List<PictureDto> photos = new ArrayList<>();
}
