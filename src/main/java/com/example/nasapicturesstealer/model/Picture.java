package com.example.nasapicturesstealer.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pictures")
public class Picture implements Serializable {

    @Serial
    private static final long serialVersionUID = -5716405045840890609L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nasa_id", nullable = false, unique = true)
    private Long nasaId;
    @Column(name = "img_src", nullable = false)
    private String imgSrc;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "camera_id", referencedColumnName = "id")
    private Camera camera;
}
