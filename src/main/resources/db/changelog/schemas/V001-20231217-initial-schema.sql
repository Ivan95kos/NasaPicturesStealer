CREATE TABLE IF NOT EXISTS cameras
(
    id         BIGINT AUTO_INCREMENT,
    nasa_id    BIGINT NOT NULL UNIQUE,
    name       VARCHAR(255) NOT NULL,
    created_at DATE NOT NULL,

    constraint cameras_PK PRIMARY KEY (id),
    constraint cameras_nasa_id_AK UNIQUE (nasa_id)
);

CREATE TABLE IF NOT EXISTS pictures
(
    id          BIGINT AUTO_INCREMENT,
    nasa_id     BIGINT NOT NULL UNIQUE,
    camera_id   BIGINT NOT NULL,
    img_src     VARCHAR(MAX),
    created_at  DATE NOT NULL,

    constraint pictures_PK PRIMARY KEY (id),
    constraint pictures_cameras_FK FOREIGN KEY (camera_id) REFERENCES cameras (id)
)
