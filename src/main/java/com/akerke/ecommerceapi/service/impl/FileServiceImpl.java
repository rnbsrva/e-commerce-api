package com.akerke.ecommerceapi.service.impl;

import com.akerke.ecommerceapi.common.enums.ImageType;
import com.akerke.ecommerceapi.common.exception.MinioException;
import com.akerke.ecommerceapi.service.FileService;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final MinioClient minioClient;

    @Override
    public void save(List<MultipartFile> images, Long id, ImageType imageType) {
        String baseBucketName = imageType.getName();
        String folderName = id.toString();

        for (MultipartFile image : images) {
            String objectName = folderName + "/" + image.getOriginalFilename();
            uploadImageToMinio(image, baseBucketName, objectName);
        }
    }

    private void uploadImageToMinio(MultipartFile image, String baseBucketName, String objectName) {
        try (InputStream inputStream = image.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(baseBucketName)
                            .object(objectName)
                            .stream(inputStream, image.getSize(), -1)
                            .contentType(image.getContentType())
                            .build()
            );
        } catch (Exception e) {
            throw new MinioException("Error uploading image to MinIO", e);
        }
    }


    @Override
    public Resource getImage(Long id, String fileName, ImageType imageType) {
        String baseBucketName = imageType.getName();
        String objectPath = id + "/" + fileName;

        try (InputStream inputStream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(baseBucketName)
                        .object(objectPath)
                        .build())) {

            return new InputStreamResource(inputStream);
        } catch (Exception e) {
            throw new MinioException("Error getting file from MinIO", e);
        }

    }


}
