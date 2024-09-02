package com.example.noticeexam.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String about;

    private LocalDateTime date;

    // 사진 처리용
    private String img;
}
