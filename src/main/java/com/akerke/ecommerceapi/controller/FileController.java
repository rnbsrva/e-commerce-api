package com.akerke.ecommerceapi.controller;

import com.akerke.ecommerceapi.common.enums.ImageType;
import com.akerke.ecommerceapi.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
@RestController
public class FileController {

    private final FileService fileService;

    @GetMapping
    ResponseEntity<Resource> getImage(
            @RequestParam Long id,
            @RequestParam String fileName,
            @RequestParam ImageType imageType
    ) {
        return ResponseEntity.ok(fileService.getImage(id, fileName, imageType));
    }

}
