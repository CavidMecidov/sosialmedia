package com.sosialmedia.sosialmedia.controller;

import com.sosialmedia.sosialmedia.dto.BaseResponse;
import com.sosialmedia.sosialmedia.service.FileService;
import com.sosialmedia.sosialmedia.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/files")
public class FileController {
    private final FileService fileService;

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileName) {
        byte[] imageData = FileUtil.readFile(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }

    @PostMapping("/upload-profile-picture/{userId}")
    public BaseResponse<Void> uploadProfilePicture(Long userId, MultipartFile file) {
        fileService.uploadProfilePicture(userId, file);
        BaseResponse<Void> baseResponse = new BaseResponse<>(null, "Profile is picture upload with successfull", true);
        return baseResponse;
    }

    @PostMapping("/upload-post-media/{postId}")
    public BaseResponse<Void> uploadPostMedia(
            @PathVariable Long postId,
            @RequestParam MultipartFile file) {

        fileService.uploadPostMedia(postId, file);

        return new BaseResponse<>(null, "Media is upload successful for post ", true);
    }

    @DeleteMapping("/{fileId}")
    public BaseResponse<Void> deleteFile(@PathVariable Long fileId) {
        fileService.deleteFile(fileId);

        return new BaseResponse<>(null, "File delete successful", true);
    }

}
