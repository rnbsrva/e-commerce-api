package com.akerke.ecommerceapi.service;

import com.akerke.ecommerceapi.common.enums.ImageType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    Resource getImage(Long id, String fileName, ImageType imageType);

    void save(List<MultipartFile> images, Long id, ImageType imageType);

}
