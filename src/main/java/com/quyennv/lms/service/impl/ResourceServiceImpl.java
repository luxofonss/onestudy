package com.quyennv.lms.service.impl;

import com.quyennv.lms.dto.resource.GetResourceResponse;
import com.quyennv.lms.entities.Resource;
import com.quyennv.lms.repository.ResourceRepository;
import com.quyennv.lms.security.UserPrincipal;
import com.quyennv.lms.service.ResourceService;
import com.quyennv.lms.thirdparty.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    private final StorageService storageService;

    private final ResourceRepository resourceRepository;

    public ResourceServiceImpl(StorageService storageService, ResourceRepository resourceRepository) {
        this.storageService = storageService;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Resource uploadResource(MultipartFile file, UserPrincipal requester) {

        String s3FileName = storageService.uploadFile(file);

        Resource resource = Resource.builder()
                .title(file.getOriginalFilename())
                .url(s3FileName)
                .ownerId(requester.getId())
                .build();
        resourceRepository.save(resource);
        return resource;
    }

    @Override
    public GetResourceResponse getResource(String id, UserPrincipal requester) {
        Resource resource = resourceRepository.findById(UUID.fromString(id));

        if (Objects.isNull(resource)) {
            throw new RuntimeException("Resource not found");
        }

        try {
            if (!requester.getId().equals(resource.getOwnerId())) {
                throw new RuntimeException("You are not allowed to access this resource");
            }

            String url = storageService.generateUrl(resource.getUrl());

            return GetResourceResponse.builder()
                    .name(resource.getTitle())
                    .url(url)
                    .build();

        } catch (Exception e) {
            log.info("Generate url failed {}", e.getMessage());
            throw new RuntimeException("Generate url failed");
        }
    }


}
