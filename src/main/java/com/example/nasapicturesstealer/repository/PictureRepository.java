package com.example.nasapicturesstealer.repository;

import com.example.nasapicturesstealer.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
