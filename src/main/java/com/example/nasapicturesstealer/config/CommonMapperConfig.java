package com.example.nasapicturesstealer.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@MapperConfig(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonMapperConfig {

}
