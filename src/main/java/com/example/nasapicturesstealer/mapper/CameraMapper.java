package com.example.nasapicturesstealer.mapper;

import com.example.nasapicturesstealer.config.CommonMapperConfig;
import com.example.nasapicturesstealer.dto.CameraDto;
import com.example.nasapicturesstealer.model.Camera;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class)
public interface CameraMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nasaId", source = "id")
    Camera map(CameraDto cameraDto);

}
