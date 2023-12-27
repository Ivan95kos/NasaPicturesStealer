package com.example.nasapicturesstealer.mapper;

import com.example.nasapicturesstealer.config.CommonMapperConfig;
import com.example.nasapicturesstealer.dto.PictureDto;
import com.example.nasapicturesstealer.model.Picture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class, uses = CameraMapper.class)
public interface PictureMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nasaId", source = "id")
    Picture map(PictureDto pictures);

}
