package com.sosialmedia.sosialmedia.service.impl;

import com.sosialmedia.sosialmedia.entity.File;
import com.sosialmedia.sosialmedia.entity.Post;
import com.sosialmedia.sosialmedia.exception.NotFoundException;
import com.sosialmedia.sosialmedia.repository.FileRepository;
import com.sosialmedia.sosialmedia.repository.PostRepository;
import com.sosialmedia.sosialmedia.repository.UserRepository;
import com.sosialmedia.sosialmedia.service.FileService;
import com.sosialmedia.sosialmedia.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void uploadProfilePicture(Long userId, MultipartFile multipartFile) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        fileRepository.findByUser(user).ifPresent(oldFile -> {
            FileUtil.deleteFile(oldFile.getName());
            fileRepository.delete(oldFile);
        });
        String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();//Sistemi saati add
        FileUtil.saveFile(fileName, multipartFile);
        File fileEntity = new File();
        fileEntity.setName(fileName);
        fileEntity.setName(multipartFile.getContentType());
        fileEntity.setUser(user);
        fileRepository.save(fileEntity);


    }

    @Override
    public void uploadPostMedia(Long postId, MultipartFile multipartFile) {
        String fileName = System.currentTimeMillis() + " ** " + multipartFile.getOriginalFilename();//Sistemi saati add
        FileUtil.saveFile(fileName, multipartFile);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));
        File fileEntity = new File();
        fileEntity.setName(fileName);
        fileEntity.setType(multipartFile.getContentType());
        fileEntity.setPost(post);
        fileRepository.save(fileEntity);
    }

    @Override
    public void deleteFile(Long fileId) {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new NotFoundException("File not found"));
        FileUtil.deleteFile(file.getName());
        fileRepository.delete(file);


    }
}
