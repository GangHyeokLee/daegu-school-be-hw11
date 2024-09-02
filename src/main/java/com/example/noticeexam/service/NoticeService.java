package com.example.noticeexam.service;

import com.example.noticeexam.domain.Notice;
import com.example.noticeexam.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final S3Service s3Service;

    public NoticeService(NoticeRepository noticeRepository, S3Service s3Service) {
        this.noticeRepository = noticeRepository;
        this.s3Service = s3Service;
    }

    public void create(Notice notice, MultipartFile file) throws IOException {
        // 기본 사진이름을 uuid로 처리하기
        UUID uuid = UUID.randomUUID();
        String filename = uuid + "_" + file.getOriginalFilename();
        s3Service.uploadFile(file, filename);

        // 객체에 저장
        notice.setImg(filename);
        notice.setDate(LocalDateTime.now());

        noticeRepository.save(notice);
    }

    //readlist
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    //readdetail
    public Notice findById(Integer id) {
        Optional<Notice> notice = noticeRepository.findById(id);
        return notice.orElse(null);
    }
}
