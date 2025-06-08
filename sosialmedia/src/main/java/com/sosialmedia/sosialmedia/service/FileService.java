package com.sosialmedia.sosialmedia.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void uploadProfilePicture(Long userId, MultipartFile multipartFile);
    void uploadPostMedia(Long postId,MultipartFile multipartFile);
    void deleteFile(Long fileId);
}
