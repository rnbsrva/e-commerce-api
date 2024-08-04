package com.akerke.ecommerceapi.config;

import com.akerke.ecommerceapi.core.AppConstants;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MinioConfig {

    @Value("${minio.url}")
    private String url;

    @Value("${minio.access.name}")
    private String accessKey;

    @Value("${minio.access.secret}")
    private String accessSecret;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, accessSecret)
                .build();
    }

    @Bean
    public CommandLineRunner bucketInitializer(MinioClient minioClient) {
        return (args) -> AppConstants.BUCKET_NAMES.forEach(bucketName -> {
            try {
                boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
                if (!isExist) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                    log.info("Bucket created successfully: " + bucketName);
                } else {
                    log.info("Bucket already exists: " + bucketName);
                }
            } catch (MinioException e) {
                log.error("Error occurred while checking/creating bucket " + bucketName + ": " + e.getMessage());
            } catch (Exception e) {
                log.error("An unexpected error occurred for bucket " + bucketName + ": " + e.getMessage());
            }
        });
    }
}